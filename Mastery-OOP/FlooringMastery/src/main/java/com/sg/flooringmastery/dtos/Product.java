/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dtos;

import java.math.BigDecimal;

/**
 *
 * @author corey
 */
public class Product {
    
    private String productType;
    private BigDecimal costPerSqFoot;
    private BigDecimal costLaborPerSqfoot;
    

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostPerSqFoot() {
        return costPerSqFoot;
    }

    public void setCostPerSqFoot(BigDecimal costPerSqFoot) {
        this.costPerSqFoot = costPerSqFoot;
    }

    public BigDecimal getCostLaborPerSqfoot() {
        return costLaborPerSqfoot;
    }

    public void setCostLaborPerSqfoot(BigDecimal costLaborPerSqfoot) {
        this.costLaborPerSqfoot = costLaborPerSqfoot;
    }
    
    
    
    
    
}
