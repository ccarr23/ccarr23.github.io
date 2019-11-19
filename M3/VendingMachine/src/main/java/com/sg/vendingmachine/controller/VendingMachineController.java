/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.ProductDAOException;
import com.sg.vendingmachine.dtos.Change;
import com.sg.vendingmachine.dtos.Product;
import com.sg.vendingmachine.services.InsufficentFundsException;
import com.sg.vendingmachine.services.ItemNotFoundException;
import com.sg.vendingmachine.services.VendingMachineService;
import com.sg.vendingmachine.services.VendingMachineServiceImpl;
import com.sg.vendingmachine.view.UserIO;
import com.sg.vendingmachine.view.UserIOConsoleImpl;
import com.sg.vendingmachine.view.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author corey
 */
public class VendingMachineController {
    
    private final VendingMachineView view;
    private final VendingMachineService service;
    

    private UserIO io = new UserIOConsoleImpl();
    
    public VendingMachineController(VendingMachineView view, VendingMachineService s){
        this.view = view;
        this.service = s;
    }
    
    public void run() throws ItemNotFoundException, InsufficentFundsException {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try {
            service.addMoney(insertMoney());
            while(keepGoing){
                listInventory();
                view.banner();
                menuSelection = getMainMenu();
                switch(menuSelection){
                    case 1:
                        service.addMoney(insertMoney());
                        break;
                    case 2:
                        chooseItem();
                        break;
                    case 3:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                        
                }
            }
            displayExit();
        } catch (ProductDAOException e){
           view.errorMessage(e.getMessage());
        }
    }

    private int getMainMenu() throws ProductDAOException {
        return view.getMenuChoice();
    }
    
    private BigDecimal insertMoney(){
        return view.insertMoney();
    }
    
    
    private void chooseItem() throws ItemNotFoundException, ProductDAOException, InsufficentFundsException {
       
       boolean keepGoing = true;
       try {
       String item = view.inputItemId();
       service.selectItem(item);
       int choice = view.purchaseMenu();
       while(keepGoing){
       switch (choice){
           case 1:
              view.viewChange(service.buyItem(item));
              break;
           case 2:
              keepGoing = false;
              break;
           default:
              unknownCommand(); 
        }
      }
    } catch (ProductDAOException | InsufficentFundsException | ItemNotFoundException e) {
        view.errorMessage(e.getMessage());
    }
   }
    
    private void listInventory() throws ProductDAOException {
        List<Product> itemList = service.viewItems();
        view.listAllInventory(itemList);
    }
    
    public void displayExit(){
        io.print("Exiting.");
    }
    
    public void unknownCommand(){
        io.print("Unknown Command");
    }
    
    
}
