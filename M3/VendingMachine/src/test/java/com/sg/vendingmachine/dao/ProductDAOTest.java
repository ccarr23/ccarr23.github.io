/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dtos.Product;
import java.math.BigDecimal;
import java.util.List;
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
public class ProductDAOTest {
    
    private ProductDAO dao = new ProductDAOFileImpl();
    
    Product one = new Product("1", "Snickers", 0, new BigDecimal("0.50"));
    Product two = new Product("2", "Trix", 0, new BigDecimal("1.00"));
    Product three = new Product("3", "PayDay", 0, new BigDecimal("1.50"));
    Product four = new Product("4", "Twislers", 0, new BigDecimal("2.00"));
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Test of getItem method, of class ProductDAO.
     */
    @Test
    public void testGetItem() throws Exception {
        try {
            assertEquals(dao.getItem("1"), one);
            assertEquals(dao.getItem("2"), two);
            assertEquals(dao.getItem("3"), three);
            assertEquals(dao.getItem("4"), four);
            assertNull(dao.getItem("Null"));
        } catch (ProductDAOException e) {
            fail();
        }
    }
    
    @Test
    public void testUpdateQty() throws Exception {
        try {
            dao.updateQty("1", -1);
            fail();  
        } catch (ProductDAOException e){
          
        }
    }
    
    @Test
    public void testReadFile() throws Exception {
        try {
            List<Product> itemList = dao.readAll();
            assertEquals(itemList.size(), 9);
        } catch (ProductDAOException e){
            fail();
        }
    }

    
}
