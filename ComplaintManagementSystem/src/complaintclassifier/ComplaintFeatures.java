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
import java.util.List;
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
    protected List<String[]> data;
    protected ArrayList<String> features; 
        
    public ComplaintFeatures(List<String[]> data) {
        this.data = data;
        tokenizer = new IndonesianSentenceTokenizer();
        features = new ArrayList();    
    }
        
        
    public void generateFeatures() throws FileNotFoundException, IOException, Exception{ 
        for(int i=0; i<data.size(); i++) {
            ArrayList<String> token = tokenizer.tokenizeSentence(data.get(i)[2]);
            
            for(int j=0; j<token.size(); j++) {
                if(!features.contains(token.get(j)) && token.get(j).length() > 1) {
                    features.add(token.get(j));
                }
            }
        }              
    }
    
    public void featureSelection(String arffFile) throws FileNotFoundException, IOException, Exception {
        
        BufferedReader reader = new BufferedReader(new FileReader(arffFile));
        Instances data = new Instances(reader);
        reader.close();
        
        data.setClassIndex(data.numAttributes() - 1);
        AttributeSelection selector = new AttributeSelection();
        InfoGainAttributeEval evaluator = new InfoGainAttributeEval();
        Ranker ranker = new Ranker();
        ranker.setNumToSelect(Math.min(2000, data.numAttributes() - 1));
        selector.setEvaluator(evaluator);
        selector.setSearch(ranker);
        selector.SelectAttributes(data);
        
        int selectedAttr[] = selector.selectedAttributes();
        ArrayList<String> result = new ArrayList<>();
        for (int i=0; i<selectedAttr.length-1; i++) {
            result.add(data.attribute(selectedAttr[i]).name());
        }
        
        features = result;
    }
    

    public void writeToArff(String filename, List<String> attributes) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }

            java.io.FileWriter fw = new java.io.FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("@relation complaint\n\n");
            for (int i = 0; i < attributes.size(); i++) {
                bw.write("@attribute " + (String) attributes.get(i) + " {0,1}\n");
            }
            bw.write("@attribute class {complaint,notcomplaint}\n\n@data\n\n");
            
            for(int i=0; i<data.size(); i++){
                for (int j = 0; j < attributes.size(); j++) {
                    if (((String)data.get(i)[2]).contains((String)attributes.get(j))) {
                        bw.write("1,");
                    } else {
                        bw.write("0,");
                    }
                }
                if(data.get(i)[3].equals("Ya")){    
                    bw.write("complaint\n");
                } else {
                    bw.write("notcomplaint\n");
                }
            }
            bw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void writeToArffTestFile(String filename, ArrayList<String> attributes) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }

            java.io.FileWriter fw = new java.io.FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("@relation complaint\n\n");
            for (int i = 0; i < attributes.size(); i++) {
                bw.write("@attribute " + (String) attributes.get(i) + " {0,1}\n");
            }
            bw.write("@attribute class {complaint,notcomplaint}\n\n@data\n\n");
            
            for(int i=0; i<data.size(); i++){
                for (int j = 0; j < attributes.size(); j++) {
                    if (((String)data.get(i)[2]).contains((String)attributes.get(j))) {
                        bw.write("1,");
                    } else {
                        bw.write("0,");
                    }
                }   
                bw.write("?\n");
            }
            bw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<String> getFeatures(){
        return features;
    }
}
