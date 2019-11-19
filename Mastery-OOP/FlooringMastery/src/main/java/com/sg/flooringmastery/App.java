/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery;

import com.sg.flooringmastery.controller.Controller;
import com.sg.flooringmastery.dao.DAOException;
import com.sg.flooringmastery.service.InvalidDataException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author corey
 */
public class App {
    
        public static void main(String[] args) throws DAOException, InvalidDataException {
            
        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        Controller controller = 
           ctx.getBean("controller", Controller.class);   
        controller.run();
        
}
}
