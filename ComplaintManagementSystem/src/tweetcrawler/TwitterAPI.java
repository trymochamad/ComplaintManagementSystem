/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tweetcrawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author TOSHIBA PC
 */
public class TwitterAPI {
    private static TwitterFactory tf;
    
    public static void initializeTwitter() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("tZqfO2sBgnOkrV6TS5vf2d9YI")
          .setOAuthConsumerSecret("4x4K4kM6aKiC3Q9gJBjxZFCny1Hk6JlySq6LoEMgNqfIq6G3Os")
          .setOAuthAccessToken("69652133-MRIXAqi7n1fjKgof4yDxGttvKeK47z0vtR7zmNNsB")
          .setOAuthAccessTokenSecret("ibUkFFGqfzKBFjwnR8dtQoJ0m4qV0JOTXbKupKoyTmEaK");
        tf = new TwitterFactory(cb.build());
    }
    
    public static ArrayList<Status> TwitterSearch(String key) {
        ArrayList<Status> tweets = new ArrayList<Status>();
        Twitter twitter = tf.getInstance();
        long lastID = Long.MAX_VALUE;
        for (int i=0; i<5; i++) {
            Query query = new Query(key);
            query.setCount(100);
            try {
                QueryResult result = twitter.search(query);
                tweets.addAll(result.getTweets());
                for (Status t: tweets) 
                    if (t.getId() < lastID) lastID = t.getId();
            } catch (TwitterException te) {}
            query.setMaxId(lastID-1);
            System.out.println("Get 100 tweets");
        }
        
        System.out.println("Saving to file data/tweet.csv...");
        saveToFile(tweets, "data/tweet.csv");
        System.out.println("Done");
        return tweets;
    }
    
    private static void saveToFile(ArrayList<Status> tweets, String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i=0; i<tweets.size(); i++){
                String tweet = tweets.get(i).getText();
                bw.write((String) tweet.replace("\n", " ").replace("\r", " "));
                bw.write("\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
