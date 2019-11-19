/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dtos.Product;
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
public class ProductDAOFileImplTest {
    
    private ProductDAOFileImpl pdi = new ProductDAOFileImpl();
    
    private Product testCarpet;
    private Product testWood;
    
    
    public ProductDAOFileImplTest() {
    }
    
    
    @Before
    public void setUp() {
        testCarpet = new Product();
        testCarpet.setProductType("carpet");
        testCarpet.setCostPerSqFoot(new BigDecimal(2.00));
        testCarpet.setCostLaborPerSqfoot(new BigDecimal(1.80));
        
        testWood = new Product();
        testWood.setProductType("wood");
        testWood.setCostPerSqFoot(new BigDecimal(3.00));
        testWood.setCostLaborPerSqfoot(new BigDecimal(2.80));
        
        pdi.products.add(testWood);
        pdi.products.add(testCarpet);
    }
    
    @After
    public void tearDown() {
        pdi.products.clear();
    }

    @Test
    public void testGetProduct() throws Exception {
        try {
            assertEquals(testCarpet, pdi.getProduct("carpet"));
            assertEquals(testWood, pdi.getProduct("wood"));
        } catch (DAOException ex){
            fail();
        }
    }
    
}
