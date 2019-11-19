/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.view;

import com.sg.flooringmastery.dtos.Order;
import com.sg.flooringmastery.dtos.Product;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author corey
 */
public class View {
    
    private UserIO io;
    
    public View(UserIO io) {
        this.io = io;
    }
    
    
    public void banner() {
        io.print("========================");
    }
    
    public void mainMenuBanner(){
        io.print("========================");
        io.print("  Flooring: Main Menu   ");
        io.print("========================");
    }
    
    public void displayOrdersBanner(){
        io.print("========================");
        io.print("Flooring: Display Orders");
        io.print("========================");
    }
    
    public void newOrderBanner(){
        io.print("========================");
        io.print("  Flooring: New Order   ");
        io.print("========================");
    }
    
        public void editOrderBanner(){
        io.print("========================");
        io.print("  Flooring: Edit Order  ");
        io.print("========================");
    }
        
        public void removeOrderBanner(){
        io.print("========================");
        io.print(" Flooring: Remove Order ");
        io.print("========================");
    }
    
     public void productCatalogBanner(){
        io.print("========================");
        io.print("   Select a Material:   ");
        io.print("========================");
    }
     
     public String modeSelect(){
         return io.readString("Enter desired operation mode: Training or Production?");
     }
    
    public int mainMenuChoice(){
        mainMenuBanner();
        io.print("1. Display Orders ");
        io.print("2. Add Order ");
        io.print("3. Edit Order ");
        io.print("4. Remove an Order ");
        io.print("5. Save ");
        io.print("6. Exit");
        
        return io.readInt("Please Select an Option.", 1, 6);
       

        
    }
    
    public LocalDate enterDate(){
        banner();
        String dateInput = io.readString("Please Enter a Date: (Example: MMDDYYYY) ");       
        return LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("MMddyyyy"));
    }
    
    public int enterOrderNumber(){
        return io.readInt("Please Enter The Order Number: ");
    }
    
    public void editPrompt(){
        io.print("Please Enter New Information as Follows: ");
        io.print("Hit ENTER to Skip");
    }
    
    public String enterName(){
       return io.readString("Enter the customer's name: ");   
    }
    
    public String enterState(){
      return io.readString("Enter One of The Following States: OH, PA, MI, IN ");
     
    }
    
    public String chooseProduct(){
       return io.readString("Enter the Product Type: ");
      
    }
    
    public BigDecimal inputArea(){
     return new BigDecimal(io.readString("Enter the area."));
     
    }
    
    public String addConfirmation(){
        return io.readString("Do you want to commit?(y/n)");
    }
    
    public String editConfirmation(){
        return io.readString("Do you want to commit these changes?(y/n)");
    }
    
    public String removeConfirmation(){
        return io.readString("Are you sure you want to delete this order?(y/n)");
    }
    
    public void displayOrder (Order order){
        
        banner();
        
        io.print("Order Number: " + order.getOrderNumber());
        io.print("Customer Name: " + order.getCustomerName());
        io.print("State: " + order.getState().getName());
        io.print("Product: " + order.getProduct().getProductType());
        io.print("Area: " + order.getArea());
        io.print("Cost Per Sq.Foot: " + order.getProduct().getCostPerSqFoot());
        io.print("Cost Of Labor Per Sq.Foot: " + order.getProduct().getCostLaborPerSqfoot());
        io.print("Cost of Materials: " + order.getMaterialCost());
        io.print("Cost of Labor: " + order.getLaborCost());
        io.print("Total Tax: " + order.getTotalTax());
        io.print("Total Cost: " + order.getTotal());
        
        banner();
        
    }
    
    public void displayProducts(List<Product> list) {
       productCatalogBanner();
       for(Product products : list){
           io.print(products.getProductType());
       }
       banner();
    }
    
    public void displayOrders(List<Order> list) {
        for (Order orders : list){
          banner();
          displayOrder(orders); 
          banner();
        }
        banner();
    }
    
    public void displayAddSuccess(){
        io.print("Order Added. Save to Commit to File.");
    }
        
    public void displayEditSuccess(){
        io.print("Order Edited. Save to Update File.");
    }
    
    public void displayRemoveSuccess(){
        io.print("Order Deleted. Save to Update File.");
    }
    
    public void displaySaveSuccess(){
        io.print("All Data Saved Successfully.");
    }
    
    public void errorMessage(String msg){
        io.print(msg);
    }
    
}
