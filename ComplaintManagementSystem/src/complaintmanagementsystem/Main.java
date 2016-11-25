/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complaintmanagementsystem;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author TOSHIBA PC
 */
public class Main {
    
    public static void printMainMenu () {
        System.out.println("\n-- MENU --");
        System.out.println("1. Build Model");
        System.out.println("2. Load Model");
        System.out.println("3. Classify Tweet");
        System.out.println("4. Evaluate Model");
        System.out.println("0. Quit");
    }
    
    public static void main (String[] args) throws IOException, Exception {
        Scanner input = new Scanner(System.in);
        ComplaintManagementSystem cms = new ComplaintManagementSystem();
        int option;
        String filename;
        
        System.out.println("---- Welcome To Twitter Complaint Management System ----");
        
        printMainMenu();     
        System.out.print("\n> Your option: ");
        option = input.nextInt();
        input.nextLine();
        
        while(option != 0) {
            if (option == 1) {
                cms.buildModel();
            } else if (option == 2) {
                cms.loadModel();
            } else if (option == 3) {
                System.out.print("Enter csv filename: ");
                filename = input.nextLine();
                cms.classifyTweet(filename);
            } else if (option == 4) {
                System.out.print("Enter csv filename: ");
                filename = input.nextLine();
                cms.evaluateModel(filename);
            }
            printMainMenu();     
            System.out.print("\n> Your option: ");
            option = input.nextInt();
            input.nextLine();
        }
    }

}
