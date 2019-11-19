/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dtos.Order;
import com.sg.flooringmastery.dtos.Product;
import com.sg.flooringmastery.dtos.State;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 *
 * @author corey
 */
public class OrdersDAOFileImpl implements OrdersDAO {
    
    private static final String currentDate = LocalDateTime.now()
            .toLocalDate()
            .format(DateTimeFormatter.ofPattern("MMddyyyy"));
    
    static final String FILE_CURRENT_DATE = "data"
            + File.separator + "orders"
            + File.separator + "Orders_" + currentDate + ".txt";
    
    private static final String DELIMITER = ",";
    
    private Map<String, List<Order>> allOrders = new TreeMap<>();
    private List<Order> currentOrders = new ArrayList<>();
    
    
    
    private String dateToString(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
    }
    
    private String createFile(String date){
        String main = "data"
                + File.separator + "orders"
                + File.separator + "Orders_";
        String keyDate = date;
        String fileExt = ".txt";
        
        return main + keyDate + fileExt;
    }
    
   // converting date of file created(MMDDYYYY) into fileName
    private String fileNameToDate(String fileName){
        int position = fileName.indexOf('_') + 1;
        return fileName.substring(position, position + 8);
    }
    
    
        private void readFile(String fileName) throws DAOException {
       
        Scanner sc;
        File file = new File(fileName);
        List<Order> orders = new ArrayList<>();
        String orderDate = fileNameToDate(fileName);
        
        if (file.exists() == false){
            return;
        }
        
        try {
            sc = new Scanner(new FileReader(fileName));
            sc.nextLine();

            
            
            while (sc.hasNext()) {
                String row = sc.nextLine();
                String[] cols = row.split(DELIMITER);
                
                Order order = new Order();
                Product product = new Product();
                State state = new State();
                
                order.setOrderNumber(Integer.parseInt(cols[0]));
                order.setCustomerName(cols[1]);
                state.setName(cols[2]);
                state.setTaxRate(new BigDecimal(cols[3]));
                product.setProductType(cols[4]);
                order.setArea(new BigDecimal(cols[5]));
                product.setCostPerSqFoot(new BigDecimal(cols[6]));
                product.setCostLaborPerSqfoot(new BigDecimal(cols[7]));
                order.setMaterialCost(new BigDecimal(cols[8]));
                order.setLaborCost(new BigDecimal(cols[9]));
                order.setTotalTax(new BigDecimal(cols[10]));
                order.setTotal(new BigDecimal(cols[11]));
                
                order.setState(state);
                order.setProduct(product);
                orders.add(order);
                allOrders.put(orderDate, orders);
                
//                if(order.getOrderNumber() <= 1){
//                    allOrders.put(orderDate, orders);
//                }
//                    if(order.getOrderNumber() != 1){
//                    order.setOrderNumber(Integer.parseInt(cols[0] + 1));
//                    allOrders.put(orderDate, orders);
//                    }
            }
            
            sc.close();
            
        } catch (FileNotFoundException ex) {
            throw new DAOException("Could not load file.");
        }
          catch (NumberFormatException ex){
            throw new DAOException("Error.");
        }
          catch (IndexOutOfBoundsException ex){
             throw new DAOException("Not found.");
          }
        }

       public void saveFile() throws DAOException {
        
        PrintWriter out;
        String fileName = "";
        
        try {
            for (String key : allOrders.keySet()){
                
                fileName = createFile(key);
                out = new PrintWriter(new File(fileName));
                out.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
                
                for (Order order : allOrders.get(key)){ 
                    String currentLine = order.getOrderNumber() +
                            DELIMITER + order.getCustomerName() +
                            DELIMITER + order.getState().getName() +
                            DELIMITER + order.getState().getTaxRate() +
                            DELIMITER + order.getProduct().getProductType() +
                            DELIMITER + order.getArea() +
                            DELIMITER + order.getProduct().getCostPerSqFoot() +
                            DELIMITER + order.getProduct().getCostLaborPerSqfoot() +
                            DELIMITER + order.getMaterialCost() +
                            DELIMITER + order.getLaborCost() +
                            DELIMITER + order.getTotalTax() +
                            DELIMITER + order.getTotal();
                    
                    out.println(currentLine);
                    out.flush();
                }
            }
        } catch (IOException ex){
            throw new DAOException ("Could not save data. ");
        } 
    }
       
      public List<Order> getCurrentOrder() {
      return currentOrders;
     }
      
      public Map<String, List<Order>> allOrders() {
          return allOrders;
      }
       
      public boolean checkDate(LocalDate date){
      List<String> fileList = new ArrayList<>();
      File folder = new File("data" + File.separator + "orders");
      File[] files = folder.listFiles();
      
      for (File file : files){
          fileList.add(file.getName());
      }
      
      List<LocalDate> listDates = fileList.stream().map((i) -> i.substring(i.indexOf("_") + 1, i.indexOf(".")))
              .map((i) -> LocalDate.parse(i, DateTimeFormatter.ofPattern("MMddyyyy")))
              .collect(Collectors.toList());
      if (listDates.stream().anyMatch(d -> d.equals(date))){
          return true;
      }
      
      return date.equals(LocalDate.now()) && getCurrentOrder().size() > 0;
    }

    @Override
    public Order addOrder(Order order) throws DAOException {
        String keyDate = fileNameToDate(FILE_CURRENT_DATE);
        readFile(FILE_CURRENT_DATE);
        
        currentOrders.add(order);
        allOrders.put(keyDate, currentOrders);
        
        int orderNumber = allOrders.get(keyDate).size();
        
        order.setOrderNumber(orderNumber);

        return order;
    }

    @Override
    public Order removeOrder(LocalDate date, int orderNumber) throws DAOException {
        String keyDate = dateToString(date);
        readFile(createFile(keyDate));
        
        for (Order order : allOrders.get(keyDate)) {
            if (order.getOrderNumber() == orderNumber) {               
               return order;
            }
        }
        throw new DAOException("Order doesn't Exist");
    }

    @Override
    public Order getOrder(LocalDate date, int orderNumber) throws DAOException {
        String keyDate = dateToString(date);
        readFile(createFile(keyDate));
              
        Optional <Order> search = getOrders(date).stream()
                .filter(i -> !i.getCustomerName().contains("null"))
                .filter(i -> i.getOrderNumber() == orderNumber)
                .findFirst();
        
        Order order = search.get();
        
        if (order != null){
            return order;
        }
        throw new DAOException("Order not found.");
    }

    @Override
    public List<Order> getOrders(LocalDate date) throws DAOException {
        String keyDate = dateToString(date);
        readFile(createFile(keyDate));
        
        List<Order> getAll = allOrders.get(keyDate).stream()
                .filter(i -> !i.getCustomerName().contains("null"))
                .collect(Collectors.toList());
        
        return getAll;  
    }
    

    
    
    
}
