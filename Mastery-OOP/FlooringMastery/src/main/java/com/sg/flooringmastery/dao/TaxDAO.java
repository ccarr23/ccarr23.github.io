/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dtos.State;

/**
 *
 * @author corey
 */
public interface TaxDAO {
    
    State getState(String name) throws DAOException;
    
}
