/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complaintmanagementsystem;

import complaintclassifier.ComplaintClassifier;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



/**
 *
 * @author TOSHIBA PC
 */
public class ComplaintManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        IOFileCSV reader = new IOFileCSV("data/tweets_labelled.csv");
        List<String[]> tweets = reader.readFile();
        
        
        PreprosesTweet pt = new PreprosesTweet();
        for(int i=0; i< tweets.size(); i++) {
            String formalized = pt.preprocessTweet(tweets.get(i)[2]);
            tweets.get(i)[2] = formalized;
        }
       
        
        ComplaintClassifier classifier = new ComplaintClassifier(tweets);
        classifier.classifyCart();
        
        
//        // TODO code application logic here
//        FileReaderCSV reader = new FileReaderCSV();
//        List tweet = reader.readFile("data/tweet_500.csv");
//        
//        //preproses 
//        PreprosesTweet preprosesTweet = new PreprosesTweet();
//        tweet = preprosesTweet.preprocessTweet(tweet);
//        for (int i = 0; i< tweet.size(); i++){
//            System.out.print(tweet.get(i));
//        }
        
        

    }
    
}
