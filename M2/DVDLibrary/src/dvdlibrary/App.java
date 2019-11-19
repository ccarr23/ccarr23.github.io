/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdlibrary;

import dvdlibrary.DAO.DVDLibraryDAO;
import dvdlibrary.DAO.DVDLibraryDAOFileImpl;
import dvdlibrary.controller.DVDLibraryController;
import dvdlibrary.view.DVDLibraryView;
import dvdlibrary.view.UserIO;
import dvdlibrary.view.UserIOConsoleImpl;

/**
 *
 * @author corey
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDAO myDao = new DVDLibraryDAOFileImpl();
        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
        
        controller.run();
    }
    
}
