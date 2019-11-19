/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdlibrary.DAO;

import DVDLibrary.DTO.Dvd;
import dvdlibrary.DVDLibraryDAOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author corey
 */
public class DVDLibraryDAOFileImpl implements DVDLibraryDAO {
    
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    
    private Map<String, Dvd> dvds = new HashMap<>();

    @Override
    public Dvd addDvd(String title, Dvd dvd) throws DVDLibraryDAOException {
        loadLibrary();
        Dvd newDvd = dvds.put(title, dvd);
        writeLibrary();
        return newDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws DVDLibraryDAOException{
        loadLibrary();
        return new ArrayList<>(dvds.values());
    }

    @Override
    public Dvd getDvd(String title) throws DVDLibraryDAOException {
        loadLibrary();
        return dvds.get(title);
    }

    @Override
    public Dvd removeDvd(String title) throws DVDLibraryDAOException {
        loadLibrary();
        Dvd removeDvd = dvds.remove(title);
        writeLibrary();
        return removeDvd;
    }
    
    @Override
    public Dvd editDvd(String title, Dvd dvd) throws DVDLibraryDAOException {
       loadLibrary();
       Dvd editDvd = dvds.put(title, dvd);
       writeLibrary();
       return editDvd;    
    }

    private void loadLibrary() throws DVDLibraryDAOException {
        Scanner scanner;
        
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDAOException("Could not load library into memory.", e);
        }
        String currentLine;
        String[] currentTokens;
        dvds.clear();
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Dvd currentDvd = new Dvd(currentTokens[0]);
            currentDvd.setReleaseDate(currentTokens[1]);
            currentDvd.setMpaaRating(currentTokens[2]);
            currentDvd.setDirectorName(currentTokens[3]);
            currentDvd.setStudio(currentTokens[4]);
            currentDvd.setUserNote(currentTokens[5]);
            
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        scanner.close();
    }
    
    
    private void writeLibrary() throws DVDLibraryDAOException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e){
            throw new DVDLibraryDAOException("Could not save data.", e);
        }
        
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList){
            out.println(currentDvd.getTitle() + DELIMITER
            + currentDvd.getReleaseDate() + DELIMITER
            + currentDvd.getMpaaRating() + DELIMITER
            + currentDvd.getDirectorName() + DELIMITER
            + currentDvd.getStudio() + DELIMITER
            + currentDvd.getUserNote());
            
            out.flush();
        }
        out.close();
    }
}
