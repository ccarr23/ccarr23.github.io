/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessnumber.services;

import com.sg.guessnumber.daos.GameDAO;
import com.sg.guessnumber.daos.RoundDAO;
import com.sg.guessnumber.dtos.Game;
import com.sg.guessnumber.dtos.Round;
import static java.lang.Math.random;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuessNumberServiceImpl implements GuessNumberService {
    
    private final GameDAO dao;
    private final RoundDAO rDao;
    
    @Autowired
    
    public GuessNumberServiceImpl(GameDAO dao, RoundDAO rDao){
        this.dao = dao;
        this.rDao = rDao;
    }
    
    @Override
    public Game createGame(Game game) {
       Random random = new Random();
       String answer = String.format("%04d", random.nextInt(9999));
       game.setAnswer(answer);
      return dao.createGame(game);
    }
    
    @Override
    public Round createRound(Round round){
        
       Game game = dao.readGameById(round.getGameId());
        int partialMatch = 0;
        int exactMatch = 0;
        
        String answer = game.getAnswer();
        String guess = round.getGuess();

        String[] arrAnswer = answer.split("");
        String[] arrGuess = guess.split("");

        
        for(int i = 0; i < arrGuess.length; i++){
            if(arrGuess[i].equals(arrAnswer[i])){
                exactMatch++;
            } else if(arrGuess[i].equals(arrAnswer[0]) || arrGuess[i].equals(arrAnswer[1]) || arrGuess[i].equals(arrAnswer[2]) || arrGuess[i].equals(arrAnswer[3])){
                partialMatch++;
            }

            if (exactMatch == 4){
                round.setResult("e:" + exactMatch + ":" + "p:" + partialMatch);
                game.setFinished(true);
                dao.gameFinished(game);
               
            } else {
                if (exactMatch != 4){
                    round.setResult("e:" + exactMatch + ":" + "p:" + partialMatch);
                    game.setFinished(false);
                    dao.gameFinished(game);
                }
            }
            
        }
        return rDao.createRound(round);
    }

    @Override
    public Game postAnswer(Game game) {
        return dao.postAnswer(game);
    }
    
    @Override
    public Round postGuess(Round round){
        return rDao.postGuess(round);
    }

    @Override
    public List<Game> readGames() {
        return dao.readGames();
    }

    @Override
    public Game readGameById(int gameId) {
        return dao.readGameById(gameId);
    }
    
    @Override
    public List<Round> readRounds() {
    return rDao.readRounds();
    }

    @Override
    public List<Round> readRoundsByGame(int gameId) {
        return rDao.readRoundsByGame(gameId);
    }
    



    
}
