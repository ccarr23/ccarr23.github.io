/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.DAOException;
import com.sg.flooringmastery.dtos.Order;
import com.sg.flooringmastery.dtos.Product;
import com.sg.flooringmastery.service.FMService;
import com.sg.flooringmastery.service.InvalidDataException;
import com.sg.flooringmastery.view.UserIO;
import com.sg.flooringmastery.view.UserIOImpl;
import com.sg.flooringmastery.view.View;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author corey
 */
public class Controller {
    
    private final View view;
    private final FMService service;
    
    private UserIO io = new UserIOImpl();
    
    public Controller(View view, FMService service){
        this.view = view;
        this.service = service;
    }
    
    public void run() throws DAOException, InvalidDataException, NumberFormatException {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try {
        service.setMode(view.modeSelect());
        while(keepGoing){
            menuSelection = getMainMenu();
            switch(menuSelection){
                case 1:
                    displayOrders();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    save();
                    break;
                case 6:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        } catch (DAOException ex){
            view.errorMessage(ex.getMessage());
        }
        displayExit();
    }
    
    private int getMainMenu(){
        return view.mainMenuChoice();
    }
    
    private void displayOrders() throws DAOException, InvalidDataException {         
        List<Order> list;
        LocalDate date = LocalDate.MAX;
        view.displayOrdersBanner();
    do {
      try {
        date = view.enterDate();
        list = service.getOrders(date);
        break;
      } catch (DAOException | InvalidDataException | DateTimeParseException ex){
        view.errorMessage(ex.getMessage());  
      }
      
    }
        while (true);
        view.displayOrders(list);
    }
    
    public void addOrder() throws DAOException {
     String name;
     String state;
     String product;
     BigDecimal area = BigDecimal.ZERO;
     List<Product> productList = service.getProduct();
     
     view.newOrderBanner();
     
     name = view.enterName();
     while(true){
         state = view.enterState();
         try {
            service.validateState(state);
            break;
         }
      catch (DAOException ex){
             view.errorMessage(ex.getMessage());
             }
           }
         
         while(true){
             view.displayProducts(productList);
             product = view.chooseProduct();
             try {                
                service.validateProduct(product);
                break;
             }
           catch (DAOException ex){
            view.errorMessage(ex.getMessage());   
         }    
       }
             
         while(true){
           try {
             area = view.inputArea();
             break;
           }
           catch (NumberFormatException ex){
            view.errorMessage(ex.getMessage());   
           }
         }
         
         Order order = service.newOrder(name, state, product, area);
         if(view.addConfirmation().equalsIgnoreCase("y")){
             try {
                 service.addOrder(order);
                 view.displayAddSuccess();
             } catch (DAOException ex){
              view.errorMessage(ex.getMessage());   
             }
           }
         }
    
     public void editOrder() throws DAOException, InvalidDataException, DateTimeParseException {
     LocalDate date = LocalDate.MAX;
     int orderNumber;
     Order order = new Order();
     Order editOrder;
     String name;
     String state;
     String product;
     BigDecimal area = BigDecimal.ZERO;
     List<Product> productList = service.getProduct();
     
     view.editOrderBanner();
     
     
    do {
      try {
        date = view.enterDate();
        service.validateDate(date);
        orderNumber = view.enterOrderNumber();
        order = service.getOrder(date, orderNumber);
        break;
      } catch (DAOException | DateTimeParseException ex){
      view.errorMessage(ex.getMessage());    
      }
      
    }
    
    while(true);
           

     view.displayOrder(order);
     view.editPrompt();
     name = view.enterName();
         
         
         state = view.enterState();
         try {
            service.validateState(state);
            
         }
      catch (DAOException ex){
             view.errorMessage(ex.getMessage());
             }
           
              
             view.displayProducts(productList);
             product = view.chooseProduct();
             try {                
                service.validateProduct(product);
                
             }
           catch (DAOException ex){
            view.errorMessage(ex.getMessage());   
         }    
                    
           try {
             area = view.inputArea();

           }
           catch (NumberFormatException ex){
           view.errorMessage(ex.getMessage());    
           }
         
         
         editOrder = service.editOrder(order, name, state, product, area);
         editOrder.setOrderNumber(order.getOrderNumber());
         if(view.editConfirmation().equalsIgnoreCase("y")){
             try {
                 service.editOrder(date, order, editOrder);
                 view.displayEditSuccess();
             } catch (DAOException ex){
             view.errorMessage(ex.getMessage());    
             }
           }
         }
     
     
    public void removeOrder() throws DAOException {
     LocalDate date = LocalDate.MAX;
     int orderNumber;
     Order order;
     
     view.removeOrderBanner();
     
     do {
      try {
        date = view.enterDate();
        service.validateDate(date);
        orderNumber = view.enterOrderNumber();
        order = service.getOrder(date, orderNumber);
        break;
      } catch (DAOException | DateTimeParseException ex){
       view.errorMessage(ex.getMessage());   
      }
      
    }
     while(true);
     view.displayOrder(order);
     if(view.removeConfirmation().equalsIgnoreCase("y")){
     try {
     service.removeOrder(date, orderNumber);
     view.displayRemoveSuccess();
      } catch (DAOException ex){
       view.errorMessage(ex.getMessage());   
      }
     }
    }
    
    public void save() throws DAOException {
        
       try {
        service.save();
        view.displaySaveSuccess();
       } catch (DAOException ex){
        view.errorMessage(ex.getMessage());   
       }
    }
    
    public void displayExit(){
        io.print("Exiting");
    }
    
    public void unknownCommand(){
        io.print("Unknown Command");
    }
}
