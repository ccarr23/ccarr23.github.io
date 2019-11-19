/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package summativesums;



/**
 *
 * @author corey
 */
public class SummativeSums {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
    int [] arrOne = { 1, 90, -33, -55, 67, -16, 28, -55, 15 };
    int [] arrTwo = { 999, -60, -77, 14, 160, 301 };
    int [] arrThree = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 
        140, 150, 160, 170, 180, 190, 200, -99 };
      
       int totalOne = addArray(arrOne);
       int totalTwo = addArray(arrTwo);
       int totalThree = addArray(arrThree);
       
       System.out.println("#1 Array Sum: " + totalOne);
       System.out.println("#2 Array Sum: " + totalTwo);
       System.out.println("#3 Array Sum: " + totalThree);
          
       
    }
    
    
    public static int addArray(int[] numbers) {
         
        int sum = 0;
         for(int i = 0; i < numbers.length; i++) {
             sum += numbers[i];
         }
         return sum;     
    }
    
}
