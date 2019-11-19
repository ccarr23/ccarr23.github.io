/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.services;

import com.sg.vendingmachine2.services.VendingMachineServiceImpl;
import com.sg.vendingmachine2.services.InsufficentFundsException;
import com.sg.vendingmachine2.dao.ProductDAOException;
import com.sg.vendingmachine2.dtos.Product;
import com.sg.vendingmachine2.dao.ProductDAOFileImpl;
import com.sg.vendingmachine2.dtos.Change;
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
public class VendingMachineServiceImplTest {
    
    private VendingMachineServiceImpl service = new VendingMachineServiceImpl(new ProductDAOFileImpl());
   
    
    Product one = new Product("1", "Snickers", 0, new BigDecimal("0.50"));
    Product two = new Product("2", "Trix", 0, new BigDecimal("1.00"));
    Product three = new Product("3", "PayDay", 0, new BigDecimal("1.50"));
    Product four = new Product("4", "Twislers", 0, new BigDecimal("2.00"));
    
    BigDecimal balance = new BigDecimal("0.00");
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of selectItem method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testSelectItem() throws Exception {
        try {
            assertEquals(service.selectItem("1"), one);
            assertEquals(service.selectItem("2"), two);
            assertEquals(service.selectItem("3"), three);
            assertEquals(service.selectItem("4"), four);
        } catch (ProductDAOException e){
            fail();
        }
    }

    /**
     * Test of buyItem method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testBuyItem() throws Exception {
     
        try {
            service.buyItem("1");
            fail("Insufficent Funds");
            
        } catch(InsufficentFundsException e){
            assertTrue(true);
        } catch (ProductDAOException e){
            fail();
        }
    }

    /**
     * Test of viewItems method, of class VendingMachineServiceImpl.
     */
    @Test
    public void testViewItems() throws Exception {
            try {
            List<Product> itemList = service.viewItems();
            assertEquals(itemList.size(), 9);
        } catch (ProductDAOException e){
            fail();
        }
    }
    
}
