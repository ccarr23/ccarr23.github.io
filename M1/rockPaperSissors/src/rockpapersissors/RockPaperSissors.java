/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rockpapersissors;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author corey
 */
public class RockPaperSissors {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
      int playerInput;
      int computerInput;
     
      boolean playAgain = true;
     
      Random comp = new Random();
     
      
      int rock = 1;
      int paper = 2;
      int sissors = 3;
      
      int playerScore = 0;
      int cpuScore = 0;
      int tie = 0;
      int rounds;
      int player;
      
      int inpRounds = Integer.parseInt(prompt("How Many Rounds?(max: 10)"));
      
      rounds = 1;
      
      if (inpRounds <= 10){
          display("Round STARTO!");
      } else {
          display("ERROR! Invalid Input.");
      }
      
     while (rounds <= inpRounds && inpRounds > 0 && inpRounds <= 10){
         
        
        playerInput = Integer.parseInt(prompt("Rock(1), Paper(2) or Sissors(3)?"));
        computerInput = comp.nextInt(3) + 1;
        System.out.println(computerInput);
        
        if (playerInput > 3 || playerInput < 1) {
            display("Invalid Entry.");
            break;
        } else { 
                
            if (playerInput == computerInput){
            display("Tie.");
            tie+=1;
            rounds++;
            display("Tie Count: " + tie + ", Player Score: " + playerScore + ", CPU Score: " + cpuScore);
          } else if (playerInput == rock) {
              if (computerInput == paper) {
                  display("You played: Rock. CPU played: Paper. CPU Wins Round. ");
                  cpuScore+=1;
                  display("CPU Score: " + cpuScore);
              } else if (computerInput == sissors){
                    display("You Played: Rock. CPU Played: Sissors. You Win Round! ");
                    playerScore+=1;
                    display("User Score: " + playerScore);
              }
          }
            if (playerInput == paper){
               if (computerInput == sissors){
                   display("You played: Paper. CPU Played: Sissors. CPU Wins Round. ");
                   cpuScore+=1;
                   display("CPU Score: " + cpuScore);
               } else if (computerInput == rock){
                   display("You Played: Paper. CPU Played: Rock. You Win Round!");
                   playerScore+=1;
                   display("User Score: " + playerScore);
               }
            }
            
               if (playerInput == sissors){
               if (computerInput == rock){
                   display("You played: Sissors. CPU Played: Rock. CPU Wins Round. ");
                   cpuScore+=1;
                   display("CPU Score: " + cpuScore);
               } else if (computerInput == paper){
                   display("You Played: Sissors. CPU Played: Paper. You Win Round!");
                   playerScore+=1;
                   display("User Score: " + playerScore);
               }
            }
                if (playerScore == inpRounds){
                    display("You Win! ");
                    display("User Score: " + playerScore + ", CPU Score: " + cpuScore + ", Tie rounds: " + tie);
                    if ("y".equalsIgnoreCase(prompt("Play Again? (y/n)"))){
                       playAgain = true;
                       cpuScore = 0;
                       playerScore = 0;
                       inpRounds = 0;
                       rounds =0;
                       inpRounds = Integer.parseInt(prompt("How Many Rounds?(max: 10)"));
                    } else {
                        display("Game Over");
                        break;
                    }
                } else if (cpuScore == inpRounds){
                    display("You Lose. :( ");
                    display("User Score: " + playerScore + ", CPU Score: " + cpuScore + ", Tie rounds: " + tie);
                       if ("y".equalsIgnoreCase(prompt("Play Again? (y/n)"))){
                       playAgain = true;
                       cpuScore = 0;
                       playerScore = 0;
                       inpRounds = 0;
                       rounds = 0;
                       inpRounds = Integer.parseInt(prompt("How Many Rounds?(max: 10)"));
                    } else {
                        display("Game Over");
                        break;  
                       }
                       if (rounds == inpRounds || tie == inpRounds){
                          display("Game Over");
                          display("User Score: " + playerScore + ", CPU Score: " + cpuScore + ", Tie rounds: " + tie);
                            if ("y".equalsIgnoreCase(prompt("Play Again? (y/n)"))){
                       playAgain = true;
                       cpuScore = 0;
                       playerScore = 0;
                       inpRounds = 0;
                       rounds = 0;
                       inpRounds = Integer.parseInt(prompt("How Many Rounds?(max: 10)"));
                    } 
                       }
                }
         }
        

     

        
      }         
    }
        public static void display(String message) {
        System.out.println(message);

    }

    public static String prompt(String message) {
        Scanner sc = new Scanner(System.in);
        display(message);
        return sc.nextLine();

    }
}
