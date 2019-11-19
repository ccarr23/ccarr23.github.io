/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdlibrary.controller;

import DVDLibrary.DTO.Dvd;
import dvdlibrary.DAO.DVDLibraryDAO;
import dvdlibrary.DAO.DVDLibraryDAOFileImpl;
import dvdlibrary.DVDLibraryDAOException;
import dvdlibrary.view.DVDLibraryView;
import dvdlibrary.view.UserIO;
import dvdlibrary.view.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author corey
 */
public class DVDLibraryController {
   
   DVDLibraryView view;
   DVDLibraryDAO dao;
   
   private UserIO io = new UserIOConsoleImpl();
   
   public DVDLibraryController(DVDLibraryDAO dao, DVDLibraryView view){
       this.dao = dao;
       this.view = view;
   }
   
   public void run() {
       boolean keepGoing = true;
       int menuSelection = 0;
       
     try {
       while (keepGoing) {

           menuSelection = getLibrary();
           switch (menuSelection) {
               case 1:
                   listLibrary();
                   break;
               case 2:
                   createDvd();
                   break;
               case 3:
                   removeDvd();
                   break;
               case 4:
                   viewDvd();
                   break;
               case 5:
                   updateDvd();
                   break;
               case 6:
                   keepGoing = false;
                   break;
               default:
                   unknownCommand();
           }
       }
       displayExit();
   } catch (DVDLibraryDAOException e){
    view.errorMessage(e.getMessage());
    
   }
}
   
   private int getLibrary() throws DVDLibraryDAOException {
       return view.printMenuAndGetLibrary();
   }
   
   private void createDvd() throws DVDLibraryDAOException{
       view.displayNewDvdBanner();
       Dvd newDvd = view.getNewDvdInfo();
       dao.addDvd(newDvd.getTitle(), newDvd);
       view.displaySuccess();
   }
   
   private void listLibrary() throws DVDLibraryDAOException{
       view.displayLibrary();
       List<Dvd> dvdList = dao.getAllDvds();
       view.displayDvdLibrary(dvdList);
   }
   
   private void viewDvd() throws DVDLibraryDAOException{
       String title = view.getDvd();
       view.displayTitle();
       Dvd dvd = dao.getDvd(title);
       view.displayDvd(dvd);
   }
   
   private void removeDvd() throws DVDLibraryDAOException{
       view.displayRemoveDvd();
       String title = view.getDvd();
       Dvd dvd = dao.getDvd(title);
       Dvd remove = dvd;
       dao.removeDvd(title);
       view.displayRemoveSuccess(remove);
   }
   
   private void updateDvd() throws DVDLibraryDAOException {
       view.displayEditDvd();
       String title = view.getDvd();
       Dvd dvd = dao.getDvd(title);
       Dvd update = dvd;
       dao.removeDvd(title);
       update = view.editDvdInfo(dvd);
       dao.editDvd(update.getTitle(), update);
       
       
       view.displayEditSuccess();
   }
       public void displayExit(){
        io.print("Exiting.");
    }
    
    public void unknownCommand(){
        io.print("Unknown Command");
    }
    
}
