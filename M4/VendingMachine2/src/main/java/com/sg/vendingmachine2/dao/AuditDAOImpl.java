/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine2.dao;

import com.sg.vendingmachine2.services.InsufficentFundsException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author acetip
 */
public class AuditDAOImpl implements AuditDAO {
    
    public static final String AUDIT_FILE = "audit.txt";

    
    public void writeAuditEntry(String auditEntry) throws ProductDAOException, InsufficentFundsException {
        
        PrintWriter out;

        try {

            /*
            We are opening the audit file in append mode so that each entry will 
            be appended to the file rather than overwriting everything that was there before.  
            We accomplish this by setting the second parameter of the FileWriter constructor to 
            true:

            new FileWriter(AUDIT_FILE, true)
             */
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new ProductDAOException("Could not persist audit information.");
        }

        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + auditEntry);
        out.flush();
    }
      
  }
    
