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
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import topicextraction.TopicExtraction;



/**
 *
 * @author TOSHIBA PC
 */
public class ComplaintManagementSystem {
    
    protected ComplaintClassifier complaintClassifier;
    protected TopicExtraction topicExtraction;
    
    public ComplaintManagementSystem() {
        complaintClassifier = new ComplaintClassifier();
        topicExtraction = new TopicExtraction();
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
        
        topicExtraction.buildModel(tweets);
    }
    
    public void loadModel() throws Exception {
        // Load complaintClassifier
        complaintClassifier.loadModel();
        // Load topicClassifier
        topicExtraction.loadModel();
    }
    
    public void classifyTweet(String filename) throws IOException, Exception {
        IOFileCSV reader = new IOFileCSV(filename);
        List<String[]> tweets = reader.readFile();
        
        List<String[]> result = new ArrayList();
        result.add(new String[] {"Labelled Tweet","",""});
        result.add(new String[] {"","",""});
        result.add(new String[] {"Tweet","Keluhan","Dinas"});
        
        for (int i=0; i< tweets.size(); i++) {
            String[] record = new String[3];
            record[0] = tweets.get(i)[0];
            
            String complaint = complaintClassifier.classifyUnseenData(tweets.get(i)[0]);
            
            if(complaint.equals("complaint")) {
                record[1] = "Ya";
                record[2] = topicExtraction.classifyTweet(tweets.get(i)[0]);
                
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
        
        List<String> topics = new LinkedList<String> (Arrays.asList(TopicExtraction.topic));
        //System.out.println(topics);
        topics.add("bukan keluhan");
        
        //Generate topics from test data
        List<String> realTopics = new ArrayList(test.size());
        for(int i=0; i<test.size(); i++){
            realTopics.add(test.get(i)[2]);
            //System.out.println(realTopics.get(i));
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
                String topic = topicExtraction.classifyTweet(test.get(i)[0]);
                indexKol = topics.indexOf(topic);
            } else {
                indexKol = topics.indexOf("bukan keluhan");
            }
            confussionMatrix[indexBar][indexKol]++; 
        }
        
        
        int correct = 0;
        for(int i=0; i<confussionMatrix.length; i++){
            for(int j=0; j<confussionMatrix[i].length; j++){
                if(i==j)
                    correct+=confussionMatrix[i][j];
            }
        }
        //Print Result
        System.out.println("\n===========Confussion Matrix===========\n");
        System.out.println("a\tb\tc\td\te\tf\tg\th\ti\tj\tk\tl\tm <--classified as");
        for(int i=0; i<confussionMatrix.length; i++){
            for(int j=0; j<confussionMatrix[i].length; j++){
                System.out.print(confussionMatrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        for(int i=0; i<topics.size(); i++){
            char index = (char)(i+97);
            System.out.println(index + ": " + topics.get(i));
        }
        
        System.out.println();
        float percent = ((float)correct/(float)test.size()) * 100;
        System.out.println("Correctly Classified Instances:\t\t" + percent + "%");
        percent = 100 - percent;
        System.out.println("Incorrectly Classified Instances:\t" + percent + "%");
    }
    
}
