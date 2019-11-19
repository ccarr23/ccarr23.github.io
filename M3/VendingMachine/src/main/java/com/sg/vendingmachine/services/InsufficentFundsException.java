/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.services;

/**
 *
 * @author corey
 */
public class InsufficentFundsException extends Exception {
    
        public InsufficentFundsException(String msg){
        super(msg);
    }
}
