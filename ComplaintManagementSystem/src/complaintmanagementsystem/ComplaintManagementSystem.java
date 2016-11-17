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
        
        //test file 
        reader = new IOFileCSV("data/testcase.csv");
        List<String[]> test = reader.readFile();
        
        //Preproses tweet
        PreprosesTweet pt = new PreprosesTweet();
        for(int i=0; i< tweets.size(); i++) {
            String formalized = pt.preprocessTweet(tweets.get(i)[2]);
            tweets.get(i)[2] = formalized;
        }
         
        //Classify using J48
        ComplaintClassifier classifier = new ComplaintClassifier(tweets);
        classifier.generateTrainData();
        classifier.buildClassifierJ48();
        classifier.printTree();
        classifier.classifyUnseenData(test);
        //classifier.fullTraining();
        //classifier.crossValidate(10);
        classifier.printResult();
    }
    
}
