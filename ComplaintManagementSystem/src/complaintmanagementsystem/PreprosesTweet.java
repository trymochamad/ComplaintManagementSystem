/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complaintmanagementsystem;

import IndonesianNLP.IndonesianSentenceFormalization;
import IndonesianNLP.IndonesianSentenceTokenizer;
import IndonesianNLP.IndonesianStemmer;
import java.util.ArrayList;


/**
 *
 * @author mochamadtry
 */

public class PreprosesTweet {
    protected IndonesianSentenceFormalization formalizer; 
    protected IndonesianSentenceTokenizer tokenizer; 
    protected IndonesianStemmer stemmer;
    
    //konstruktor
    public PreprosesTweet(){
        formalizer = new IndonesianSentenceFormalization(); 
        tokenizer = new IndonesianSentenceTokenizer();
        stemmer = new IndonesianStemmer();
        
        formalizer.initStopword();   
    }
    
    public ArrayList preprocessTweet (ArrayList listOfText){
        ArrayList processed = new ArrayList(); 
        for (int i=0; i<listOfText.size(); i++){
            //Formalisasi 
            String text = formalizer.normalizeSentence(listOfText.get(i).toString());
            
            //Hapus semua yang tidak diperlukan 
            text = prosesNoNeed(text.toLowerCase());
            
            //Tokenisasi 
            ArrayList<String> words = tokenizer.tokenizeSentence(text);
            
            //Stemming 
            StringBuilder result = new StringBuilder(); 
            for (String word : words){
                if (word.length() > 1){
                    word = stemmer.stem(word);
                    word = formalizer.formalizeWord(word);
                    result.append(word + " ");
                }
            }
            //Hapus Stop word 
            String finalize = formalizer.deleteStopword(result.toString());
            processed.add(finalize);
            
        }
        
        return processed; 
    }
    
    public String prosesNoNeed (String _text){
    //Menghapus @ridwankamil @pemkotBandung, angka
        String text = _text;
        text = text.replaceAll("[^a-zA-Z\\s]", " ");
        return text;
    }
        
    
    
    
    
}
