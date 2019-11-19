/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dtos.State;
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
public class TaxDAOFileImpl implements TaxDAO {
    
    static final String FILE_NAME = "data" +
            File.separator + "taxes" +
            File.separator + "Taxes.txt";
    
    private static final String DELIMITER = ",";
    
    public List<State> states = new ArrayList<>();
    
    public String getFile(){
        return FILE_NAME;
    }
    
    private void readFile(String fileName) throws DAOException {

         Scanner sc;
         
         try {
             
             sc = new Scanner(new FileReader(fileName));
             sc.nextLine();
             
             while(sc.hasNext()){
                 String row = sc.nextLine();
                 String cols[] = row.split(DELIMITER);
                 State state = new State();
                 
                 state.setName(cols[0]);
                 state.setTaxRate(new BigDecimal(cols[1]));
                 
                 states.add(state);
             }           
             
         } catch (FileNotFoundException ex){
            
         throw new DAOException("File not found. Unable to load.");  
         
        } catch (NumberFormatException ex){
            throw new DAOException("Invalid.");
            
        } catch (IndexOutOfBoundsException ex){
            throw new DAOException("");
        }    
    }
    
    public List<State> getStates() throws DAOException {     
        readFile(FILE_NAME);
        return states;
    }
            

    @Override
    public State getState(String name) throws DAOException {
        readFile(FILE_NAME);
        
        for (int i = 0; i < states.size(); i++){
            if(states.get(i).getName().equalsIgnoreCase(name)){
                return states.get(i);
            }
        }
        
        throw new DAOException("Not Available.");
    }
    
}
