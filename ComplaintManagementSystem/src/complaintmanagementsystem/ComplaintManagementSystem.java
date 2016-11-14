/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complaintmanagementsystem;

import java.util.ArrayList;
import java.util.Scanner;



/**
 *
 * @author TOSHIBA PC
 */
public class ComplaintManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        FileReaderCSV reader = new FileReaderCSV();
        ArrayList tweet = reader.readFile("data/tweet_500.csv");
        
        //preproses 
        PreprosesTweet preprosesTweet = new PreprosesTweet();
        tweet = preprosesTweet.preprocessTweet(tweet);
        for (int i = 0; i< tweet.size(); i++){
            System.out.print(tweet.get(i));
        }
        
        

    }
    
}
