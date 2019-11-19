/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dtos.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author corey
 */
public interface OrdersDAO {
    
    Order addOrder(Order order) throws DAOException;
    
    Order removeOrder(LocalDate date, int orderNumber) throws DAOException;
    
    Order getOrder(LocalDate date, int orderNumber) throws DAOException;
    
    List<Order> getOrders(LocalDate date) throws DAOException; 

    
}
