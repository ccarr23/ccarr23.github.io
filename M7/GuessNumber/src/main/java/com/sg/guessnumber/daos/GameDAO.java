/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessnumber.daos;

import com.sg.guessnumber.dtos.Game;
import java.util.List;

/**
 *
 * @author corey
 */
public interface GameDAO {
    
    Game createGame(Game game);
    
    public Game postAnswer(Game game);
    
    List<Game> readGames();
    
    Game readGameById(int gameId);
    
    public Game gameFinished(Game game);
    
    
}
