/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessnumber.daos;

import com.sg.guessnumber.dtos.Game;
import com.sg.guessnumber.dtos.Round;
import java.util.List;

/**
 *
 * @author corey
 */
public interface RoundDAO {
    
    Round createRound(Round round);
    
    Round readRoundById(int roundId);
    
    Round postGuess(Round guess);
    
    List<Round> readRounds();
    
    List<Round> readRoundsByGame(int gameId);
    
    
}
