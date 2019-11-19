/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine2.services;

/**
 *
 * @author corey
 */
public class ItemNotFoundException extends Exception {

    public ItemNotFoundException() {
    }
    
    public ItemNotFoundException(String msg){
        super(msg);
    }
    
    public ItemNotFoundException(String msg, Throwable cause){
      super(msg, cause);  
    }
    
    public ItemNotFoundException(Throwable cause){
        super(cause);
    }
    
    public ItemNotFoundException(String msg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(msg, cause, enableSuppression, writableStackTrace);
    }
}
