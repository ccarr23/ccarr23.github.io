/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessnumber.services;

import com.sg.guessnumber.dtos.Game;
import com.sg.guessnumber.dtos.Round;
import java.util.List;

/**
 *
 * @author corey
 */
public interface GuessNumberService {
     
    Game createGame(Game game);
    
    Round createRound(Round round);
    
    public Game postAnswer(Game game);
    
    public Round postGuess(Round round);
    
    List<Game> readGames();
    
    Game readGameById(int gameId);
    
    List<Round> readRounds();
    
    List<Round> readRoundsByGame(int gameId);
}
