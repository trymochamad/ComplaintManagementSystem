/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicextraction;

import IndonesianNLP.IndonesianSentenceTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author TOSHIBA PC
 */
public class TopicExtraction {
    
    protected IndonesianSentenceTokenizer tokenizer;
    protected Set<String> vocabulary;
    protected double[] prior;
    protected Map<String,double[]> condProb;
    
    // Daftar dinas
    public final static String[] topic = {
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
    
    public TopicExtraction() {
        tokenizer = new IndonesianSentenceTokenizer();
        vocabulary = new HashSet<>();
        prior = new double[12];
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
            prior[i] = (double) numTopic[i] / corpusSize;
            String text = concatTweet(corpus, i);
            Map<String,Integer> counterToken = new HashMap<>();
            for (String token : vocabulary) {
                counterToken.put(token, StringUtils.countMatches(text, token));
            }
            ArrayList<String> tokens = tokenizer.tokenizeSentence(text);
            int textLength = tokens.size();
            for (String token : vocabulary) {
                double[] value;
                if (condProb.containsKey(token)) {
                    value = condProb.get(token);
                } else {
                    value = new double[12];
                    for (int j=0; j<12; j++)
                        value[j] = 0;
                }
                value[i] = (double) (counterToken.get(token) + 1) / (textLength + (vocabulary.size()));
                condProb.put(token, value);
            }
        }
        
        // Save model
        saveModel();
    }
    
    public void loadModel() {
        // Load vocabulary
        ArrayList<String> content = readFile("model/TopicExtraction.vocabulary");
        this.vocabulary.addAll(content);
        content.clear();
        
        // Load prior
        content = readFile("model/TopicExtraction.prior");
        for (int i=0; i<12; i++) {
            this.prior[i] = Double.parseDouble(content.get(i));
        }
        content.clear();
        
        // Load condProb
        content = readFile("model/TopicExtraction.condProb");
        for (int i=0; i<content.size(); i++) {
            String[] strArray = content.get(i).split(" ");
            double[] value = new double[strArray.length-1];
            for (int j=0; j<value.length; j++) {
                value[j] = Double.parseDouble(strArray[j+1]);
            }
            this.condProb.put(strArray[0], value);
        }
        content.clear();
    }
    
    public void saveModel() {
        // Save vocabulary
        StringBuilder vocabBuilder = new StringBuilder();
        for (String token : vocabulary) {
            vocabBuilder.append(token).append("\n");
        }
        writeFile("model/TopicExtraction.vocabulary", vocabBuilder.toString());
        
        // Save prior
        StringBuilder priorBuilder = new StringBuilder();
        for (int i=0; i<prior.length; i++) {
            priorBuilder.append(prior[i]).append("\n");
        }
        writeFile("model/TopicExtraction.prior", priorBuilder.toString());
        
        // Save condProb
        StringBuilder condProbBuilder = new StringBuilder();
        for (Map.Entry<String,double[]> entry : condProb.entrySet()) {
            condProbBuilder.append(entry.getKey()).append(" ");
            double[] value = entry.getValue();
            for (int i=0; i<value.length; i++) {
                condProbBuilder.append(value[i]).append(" ");
            }
            condProbBuilder.append("\n");
        }
        writeFile("model/TopicExtraction.condProb", condProbBuilder.toString());
    }
    
    public ArrayList<String> readFile(String filename) {    
        BufferedReader br = null;
        String line;
        ArrayList<String> content = new ArrayList();

        try {
            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                content.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Currently no model is saved. Build first.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return content;
    }
    
    public void writeFile(String filename, String content) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }

            java.io.FileWriter fw = new java.io.FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String concatTweet(List<String[]> corpus, int topicIdx) {
        StringBuilder result = new StringBuilder();
        for (int i=0; i<corpus.size(); i++) {
            if (corpus.get(i)[5].equals(topic[topicIdx]))
                result.append(corpus.get(i)[2]).append(" ");
        }
        return result.toString();
    }
    
    public String classifyTweet(String tweet) {
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
                if (vocabulary.contains(t))
                    score[i] += Math.log(condProb.get(t)[i]);
            }
            if (score[i] > score[idxMax])
                idxMax = i;
        }
        
        return topic[idxMax];
    }
       
}
