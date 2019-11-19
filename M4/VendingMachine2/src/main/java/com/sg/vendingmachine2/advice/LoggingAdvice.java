/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine2.advice;

import com.sg.vendingmachine2.dao.AuditDAO;
import com.sg.vendingmachine2.dao.ProductDAO;
import com.sg.vendingmachine2.dao.ProductDAOException;
import com.sg.vendingmachine2.services.InsufficentFundsException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author acetip
 */
public class LoggingAdvice {
    
    private AuditDAO audit;
    
    public LoggingAdvice(AuditDAO audit){
        this.audit = audit;
    }
    
    public void createAuditEntry(JoinPoint jp){
        Object args[] = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            audit.writeAuditEntry(auditEntry);
        } catch (ProductDAOException | InsufficentFundsException e) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}
