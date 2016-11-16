/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complaintclassifier;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zulvafachrina
 */
public class ComplaintClassifier {
   
    List<String[]> data;
    
    public ComplaintClassifier(List<String[]> data) {
        this.data = data;
    }
    
    public void classifyCart() throws Exception{
        ComplaintFeatures cf = new ComplaintFeatures(data);
        cf.generateFeatures();
        cf.writeToArff("data/complainData.arff");
        cf.featureSelection("data/complainData.arff");
        cf.writeToArff("data/complainData.arff");
    }
    
}
