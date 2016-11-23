/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package complaintmanagementsystem;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author mochamadtry
 */
public class IOFileCSV {

    private String filename;
    
    public IOFileCSV(String file) {
        filename = file;
    }
    
    public List readFile() throws FileNotFoundException, IOException {
        CSVReader reader = new CSVReader(new FileReader(filename));
        List myEntries = reader.readAll();
        myEntries.subList(0, 3).clear();
       
        return myEntries;
    }
    
    public void writeFile(List<String> content) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(filename));
        for (int i=0; i<content.size(); i++) {
            String [] record = content.get(i).split(",");
            writer.writeNext(record);
        }
        writer.close();
    }
}
