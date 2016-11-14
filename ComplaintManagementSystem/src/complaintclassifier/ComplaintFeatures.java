/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complaintclassifier;
        
import IndonesianNLP.IndonesianSentenceTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.core.Instances;

/**
 *
 * @author zulvafachrina
 */
public class ComplaintFeatures {
    
        protected IndonesianSentenceTokenizer tokenizer;
        
        public ComplaintFeatures() {
            tokenizer = new IndonesianSentenceTokenizer();
        }
        
        
        public ArrayList<String> generateFeatures(ArrayList<String> spam, ArrayList<String> notSpam) throws FileNotFoundException, IOException, Exception{
        Map<String,Integer[]> counter = new HashMap<>();
        ArrayList<String> result = new ArrayList<>();
        
        for(int i=0; i<spam.size(); i++) {
            ArrayList<String> token = tokenizer.tokenizeSentence(spam.get(i));           
            
            for(int j=0; j< token.size(); j++) {
                if(!counter.containsKey(token.get(j))) {
                    Integer[] temp = {1,0}; 
                    counter.put(token.get(j),temp);
                } else {
                    Integer[] temp = counter.get(token.get(j));
                    temp[0]++;
                    counter.put(token.get(j), temp);
                }
            }
        }
        
        for(int i=0; i<notSpam.size(); i++) {
            ArrayList<String> token = tokenizer.tokenizeSentence(notSpam.get(i));
            
            for(int j=0; j< token.size(); j++) {
                if(!counter.containsKey(token.get(j))) {
                    Integer[] temp = {0,1}; 
                    counter.put(token.get(j),temp);
                } else {
                    Integer[] temp = counter.get(token.get(j));
                    temp[1]++;
                    counter.put(token.get(j), temp);
                }
            }
        }
        
        result.addAll(counter.keySet());                
        return result;
    }
    
    public ArrayList<String> featureSelection(String arffFile) throws FileNotFoundException, IOException, Exception {
        
        BufferedReader reader = new BufferedReader(new FileReader(arffFile));
        Instances data = new Instances(reader);
        reader.close();
        
        data.setClassIndex(data.numAttributes() - 1);
        AttributeSelection selector = new AttributeSelection();
        InfoGainAttributeEval evaluator = new InfoGainAttributeEval();
        Ranker ranker = new Ranker();
        ranker.setNumToSelect(Math.min(1000, data.numAttributes() - 1));
        selector.setEvaluator(evaluator);
        selector.setSearch(ranker);
        selector.SelectAttributes(data);
        
        int selectedAttr[] = selector.selectedAttributes();
        ArrayList<String> result = new ArrayList<>();
        for (int i=0; i<selectedAttr.length-1; i++) {
            result.add(data.attribute(selectedAttr[i]).name());
        }
        
        return result;
    }
    

    public void writeToArff(ArrayList<String> attribute, ArrayList spam, ArrayList notSpam, String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }

            java.io.FileWriter fw = new java.io.FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("@relation spam\n\n");
            for (int i = 0; i < attribute.size(); i++) {
                bw.write("@attribute " + (String) attribute.get(i) + " {0,1}\n");
            }
            bw.write("@attribute class {spam,notspam}\n\n@data\n\n");
            for (int i = 0; i < spam.size(); i++) {
                for (int j = 0; j < attribute.size(); j++) {
                    if (((String)spam.get(i)).contains((String)attribute.get(j))) {
                        bw.write("1,");
                    } else {
                        bw.write("0,");
                    }
                }
                bw.write("spam\n");
            }
            for (int i = 0; i < notSpam.size(); i++) {
                for (int j = 0; j < attribute.size(); j++) {
                    if (((String)notSpam.get(i)).contains((String)attribute.get(j))) {
                        bw.write("1,");
                    } else {
                        bw.write("0,");
                    }
                }
                bw.write("notspam\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}