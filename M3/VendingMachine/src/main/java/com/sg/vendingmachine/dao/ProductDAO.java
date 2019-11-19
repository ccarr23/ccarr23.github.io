/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dtos.Product;
import java.util.List;

/**
 *
 * @author corey
 */
public interface ProductDAO {
    
     List<Product> readAll() throws ProductDAOException;
     
     Product getItem(String itemId) throws ProductDAOException;
     
     public void updateQty(String itemId, int qty) throws ProductDAOException;

}
