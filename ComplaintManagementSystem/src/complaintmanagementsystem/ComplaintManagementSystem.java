/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complaintmanagementsystem;

import complaintclassifier.ComplaintClassifier;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import topicclassifier.TopicClassifier;



/**
 *
 * @author TOSHIBA PC
 */
public class ComplaintManagementSystem {
    
    protected ComplaintClassifier complaintClassifier;
    protected TopicClassifier topicClassifier;
    
    public ComplaintManagementSystem() {
        complaintClassifier = new ComplaintClassifier();
        topicClassifier = new TopicClassifier();
    }
    
    public void buildModel() throws IOException, Exception {
        IOFileCSV reader = new IOFileCSV("data/tweets_labelled_dinas.csv");
        List<String[]> tweets = reader.readFile();
        
        // Preproses tweet
        PreprosesTweet pt = new PreprosesTweet();
        for(int i=0; i< tweets.size(); i++) {
            String formalized = pt.preprocessTweet(tweets.get(i)[2]);
            tweets.get(i)[2] = formalized;
        }
         
        // Classify using J48
        complaintClassifier.generateTrainData(tweets);
        complaintClassifier.buildClassifierJ48();
        complaintClassifier.printTree();
        
        topicClassifier.buildModel(tweets);
    }
    
    public void loadModel() throws Exception {
        // Load complaintClassifier
        complaintClassifier.loadModel();
        // Load topicClassifier
        topicClassifier.loadModel();
    }
    
    public void classifyTweet(String filename) throws IOException, Exception {
        IOFileCSV reader = new IOFileCSV(filename);
        List<String[]> tweets = reader.readFile();
        
        List<String[]> result = new ArrayList();
        result.add(new String[] {"Labelled Tweet","",""});
        result.add(new String[] {"","",""});
        result.add(new String[] {"Tweet","Keluhan","Topik"});
        
        for (int i=0; i< tweets.size(); i++) {
            String[] record = new String[3];
            record[0] = tweets.get(i)[0];
            
            String complaint = complaintClassifier.classifyUnseenData(tweets.get(i)[0]);
            
            if(complaint.equals("complaint")) {
                record[1] = "Ya";
                record[2] = topicClassifier.classifyTweet(tweets.get(i)[0]);
                
            } else {
                record[1] = "Tidak";
                record[2] = "bukan keluhan";
            }
            
            result.add(record);
        }
        
        IOFileCSV writer = new IOFileCSV("test/result.csv");
        writer.writeFile(result);
        
    }
    
    public void evaluateModel(String filename) throws IOException, Exception {
        IOFileCSV reader = new IOFileCSV(filename);
        List<String[]> test = reader.readFile();
        
        List<String> topics = Arrays.asList(TopicClassifier.topic);
        topics.add("bukan keluhan");
        
        //Generate topics from test data
        List<String> realTopics = new ArrayList(test.size());
        for(int i=0; i<test.size(); i++){
            realTopics.add(test.get(i)[2]);
        }
        
        //initiate confussion matrix
        int[][] confussionMatrix = new int[topics.size()][topics.size()];
        for(int i=0; i<confussionMatrix.length; i++){
            for(int j=0; j<confussionMatrix[i].length; j++){
                confussionMatrix[i][j] = 0;
            }
        }
        
        int indexBar;
        int indexKol;
        for(int i=0; i<test.size(); i++) {
            String complaint = complaintClassifier.classifyUnseenData(test.get(i)[0]);
            indexBar = topics.indexOf(realTopics.get(i));
            if(complaint.equals("complaint")){
                String topic = topicClassifier.classifyTweet(test.get(i)[0]);
                indexKol = topics.indexOf(topic);
            } else {
                indexKol = topics.indexOf("bukan keluhan");
            }
            confussionMatrix[indexBar][indexKol]++; 
        }
        
        for(int i=0; i<confussionMatrix.length; i++){
            for(int j=0; j<confussionMatrix[i].length; j++){
                System.out.print(confussionMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
}
