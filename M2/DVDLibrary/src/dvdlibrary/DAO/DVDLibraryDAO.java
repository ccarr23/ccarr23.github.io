/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdlibrary.DAO;

import DVDLibrary.DTO.Dvd;
import dvdlibrary.DVDLibraryDAOException;
import java.util.List;

/**
 *
 * @author corey
 */
public interface DVDLibraryDAO {
    
    Dvd addDvd(String title, Dvd dvd) throws DVDLibraryDAOException;
    
    List<Dvd> getAllDvds() throws DVDLibraryDAOException;
    
    Dvd getDvd(String title) throws DVDLibraryDAOException;
    
    Dvd removeDvd(String title) throws DVDLibraryDAOException;
    
    Dvd editDvd(String title, Dvd dvd) throws DVDLibraryDAOException;
    
    
}
