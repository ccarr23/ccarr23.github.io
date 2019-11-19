/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessnumber.daos;

import com.sg.guessnumber.dtos.Game;
import com.sg.guessnumber.dtos.Round;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class GameDAOJdbcImpl implements GameDAO {
    
   private final JdbcTemplate jdbcTemplate;
   
    private final String gameStart = "insert into games(gameId, answer, isFinished) values (?,?,?);";
    private final String getAnswer = "insert into games(answer, isFinished) values (?,?);";
    private final String selectAllGames = "select games.gameId, answer, isFinished from games;";
    private final String selectGame =  "select games.gameId, answer, isFinished from games where games.gameId = ?";
    private final String gameFinished = "update games Set answer = ?, isFinished = ? where gameId = ?;";

    
    @Autowired
    public GameDAOJdbcImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Game createGame(Game game) {
     jdbcTemplate.update(gameStart, game.getGameId(), game.getAnswer(), game.isFinished());
     int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
     game.setGameId(newId);
     return game;
    }

    @Override
    public Game postAnswer(Game game) {
       jdbcTemplate.update(getAnswer, game.getAnswer(), game.isFinished());
       return game; 
    }

    @Override
    public Game readGameById(int gameId) {
        return this.jdbcTemplate.queryForObject(selectGame, new GameJDBCMapper(), gameId);
    }

    @Override
    public List<Game> readGames() {
        return this.jdbcTemplate.query(selectAllGames, new GameJDBCMapper());
    }

   @Override
    public Game gameFinished(Game game){
        jdbcTemplate.update(gameFinished, game.getAnswer(), game.isFinished(), game.getGameId());
        return game;
    }
    
    
        private class GameJDBCMapper implements org.springframework.jdbc.core.RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int i) throws SQLException {
            Game game = new Game();
            Round round = new Round();

            game.setGameId(rs.getInt("gameId"));
            game.setAnswer(rs.getString("answer"));
            game.setFinished(rs.getBoolean("isFinished"));
            
//            round.setRoundId(rs.getInt("roundId"));
//            round.setGuess(rs.getString("guess"));
            
            
            
            return game;
        }

    }
}
