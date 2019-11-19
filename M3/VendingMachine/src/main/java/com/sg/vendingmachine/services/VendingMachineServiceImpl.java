/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.services;

import com.sg.vendingmachine.dao.ProductDAO;
import com.sg.vendingmachine.dao.ProductDAOException;
import com.sg.vendingmachine.dtos.Change;
import com.sg.vendingmachine.dtos.Product;
import com.sg.vendingmachine.view.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;


/**
 *
 * @author corey
 */
public class VendingMachineServiceImpl implements VendingMachineService {
    
    private ProductDAO dao;
    private BigDecimal balance;
    
    public VendingMachineServiceImpl(ProductDAO dao){
        this.dao = dao;
        balance = new BigDecimal("0.00");
  
    }
    

    @Override
    public void addMoney(BigDecimal money) {
     balance = balance.add(money);
    }

    @Override
    public Product selectItem(String itemId) throws ProductDAOException, ItemNotFoundException {
        Product item = dao.getItem(itemId); 
        if(item == null) {
            throw new ProductDAOException("Item does not exist.");
        } 
        System.out.println("You Selected: " + item.getName() + "  $" + item.getCost());
        return item;
    }
 
    @Override
    public Change buyItem(String itemId) throws ProductDAOException, InsufficentFundsException, ItemNotFoundException {
        Product item = dao.getItem(itemId);
        Change change = new Change(balance, item.getCost());
        if(item.getCost().compareTo(balance) > 0){
            throw new InsufficentFundsException("Insufficent Funds.");
        } else {
        if(item.getQty() <= 0) {
            throw new ItemNotFoundException("Item out of stock.");
        }
        System.out.println("You bought: " + item.getName());
        if(item.getQty() < 0){
            throw new ProductDAOException("Inventory must be greater than 0.");
        } else {
        int newQty = item.getQty() - 1;
        dao.updateQty(itemId, newQty);
        }
      System.out.println("Here's your change: ");
      if(change.getQuarters() > 0){
         change.getQuarters();
      }
      if(change.getDimes() > 0){
         change.getDimes();
      }
      if(change.getNickles() > 0){
         change.getNickles();
      }
      if(change.getPennies() > 0){
         change.getPennies();
      }
        balance = new BigDecimal("0.00");
//        System.exit(0);
        } 
        return change;
    }
    

    @Override
    public List<Product> viewItems() throws ProductDAOException {
        return dao.readAll();
    }

}
