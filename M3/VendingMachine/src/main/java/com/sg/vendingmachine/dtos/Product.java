/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dtos;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author corey
 */
public class Product {
    
    private String name;
    private BigDecimal cost;
    private String itemId;
    private int qty;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.cost);
        hash = 17 * hash + Objects.hashCode(this.itemId);
        hash = 17 * hash + this.qty;
        return hash;
    }

 public Product() {    
}
 public Product(String itemId, String name, int qty, BigDecimal cost){
     this.itemId = itemId;
     this.name = name;
     this.qty = qty;
     this.cost = cost;
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
        final Product other = (Product) obj;
        return true;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    
    
    public String getItemId() {
        return itemId;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
    
}
