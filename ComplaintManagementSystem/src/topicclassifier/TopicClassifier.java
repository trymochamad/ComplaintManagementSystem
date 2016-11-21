/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicclassifier;

import IndonesianNLP.IndonesianSentenceTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author TOSHIBA PC
 */
public class TopicClassifier {
    
    protected IndonesianSentenceTokenizer tokenizer;
    protected Set<String> vocabulary;
    protected int[] prior;
    protected  Map<String,double[]> condProb;
    
    // Daftar dinas
    final static String[] topic = {
        "Dinas Kependudukan dan Pencatatan Sipil",
        "Dinas Tata Ruang dan Ciptakarya",
        "Dinas Kebudayaan dan Pariwisata",
        "Dinas Kesehatan",
        "Dinas Tenaga Kerja",
        "Dinas Sosial",
        "Dinas Pemakaman dan Pertamanan",
        "Dinas Bina Marga dan Pengairan",
        "Badan Kepegawaian Daerah",
        "Dinas Perhubungan",
        "Badan Pelayanan Perizinan Terpadu",
        "Lain-Lain"
    };
    
    public TopicClassifier() {
        tokenizer = new IndonesianSentenceTokenizer();
        vocabulary = new HashSet<>();
        prior = new int[12];
        condProb = new HashMap<>();
    }
    
    public void buildModel(List<String[]> data) {
        List<String[]> corpus = data;
        
        // Extract vocabulary
        for (int i=0; i<corpus.size(); i++) {
            ArrayList<String> tokens = tokenizer.tokenizeSentence(corpus.get(i)[2]);
            vocabulary.addAll(tokens);
        }
        
        // Count topic
        int[] numTopic = new int[12];
        int corpusSize = corpus.size();
        for (int i=0; i<12; i++) {
            numTopic[i] = 0;
        }
        for (int i=0; i<corpus.size(); i++) {
            if (corpus.get(i)[5].equals(topic[0]))
                numTopic[0]++;
            else if (corpus.get(i)[5].equals(topic[1]))
                numTopic[1]++;
            else if (corpus.get(i)[5].equals(topic[2]))
                numTopic[2]++;
            else if (corpus.get(i)[5].equals(topic[3]))
                numTopic[3]++;
            else if (corpus.get(i)[5].equals(topic[4]))
                numTopic[4]++;
            else if (corpus.get(i)[5].equals(topic[5]))
                numTopic[5]++;
            else if (corpus.get(i)[5].equals(topic[6]))
                numTopic[6]++;
            else if (corpus.get(i)[5].equals(topic[7]))
                numTopic[7]++;
            else if (corpus.get(i)[5].equals(topic[8]))
                numTopic[8]++;
            else if (corpus.get(i)[5].equals(topic[9]))
                numTopic[9]++;
            else if (corpus.get(i)[5].equals(topic[10]))
                numTopic[10]++;
            else if (corpus.get(i)[5].equals(topic[11]))
                numTopic[11]++;
            else
                corpusSize--;
        }
        
        // Calculate prior and condProb
        for (int i=0; i<12; i++) {
            prior[i] = numTopic[i] / corpusSize;
            String text = concatTweet(corpus, i);
            Map<String,Integer> counterToken = new HashMap<>();
            for (String token : vocabulary) {
                counterToken.put(token, countOccurence(text, token));
            }
            for (String token : vocabulary) {
                double[] value;
                if (condProb.containsKey(token)) {
                    value = condProb.get(token);
                } else {
                    value = new double[12];
                    for (int j=0; j<12; j++)
                        value[j] = 0;
                }
                value[i] = (counterToken.get(token) + 1) / (vocabulary.size());
                condProb.put(token, value);
            }
        }
        
        // Save model
        saveModel();
    }
    
    public void loadModel(String filename) {
        
    }
    
    public void saveModel() {
        
    }
    
    public String concatTweet(List<String[]> corpus, int topicIdx) {
        StringBuilder result = new StringBuilder();
        for (int i=0; i<corpus.size(); i++) {
            if (corpus.get(i)[5].equals(topic[topicIdx]))
                result.append(corpus.get(i)[2] + " ");
        }
        return result.toString();
    }
    
    public int countOccurence(String text, String token) {
        return 0;
    }
    
    public void classifyTweet(String tweet) {
        // Extract unique token
        ArrayList<String> tokens = tokenizer.tokenizeSentence(tweet);
        Set<String> temp = new HashSet<>();
        temp.addAll(tokens);
        tokens.clear();
        tokens.addAll(temp);
        
        double[] score = new double[12];
        int idxMax = 0;
        for (int i=0; i<12; i++) {
            score[i] = Math.log(prior[i]);
            for (String t : tokens) {
                score[i] += Math.log(condProb.get(t)[i]);
            }
            if (score[i] > score[idxMax])
                idxMax = i;
        }
        
        System.out.println("Tweet untuk " + topic[idxMax]);
    }
    
    
}
