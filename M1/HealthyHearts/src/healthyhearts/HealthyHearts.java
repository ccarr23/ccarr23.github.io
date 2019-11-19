/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthyhearts;

import java.util.Scanner;

/**
 *
 * @author corey
 */
public class HealthyHearts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner ageInput = new Scanner(System.in);
        
        int maxRate;
        double targetRate1 = 0.5;
        double targetRate2 = 0.85;
        double zoneOne;
        double zoneTwo;
        int age;
        
        System.out.println("Enter your age.");
        age = ageInput.nextInt();
        
        maxRate = 220 - age;
        zoneOne = maxRate * targetRate1;
        zoneTwo = maxRate * targetRate2;
        
        
        System.out.println("Your maximum heart rate should be " + maxRate + " beats per minute.");
        System.out.println("Your target HR zone is " + zoneOne + " - " + zoneTwo + " beats per minute.");
        
        
            
        
        
    }
    
}
