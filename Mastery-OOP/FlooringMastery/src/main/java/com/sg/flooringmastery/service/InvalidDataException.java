/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

/**
 *
 * @author corey
 */
public class InvalidDataException extends Exception {
    
    
    public InvalidDataException() {
    }
    
    public InvalidDataException(String msg){
        super(msg);
    }
    
    public InvalidDataException(String msg, Throwable cause){
      super(msg, cause);  
    }
    
    public InvalidDataException(Throwable cause){
        super(cause);
    }
    
    public InvalidDataException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
