/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine2.dao;

import com.sg.vendingmachine2.services.InsufficentFundsException;

/**
 *
 * @author acetip
 */
public interface AuditDAO {
    
    public void writeAuditEntry(String auditEntry) throws ProductDAOException, InsufficentFundsException;
    
}
