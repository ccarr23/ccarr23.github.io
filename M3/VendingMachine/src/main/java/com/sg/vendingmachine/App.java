/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.ProductDAO;
import com.sg.vendingmachine.dao.ProductDAOFileImpl;
import com.sg.vendingmachine.services.InsufficentFundsException;
import com.sg.vendingmachine.services.ItemNotFoundException;
import com.sg.vendingmachine.services.VendingMachineService;
import com.sg.vendingmachine.services.VendingMachineServiceImpl;
import com.sg.vendingmachine.view.UserIO;
import com.sg.vendingmachine.view.UserIOConsoleImpl;
import com.sg.vendingmachine.view.VendingMachineView;

/**
 *
 * @author corey
 */
public class App {
    
    public static void main(String[] args) throws ItemNotFoundException, InsufficentFundsException {
        ProductDAO dao = new ProductDAOFileImpl();
        VendingMachineService service = new VendingMachineServiceImpl(dao);
        UserIO io = new UserIOConsoleImpl();
        VendingMachineView view = new VendingMachineView(io);
        VendingMachineController controller = new VendingMachineController(view, service);
        
        controller.run();
        
    }
}
