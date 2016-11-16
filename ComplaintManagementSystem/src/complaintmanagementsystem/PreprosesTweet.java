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
    public IndonesianSentenceFormalization formalizer; 
    public IndonesianSentenceTokenizer tokenizer; 
    public IndonesianStemmer stemmer;
    
    //konstruktor
    public PreprosesTweet(){
        formalizer = new IndonesianSentenceFormalization(); 
        tokenizer = new IndonesianSentenceTokenizer();
        stemmer = new IndonesianStemmer();
        
        formalizer.initStopword();   
    }
    
    public String preprocessTweet (String text){
        String processed = new String(); 
        
        //Formalisasi 
        processed = formalizer.normalizeSentence(text);

        //Hapus semua yang tidak diperlukan 
        processed = prosesNoNeed(text.toLowerCase());

        //Tokenisasi 
        ArrayList<String> words = tokenizer.tokenizeSentence(processed);

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
        processed = finalize;        
        return processed; 
    }
    
    public String prosesNoNeed (String _text){
    //Menghapus @ridwankamil @pemkotBandung, angka
        String text = _text;
        boolean match;
        if (match = text.matches("@")) 
        {
            text = text.replaceAll("@\\w+", "namaMention");
        }
        text = text.replaceAll("[^a-zA-Z\\s]", " ");
        return text;
    }
        
    
    
    
    
}
