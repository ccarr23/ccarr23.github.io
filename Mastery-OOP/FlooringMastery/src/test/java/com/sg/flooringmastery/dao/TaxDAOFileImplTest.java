/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dtos.State;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author corey
 */
public class TaxDAOFileImplTest {
    
    private TaxDAOFileImpl tdi = new TaxDAOFileImpl();
    
    private State testNY;
    private State testNJ;
    
    public TaxDAOFileImplTest() {
    }
        
    @Before
    public void setUp() {
        testNY = new State();
        testNY.setName("NYC");
        testNY.setTaxRate(new BigDecimal("5.50"));
        
        testNJ = new State();
        testNJ.setName("NJ");
        testNJ.setTaxRate(new BigDecimal("2.50"));
        
        tdi.states.add(testNY);
        tdi.states.add(testNJ);
        
        
    }
    
    @After
    public void tearDown() {
        tdi.states.clear();
    }

    @Test
    public void testGetStates() throws Exception {
        try {
            assertEquals(testNY, tdi.getState("NYC"));
            assertEquals(testNJ, tdi.getState("NJ"));
        } catch (DAOException ex) {
         fail();   
        }
    }
    
}
