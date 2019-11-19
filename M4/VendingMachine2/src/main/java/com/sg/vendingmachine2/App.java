/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine2;

import com.sg.vendingmachine2.controller.VendingMachineController;
import com.sg.vendingmachine2.services.InsufficentFundsException;
import com.sg.vendingmachine2.services.ItemNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author corey
 */
public class App {
    
    public static void main(String[] args) throws ItemNotFoundException, InsufficentFundsException {
        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = 
           ctx.getBean("controller", VendingMachineController.class);   
        controller.run();
        
    }
}
