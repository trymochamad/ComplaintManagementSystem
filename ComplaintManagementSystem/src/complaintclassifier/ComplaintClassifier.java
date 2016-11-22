/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complaintclassifier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author zulvafachrina
 */
public class ComplaintClassifier {
   
    protected List<String[]> data;
    protected Instances train;
    protected Classifier classifier;
    protected Evaluation eval;
    protected ArrayList<String> features; 
    
    public ComplaintClassifier() { }
    
    public void generateTrainData(List<String[]> list) throws Exception {
        data = list;
        
        //Temporary 3000 tweets, because 7000 so huge
        data = data.subList(0, 3000);  
        
        ComplaintFeatures cf = new ComplaintFeatures(data);
        cf.generateFeatures();
        features = cf.getFeatures();
        cf.writeToArff("data/complainData.arff", features);
        cf.featureSelection("data/complainData.arff");
        cf.writeToArff("data/complainData.arff", features);
        
        BufferedReader reader = new BufferedReader(new FileReader("data/complainData.arff"));
        train = new Instances(reader);
        train.setClassIndex(train.numAttributes() - 1);
        reader.close();
    }
    
    public void buildClassifierJ48() throws Exception {
        classifier = new J48();    
        classifier.buildClassifier(train);   
    }
    
    public void printResult() throws Exception {
        System.out.println(eval.toSummaryString("\nSummary\n======\n", false));   
        System.out.println(eval.toClassDetailsString("\nStatistic\n======\n"));
        System.out.println(eval.toMatrixString("\nConfusion Matrix\n======\n"));
    }
    
    public void printTree() {
        System.out.println(classifier.toString());
    }
    
    public void fullTraining() throws Exception {
        eval = new Evaluation(train);
        eval.evaluateModel(classifier, train);   
    }    
    
    public void crossValidate(int folds) throws Exception {
        eval = new Evaluation(train);
        eval.crossValidateModel(classifier, train, folds, new Random(1));     
    }
    
    public void classifyUnseenData(List<String[]> data) throws IOException, Exception {
        ComplaintFeatures cf = new ComplaintFeatures(data);
        cf.writeToArff("data/complaintTest.arff", features);
        
        BufferedReader reader = new BufferedReader(new FileReader("data/complaintTest.arff"));
        Instances test = new Instances(reader);
        test.setClassIndex(test.numAttributes() - 1);
        reader.close();
        
        eval = new Evaluation(train);
        eval.evaluateModel(classifier, test);       
    }
}
