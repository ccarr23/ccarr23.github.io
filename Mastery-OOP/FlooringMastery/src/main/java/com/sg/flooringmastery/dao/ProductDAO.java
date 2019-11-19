/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dtos.Product;

/**
 *
 * @author corey
 */
public interface ProductDAO {
    
    Product getProduct(String product) throws DAOException;
}
