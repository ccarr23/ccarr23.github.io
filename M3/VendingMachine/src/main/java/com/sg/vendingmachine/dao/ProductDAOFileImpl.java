/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dtos.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author corey
 */
public class ProductDAOFileImpl implements ProductDAO {
    
    private final String filename = "inventory.txt";
    private final String DELIMITER = "::";
    
    public Map<String, Product> items = new HashMap<>();
    
private void readFile() throws ProductDAOException{
    
    try {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        
        while(sc.hasNextLine()){
            
        String row = sc.nextLine();
        String[] cols = row.split(DELIMITER);
        Product product = new Product();
        product.setItemId(cols[0]);
        product.setName(cols[1]);
        product.setQty(Integer.parseInt(cols[2]));
        product.setCost(new BigDecimal(cols[3]));
        items.put(product.getItemId(), product);
        
        }
    } catch (FileNotFoundException ex) {
        throw new ProductDAOException("Could not load items.");
    }
}

    private void saveFile() throws ProductDAOException {
        PrintWriter out;
        try {
           out = new PrintWriter(new FileWriter(filename)); 
        } catch (IOException ex) {
            throw new ProductDAOException("Could not save data.");
        }
       List<Product> itemList = this.readAll();
       for(Product products : itemList){
           out.println(products.getItemId() + DELIMITER
           + products.getName() + DELIMITER
           + products.getQty() + DELIMITER
           + products.getCost());
           
           out.flush();
       }
        out.close();
    }

    @Override
    public List<Product> readAll() throws ProductDAOException {
        readFile();
        return new ArrayList<>(items.values());
    }

    @Override
    public Product getItem(String itemId) throws ProductDAOException {
        readFile();
        return items.get(itemId);
    }   

    @Override
    public void updateQty(String itemId, int qty) throws ProductDAOException {
        readFile();
        if(qty < 0){
            throw new ProductDAOException("Inventory must be greater than 0.");
            }
        items.get(itemId).setQty(qty);
        saveFile();
    }
}
