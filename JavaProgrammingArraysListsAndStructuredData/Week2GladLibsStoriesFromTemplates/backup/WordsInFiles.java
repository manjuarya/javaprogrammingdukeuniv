
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
    private ArrayList<String> allFileData;
    HashMap<String, Integer> uniqueWord; 
    String wordMaxOccure;
    
    public WordsInFiles () {
        myMap = new HashMap<String, ArrayList<String>>();
        allFileData = new ArrayList<String>();
        uniqueWord = new HashMap<String, Integer>();
        wordMaxOccure = "red";
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
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile (f);
        }
        for (String s : myMap.keySet()) {
            System.out.println(s + "\t" + myMap.get(s));
        }
        //System.out.println("The all files data is " + allFileData);
        int number = maxNumber();
        wordsInNumFiles (7);        
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
    
    private void printFilesIn () {
        ArrayList<String> current = new ArrayList<String>();
        ArrayList<String> fileList = new ArrayList<String>();
        for (String label : myMap.keySet()) {              
            current = myMap.get(label);            
            if (current.contains(wordMaxOccure)) {
                fileList.add(label);
            }            
        }
        System.out.println("The most occurance word is  " + wordMaxOccure + "  & it occured in file  " + fileList);    
    }    
    
    private void wordsInNumFiles (int number) {
        ArrayList<String> words = new ArrayList<String>();
        for (String s : uniqueWord.keySet()) {
            int i = uniqueWord.get(s);
            if (i == number) {
                words.add(s);
            }
        }
        System.out.println("The total words  " + words.size() + words + "  occuered in   " + number + "  files");   
    }
    
    public void tester() {
        buildWordFileMap();
        printFilesIn ();
    }
}
