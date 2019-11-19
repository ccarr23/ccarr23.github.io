/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dvdlibrary.view;

import DVDLibrary.DTO.Dvd;
import java.util.List;

/**
 *
 * @author corey
 */
public class DVDLibraryView {
    private UserIO io;
    
    public DVDLibraryView(UserIO io){
        this.io = io;
    }
    
    public int printMenuAndGetLibrary() {
        
           io.print("DVD Library: Main Menu");
           io.print("1. List DVDs");
           io.print("2. Add a DVD");
           io.print("3. Remove a DVD");
           io.print("4. View a DVD");
           io.print("5. Edit a DVD");
           io.print("6. EXIT");
           
           return io.readInt("Please select from the given choices.", 1, 6);
    }
    
    public void displayNewDvdBanner(){
        io.print("=== Input New DVD Info ===");
    }
    
    public void displaySuccess(){
        io.readString("New DVD added to library. Please hit ENTER to continue");
    }
    
    public void displayRemoveDvd() {
        io.print("=== Remove DVD ===");
    }
    
    public void displayRemoveSuccess(Dvd dvd){
        if (dvd != null){
        io.readString("DVD successfully removed. Hit ENTER to continue.");
        } else {
            io.readString("Title doesn't exist. Hit ENTER to continue.");
        }
    }
    
    public void displayEditDvd(){
        io.print("=== Edit DVD ===");
    }
    
    public void displayEditSuccess(){
        io.readString("Edit successful. Please hit ENTER to continue.");
    } 
    
    public void displayLibrary() {
        io.print("===Display DVD Catalog ===");
    }
    
    public void displayTitle(){
        io.print("=== Display Title ===");
    }
    
    public Dvd getNewDvdInfo() {
        String title = io.readString("Enter Title");
        String releaseDate = io.readString("Enter Release Date");
        String mpaaRating = io.readString("Enter MPAA Rating");
        String directorName = io.readString("Enter Director's Name");
        String studio = io.readString("Enter Studio Name");
        String userNote = io.readString("Enter Ratings or Comments");
        
        Dvd currentDvd = new Dvd(title);
        currentDvd.setReleaseDate(releaseDate);
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setUserNote(userNote);
        return currentDvd;
    }
    
    public Dvd editDvdInfo(Dvd dvd) {
        if(dvd == null){
           io.print("Title Not Found.");
        } else {
        
        String title = io.readString("Edit Title");
        String releaseDate = io.readString("Edit Release Date");
        String mpaaRating = io.readString("Edit MPAA Rating");
        String directorName = io.readString("Edit Director's Name");
        String studio = io.readString("Edit Studio Name");
        String userNote = io.readString("Edit Ratings or Comments");
        
        Dvd editDvd = new Dvd(title);
        editDvd.setReleaseDate(releaseDate);
        editDvd.setMpaaRating(mpaaRating);
        editDvd.setDirectorName(directorName);
        editDvd.setStudio(studio);
        editDvd.setUserNote(userNote);
        return editDvd;
        }
        return dvd;
    }
    
    public void displayDvdLibrary(List<Dvd> dvdList){
     for (Dvd currentDvd : dvdList){
         io.print("Title: " + currentDvd.getTitle()
         + " Release Date: " + currentDvd.getReleaseDate() + " | "
         + " MPAA Rating: " + currentDvd.getMpaaRating() + " | "
         + " Director's Name: " + currentDvd.getDirectorName() + " | "
         + " Studio: " + currentDvd.getStudio() + " | "
         + " User Note: " + currentDvd.getUserNote());
     }
     io.readString("Hit ENTER to continue.");
    }
    
    public String getDvd(){
        return io.readString("Please enter the title.");
    }
    
    public void displayDvd(Dvd dvd){
        if(dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getMpaaRating());
            io.print(dvd.getDirectorName());
            io.print(dvd.getStudio());
            io.print(dvd.getUserNote());
        } else {
            io.print("Title doesn't exist.");
        }
        io.readString("Hit ENTER to continue.");
    }
    
    
    public void errorMessage(String errorMsg){
        io.print("***ERROR***");
        io.print(errorMsg);
    }
    

}
