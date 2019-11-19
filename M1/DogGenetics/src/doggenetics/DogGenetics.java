/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doggenetics;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author corey
 */
public class DogGenetics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Random breedRandom = new Random();
        
        Scanner sc = new Scanner(System.in);
        
        String[] dogBreeds = {
            "Boxer", "Bull Terrier", "Pitbull", "Shiba Inu", "Bloodhound"
        };
        
        System.out.println("What's you dogs name?");
        String dogName = sc.nextLine();
        
        System.out.println("Well i have a detailed report on " + dogName + "'s ansestry right here.");
       
       int b1 = breedRandom.nextInt(101);
       int b2 = breedRandom.nextInt(101 - b1);
       int b3 = breedRandom.nextInt(101 - b1 - b2);
       int b4 = breedRandom.nextInt(101 - b1 - b2 - b3);
       int b5 = 100 - b1 - b2 - b3 - b4;
       
       
       if (b1 + b2 + b3 + b4 + b5 == 100) {
           
           System.out.println(dogName + " is:");
           
       System.out.println(b1 + "% " + dogBreeds[0]);
       System.out.println(b2 + "% " + dogBreeds[1]);
       System.out.println(b3 + "% " + dogBreeds[2]);
       System.out.println(b4 + "% " + dogBreeds[3]);
       System.out.println(b5 + "% " + dogBreeds[4]);
       
       System.out.println("That's quite a mix there friend.");
      
       }              
    }
   
}
