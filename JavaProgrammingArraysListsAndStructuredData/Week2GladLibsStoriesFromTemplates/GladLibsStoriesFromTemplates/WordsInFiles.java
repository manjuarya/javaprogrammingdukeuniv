
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (Manju) 
 * @version (28/08/2019)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> myMap;
    private HashMap<String, ArrayList<String>> myMapOpp;
    private ArrayList<String> allFileData;
    HashMap<String, Integer> uniqueWord; 
    String wordMaxOccure;
    
    public WordsInFiles () {
        myMap = new HashMap<String, ArrayList<String>>();
        myMapOpp = new HashMap<String, ArrayList<String>>();
        allFileData = new ArrayList<String>();
        uniqueWord = new HashMap<String, Integer>();
        wordMaxOccure = "";
    }
    
    //this method for a map in which words are mapped to files
    private void addWordsFromFileOppMap (File f) {
        ArrayList<String> list = new ArrayList<String>();
        String name = f.getName();
        list.add(name);
        FileResource file = new FileResource(f);
        for (String word : file.words()) {
            if(myMapOpp.containsKey(word)) {
                ArrayList<String> value = myMapOpp.get(word);
                if (! value.contains(name)) {
                    value.add(name);
                    myMapOpp.put(word, value);
                }                
                //System.out.println(value.size());
            }
            else {
                myMapOpp.put(word, list);                
            }                    
        } 
        /*for (String s : myMapOpp.keySet()) {
            System.out.println(s + "      " + myMapOpp.get(s));           
        }*/
        //System.out.println(myMapOpp.size());
    }
    
    private void addWordsFromFile (File f) {
        ArrayList<String> list = new ArrayList<String>();
        String name = f.getName();
        FileResource file = new FileResource(f);
        for (String word : file.words()) {
            list.add(word);
            allFileData.add(word);
        }
        myMap.put(name, list);
    }
    
    private void buildWordFileMap () {
        myMap.clear();
        myMapOpp.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile (f);
            addWordsFromFileOppMap (f);
        }
        for (String s : myMap.keySet()) {
            //System.out.println(s + "\t" + myMap.get(s));
        }
        //System.out.println("The all files data is " + allFileData);
        //int number = maxNumber();
        wordsInNumFiles (4);        
    }
    
    private int maxNumber() {
        
        int wordsMaxOccurance = 0;
        
        for (int k=0; k<allFileData.size(); k++) {
            if (!uniqueWord.containsKey(allFileData.get(k))) {
                uniqueWord.put(allFileData.get(k), 1);
            }
            else {
                uniqueWord.put(allFileData.get(k), uniqueWord.get(allFileData.get(k))+1);
            }
        }
        for (String s : uniqueWord.keySet()) {
            System.out.println(s + "\t" + uniqueWord.get(s));
            if ( wordsMaxOccurance < uniqueWord.get(s) ) {
                wordsMaxOccurance = uniqueWord.get(s);
                wordMaxOccure = s;
            }
        }
        System.out.println("The word which is maximum occured is  " + wordMaxOccure + "  & its occurance is  " + wordsMaxOccurance);
        return wordsMaxOccurance;
    }
    
    private void printFilesIn (String word) {
        ArrayList<String> current = new ArrayList<String>();
        ArrayList<String> fileList = new ArrayList<String>();
        for (String label : myMap.keySet()) {              
            current = myMap.get(label);            
            if (current.contains(word)) {
                fileList.add(label);
            }            
        }
        System.out.println("The word  " + word + "  appeared in the files  " + fileList);    
    }    
    
    private void wordsInNumFiles (int number) {
        System.out.println(myMapOpp.size()); 
        ArrayList<String> files = new ArrayList<String>();
        ArrayList<String> words = new ArrayList<String>();
        for (String s : myMapOpp.keySet()) {
            files = myMapOpp.get(s);
            if (files.size() >= number) {
                words.add(s);
            }            
        }
        System.out.println("The total words  " + words.size() + "  occuered in   " + number + "  files");   
    } 
    
    public void tester() {
        buildWordFileMap();
        String word = "tree";
        printFilesIn (word);
    }
}
