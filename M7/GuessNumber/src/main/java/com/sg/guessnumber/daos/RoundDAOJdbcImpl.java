/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessnumber.daos;

import com.sg.guessnumber.dtos.Game;
import com.sg.guessnumber.dtos.Round;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoundDAOJdbcImpl implements RoundDAO {
    
    private final JdbcTemplate jdbcTemplate;
    
    
    @Autowired
    public RoundDAOJdbcImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        
    }
    
    private final String insertRound = "Insert into rounds (roundId, guess, result, gameId) values (?,?,?,?);";
    private final String setGuess = "Insert into rounds (guess, result, gameId) values (?,?,?);";
    private final String selectRound = "select roundId, guess, result, gameId From rounds where roundId = ?;";
    private final String selectAllRounds = "Select roundId, guess, result, gameId from rounds;";
    private final String selectAllRoundsById = "Select rounds.roundId, rounds.guess, rounds.result, games.gameId from rounds INNER JOIN games on games.gameId = rounds.gameId WHERE games.gameId = ?;";
    
    
    @Override
    public Round createRound(Round round) {
     jdbcTemplate.update(insertRound, round.getRoundId(), round.getGuess(), round.getResult(), round.getGameId());
     int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
     round.setRoundId(newId);
     return round;
    }

    @Override
    public Round postGuess(Round round) {
        jdbcTemplate.update(setGuess, round.getGuess(), round.getResult(), round.getGameId());
        return round;
    }
    
    @Override
    public Round readRoundById(int roundId) {
        return this.jdbcTemplate.queryForObject(selectRound, new RoundJDBCMapper(), roundId);
    } 
    
    @Override
    public List<Round> readRounds() {
        return this.jdbcTemplate.query(selectAllRounds, new RoundJDBCMapper());
    }

    @Override
    public List<Round> readRoundsByGame(int gameId) {
        return this.jdbcTemplate.query(selectAllRoundsById, new RoundJDBCMapper(), gameId);
    }


            private class RoundJDBCMapper implements org.springframework.jdbc.core.RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int i) throws SQLException {
            Round round = new Round();

            round.setRoundId(rs.getInt("roundId"));
            round.setGuess(rs.getString("guess"));
            round.setResult(rs.getString("result"));
            round.setGameId(rs.getInt("gameId"));
            
            
            
            
            
            return round;
        }

    }
    
}
