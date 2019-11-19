/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessnumber.dtos;

import java.util.Objects;

/**
 *
 * @author corey
 */
public class Round {
    private int roundId;
    private String guess;
    private String result;
    private int gameId;
    
    
    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.roundId;
        hash = 89 * hash + Objects.hashCode(this.guess);
        hash = 89 * hash + Objects.hashCode(this.result);
        hash = 89 * hash + this.gameId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        return true;
    }



    




    
    
    



    
    
    
    
    
    
}
