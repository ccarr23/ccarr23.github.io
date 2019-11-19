/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine2.services;

import com.sg.vendingmachine2.dao.ProductDAO;
import com.sg.vendingmachine2.dao.ProductDAOFileImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author acetip
 */
public class VendingMachineServiceTest {
    
VendingMachineService service;
          
    
    public VendingMachineServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
        service = ctx.getBean("service", VendingMachineService.class);
    }
    
    @After
    public void tearDown() {
    }
    
}
