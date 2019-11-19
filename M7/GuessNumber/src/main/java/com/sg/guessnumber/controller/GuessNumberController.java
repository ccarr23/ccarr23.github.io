/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessnumber.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sg.guessnumber.dtos.Game;
import com.sg.guessnumber.dtos.Round;
import com.sg.guessnumber.services.GuessNumberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author corey
 */

@RestController
@RequestMapping("/api")
public class GuessNumberController {
    
private GuessNumberService service;


@Autowired    

public GuessNumberController(GuessNumberService service) {
    this.service = service;
}
public void run(){
    
}
    @RequestMapping(value="/games/begin", method=POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame(Game game){
        return service.createGame(game);
    }
    
    @RequestMapping(value="/games/guess", method=POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Round postGuess(@RequestBody Round round){
        return service.createRound(round);
    }
    
    @RequestMapping(value="/games", method=GET)
    public List<Game> getGames(){
     return service.readGames();
    }
    
    @RequestMapping(value="/games/{gameId}", method=GET)
    public ResponseEntity<Game> getGameById(@PathVariable int gameId){
        Game game = service.readGameById(gameId);
        if (game == null){
          return new ResponseEntity(null, HttpStatus.NOT_FOUND);  
        }
        return new ResponseEntity(game, HttpStatus.OK);
    }
    @RequestMapping(value="/games/rounds/{gameId}", method=GET)
    public ResponseEntity<Round> getRoundsById(@PathVariable int gameId){
        
        List<Round> round = service.readRoundsByGame(gameId);
        if (round == null){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(round, HttpStatus.OK);
    }
    
    
    
    
    
}
