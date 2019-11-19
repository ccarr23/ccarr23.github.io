/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine2.view;

import com.sg.vendingmachine2.dtos.Change;
import com.sg.vendingmachine2.dtos.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author corey
 */
public class VendingMachineView {
    private UserIO io;
    
    public VendingMachineView(UserIO io){
        this.io = io;
    }
    
    public BigDecimal insertMoney(){
        
        BigDecimal result = null;
        do {
            String input = io.readString("Please Insert Money: $");
            try {
                result = new BigDecimal(input);
            } catch (NumberFormatException e){
                io.print("Invalid entry.");
            }
        } while (result == null);
        result = result.setScale(2, RoundingMode.DOWN);
        io.print("You Entered: $" + result);
        return result;
    }
    
    public int purchaseMenu(){
        banner();
        io.print("1. Buy Item.");
        io.print("2. Back to Main Menu.");
        banner();
        return io.readInt("Please Choose.", 1, 2);
    }
    
    public void banner(){
        io.print("==========================");
    }
    
    public int getMenuChoice(){
        
        io.print("Main Menu");
        banner();
        io.print("1. Insert Money");
        io.print("2. Select Item");
        io.print("3. Exit");
        banner();
        return io.readInt("Please select from the given choices.", 1, 3);
    }
    
    public String inputItemId(){
        banner();
        return io.readString("Enter Item ID:");
    }
    
    
    public void listAllInventory(List<Product> itemList){
        for(Product products : itemList){
            io.print("Item ID:" + products.getItemId()
            + "  " + products.getName()
            + "  " + products.getQty()
            + "  $" + products.getCost());
    }
  }
    
    public void errorMessage(String errorMsg){
        io.print("ATTENTION");
        io.print(errorMsg);
    }
    
    public void viewChange (Change change){
        io.print(change.getQuarters() + " Quarters");
        io.print(change.getDimes() + " Dimes");
        io.print(change.getNickles() + " Nickles");
        io.print(change.getPennies() + " Pennies");
    }
    
}
