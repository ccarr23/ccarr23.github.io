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
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


/**
 *
 * @author corey
 */
public class FMService {
    
    private OrdersDAOFileImpl odi;
    private ProductDAOFileImpl pdi;
    private TaxDAOFileImpl tdi;
    private Mode mode;
    
    public FMService(OrdersDAOFileImpl odi, ProductDAOFileImpl pdi, TaxDAOFileImpl tdi, Mode mode){
        this.odi = odi;
        this.pdi = pdi;
        this.tdi = tdi;
        this.mode = mode;
  
    }
    
 public Order addOrder(Order order) throws DAOException {
    order = orderSubmit(order);
    return odi.addOrder(order);
 }
 
 public void removeOrder(LocalDate date, int orderNumber) throws DAOException {
    Map<String, List<Order>> orderList = odi.allOrders();
    String orderDate = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
    Order removeOrder;
    removeOrder = odi.removeOrder(date, orderNumber);
    int index = orderList.get(orderDate).indexOf(removeOrder);
    orderList.get(orderDate).remove(index);
 }
 
 public void editOrder(LocalDate date, Order oldOrder, Order newOrder) throws DAOException {
     Map<String, List<Order>> orderList = odi.allOrders();
     String orderDate = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
     int index = orderList.get(orderDate).indexOf(oldOrder);
     
     newOrder = orderSubmit(newOrder);
     
     orderList.get(orderDate).remove(index);
     orderList.get(orderDate).add(newOrder);     
 }
 
 public Order getOrder(LocalDate date, int orderNumber) throws DAOException {
    return odi.getOrder(date, orderNumber);
 }
    
 public List<Order> getOrders(LocalDate date) throws DAOException, InvalidDataException {
    if(validateDate(date) == false){
        throw new InvalidDataException("Not Found.");
    }
    return odi.getOrders(date);
 }
 
 
 
 
 public List<State> getStates() throws DAOException{
     return tdi.getStates();
 }
 
 public List<Product> getProduct() throws DAOException{
     return pdi.getProducts();
 }

 public boolean validateDate(LocalDate date){
    return odi.checkDate(date);
}
 
public void validateState(String state) throws DAOException{
    tdi.getState(state);
}

public void validateProduct(String productType) throws DAOException{
    pdi.getProduct(productType);
}

 private BigDecimal taxToDecimal(Order order){
     return order.getState().getTaxRate().movePointLeft(2);
 }
 
 private BigDecimal revertTax(Order order){
     return order.getState().getTaxRate().movePointRight(2);
 }

 private BigDecimal totalCostMaterial(Order order){
     return order.getArea().multiply(order.getProduct().getCostPerSqFoot());
 }

 private BigDecimal totalCostLabor (Order order){
     return order.getArea().multiply(order.getProduct().getCostLaborPerSqfoot());
 }
 
 
 
 
     public Order newOrder(String name, String state, String productType, BigDecimal area){
       
        Order order = new Order();
        order.setCustomerName(name);
        order.getState().setName(state);
        order.getProduct().setProductType(productType);
        order.setArea(area);
        
        return order;
        
    }
    
    public Order editOrder(Order order, String name, String state, String productType, BigDecimal area) throws DAOException{
        
        Order editOrder = new Order();
        editOrder.setOrderNumber(order.getOrderNumber());
        
        if(name.isEmpty()){
            editOrder.setCustomerName(order.getCustomerName());
        } else {
            editOrder.setCustomerName(name);
        }
        
        if(state.isEmpty()){
            editOrder.getState().setName(order.getState().getName());
        } else {
            editOrder.getState().setName(state);
        }
        
        if(productType.isEmpty()){
            editOrder.getProduct().setProductType(order.getProduct().getProductType());
        } else {
            editOrder.getProduct().setProductType(productType);
        }
        
        if(area.equals(BigDecimal.ZERO)){
            editOrder.setArea(order.getArea());
        } else {
            editOrder.setArea(area);
        }
        
        return editOrder;
        
    }
 
 private BigDecimal totalTax(Order order){
     BigDecimal totalTax = new BigDecimal("0.00");
     
     totalTax = totalTax.add(totalCostMaterial(order));
     totalTax = totalTax.add(totalCostLabor(order));
     totalTax = totalTax.multiply(order.getState().getTaxRate());
     
     return totalTax;
 }
 
 private BigDecimal orderTotal(Order order){
     BigDecimal total = BigDecimal.ZERO;
     
     total = total.add(totalCostMaterial(order));
     total = total.add(totalCostLabor(order));
     total = total.add(totalTax(order));
     
     return total;
 }
 
 private Order orderSubmit(Order order) throws DAOException {
     String stateName = order.getState().getName();
     State state = tdi.getState(stateName);
     order.setState(state);
     
     String productType = order.getProduct().getProductType();
     Product product = pdi.getProduct(productType);
     order.setProduct(product);
     
     order.getState().setTaxRate(taxToDecimal(order));
     order.setMaterialCost(totalCostMaterial(order));
     order.setLaborCost(totalCostLabor(order));
     order.setTotalTax(totalTax(order).setScale(2, RoundingMode.CEILING));
     order.setTotal(orderTotal(order).setScale(2, RoundingMode.CEILING));
     order.getState().setTaxRate(revertTax(order));
     
     return order;
     
 }
 
 public void setMode(String select) throws InvalidDataException {
    mode.setMode(select);         
 }
 
 public void save() throws DAOException {
     if(mode.getMode().equalsIgnoreCase("Training")){
         throw new DAOException("Currently in Training Mode. Data submitted will not be saved.");
     }
     odi.saveFile();
 }

    
}
