/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine2.services;

import com.sg.vendingmachine2.dao.ProductDAOException;
import com.sg.vendingmachine2.dtos.Change;
import com.sg.vendingmachine2.dtos.Product;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author corey
 */
public interface VendingMachineService {
    
    public void addMoney(BigDecimal money);
    
    public Product selectItem(String itemId) throws ProductDAOException, ItemNotFoundException;
     
    public Change buyItem(String itemId) throws ProductDAOException, InsufficentFundsException, ItemNotFoundException;
    
    List<Product> viewItems() throws ProductDAOException;
}
