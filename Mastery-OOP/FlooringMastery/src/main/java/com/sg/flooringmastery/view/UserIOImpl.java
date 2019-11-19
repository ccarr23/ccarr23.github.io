/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.view;

import java.util.Scanner;

/**
 *
 * @author corey
 */
public class UserIOImpl implements UserIO {
    
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

//    @Override
//    public double readDouble(String prompt) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public double readDouble(String prompt, double min, double max) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public float readFloat(String prompt) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public float readFloat(String prompt, float min, float max) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }


    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return Integer.parseInt(input);
    }
    
    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return Integer.parseInt(input);
    }

//    @Override
//    public long readLong(String prompt) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

//    @Override
//    public long readLong(String prompt, long min, long max) {
//        System.out.println(prompt);
//       
//    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
}
    
}
