/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine2.dtos;

import java.math.BigDecimal;

/**
 *
 * @author corey
 */
public class Change {
    
    private final int quarters;
    private final int dimes;
    private final int nickles;
    private final int pennies;
    
    private static final BigDecimal QUARTER = new BigDecimal("0.25");
    private static final BigDecimal DIME = new BigDecimal("0.10");
    private static final BigDecimal NICKLE = new BigDecimal("0.05");
    private static final BigDecimal PENNY = new BigDecimal("0.01");
    
    
    public Change(BigDecimal money, BigDecimal cost){
        BigDecimal change = money.subtract(cost);
        BigDecimal[] remainder = change.divideAndRemainder(QUARTER);
        quarters = remainder[0].intValue();
        change = remainder[1];
        
        remainder = change.divideAndRemainder(DIME);
        dimes = remainder[0].intValue();
        change = remainder[1];
        
        remainder = change.divideAndRemainder(NICKLE);
        nickles = remainder[0].intValue();
        change = remainder[1];
        
        remainder = change.divideAndRemainder(PENNY);
        pennies = remainder[0].intValue();
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickles() {
        return nickles;
    }

    public int getPennies() {
        return pennies;
    }

    
    
}
