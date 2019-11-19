/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.DAOException;
import com.sg.flooringmastery.dao.OrdersDAOFileImpl;
import com.sg.flooringmastery.dao.ProductDAOFileImpl;
import com.sg.flooringmastery.dao.TaxDAOFileImpl;
import com.sg.flooringmastery.dtos.Order;
import com.sg.flooringmastery.dtos.Product;
import com.sg.flooringmastery.dtos.State;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class FMServiceTest {
    
    private final OrdersDAOFileImpl odi = new OrdersDAOFileImpl();
    private final ProductDAOFileImpl pdi = new ProductDAOFileImpl();
    private final TaxDAOFileImpl tdi = new TaxDAOFileImpl();
    private final Mode production = new Mode();
    
    private final FMService service = new FMService(odi, pdi, tdi, production);
    private final LocalDate date = LocalDate.MAX;
    private final String testDate = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
      
    private Order test = new Order();
    
    public FMServiceTest() {
    }
    
    private Order testOrder(){
      	Order order = new Order();
	State state = new State();
	Product product = new Product();

	order.setOrderNumber(1);
	order.setCustomerName("Zero");
	state.setName("PA");
	state.setTaxRate(new BigDecimal("7.125"));
	product.setProductType("wood");
	order.setArea(new BigDecimal("500"));
	product.setCostPerSqFoot(new BigDecimal("1.50"));
	product.setCostLaborPerSqfoot(new BigDecimal("2.50"));
	order.setMaterialCost(new BigDecimal("3.50"));
	order.setLaborCost(new BigDecimal("4.50"));
	order.setTotalTax(new BigDecimal("5.50"));
	order.setTotal(new BigDecimal("6.50"));

	order.setState(state);
	order.setProduct(product);

	return order;  
    }
    
    @Before
    public void setUp() {
        test = testOrder();
        odi.getCurrentOrder().add(test);
        odi.allOrders().put(testDate, odi.getCurrentOrder());
    }
    
    @After
    public void tearDown() {
        odi.getCurrentOrder().clear();
        odi.allOrders().remove(testDate);
        
    }

    @Test
    public void testAddOrder() throws Exception {
        Order testOne = testOrder();
        try {
            service.addOrder(testOne);
        } catch (DAOException ex){
            fail();
        }
        assertEquals(2, testOne.getOrderNumber());
    }
    
    @Test 
    public void testRemoveOrder() throws Exception {
    
        try {
            service.removeOrder(date, test.getOrderNumber());
        } catch (DAOException ex){
            fail();
        }
        assertEquals(1, test.getOrderNumber());
}
    
}
