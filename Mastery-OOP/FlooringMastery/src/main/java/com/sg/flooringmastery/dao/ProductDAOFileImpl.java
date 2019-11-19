/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dtos.Product;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author corey
 */
public class ProductDAOFileImpl implements ProductDAO {
    
    private static final String DELIMITER = ",";
    static final String FILE_NAME = "data" +
            File.separator + "products" +
            File.separator + "products.txt";
    
    public List<Product> products = new ArrayList<>();
    
    public String getFile(){
        return FILE_NAME;
    }
     
    private void readFile(String fileName) throws DAOException {
        
        Scanner sc;
        
        try {
            sc = new Scanner(new FileReader(fileName));
            sc.nextLine();
            
            
            while (sc.hasNext()){
                String row = sc.nextLine();
                String[] cols = row.split(DELIMITER);
                Product product = new Product();
                product.setProductType(cols[0]);
                product.setCostPerSqFoot(new BigDecimal(cols[1]));
                product.setCostLaborPerSqfoot(new BigDecimal(cols[2]));
                
                products.add(product);
                
                          
            }
           sc.close();
        } catch (FileNotFoundException ex){   
         throw new DAOException("File not found. Unable to load.");  
         
        } catch (NumberFormatException ex){
            throw new DAOException("Invalid.");
            
        } catch (IndexOutOfBoundsException ex){
            throw new DAOException("");
        }            
    }

    @Override
    public Product getProduct(String product) throws DAOException {
       readFile(FILE_NAME);
       
       for (int i = 0; i < products.size(); i++) {
           if (products.get(i).getProductType().equalsIgnoreCase(product)){
               return products.get(i);
           }
       }
       throw new DAOException("Selection doesn't exist.");
    }
    
    public List<Product> getProducts() throws DAOException {
       products.clear();
       readFile(FILE_NAME);
       return products;
    }
    
}
