
/**
 * Write a description of GladLibMap here.
 * 
 * @author (Manju) 
 * @version (28/08/2019)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
        
    private Random myRandom;
    
    private String dataSourceDirectory = "Data";
    private String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    
    public GladLibMap(){
	myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
	myRandom = new Random();
    }
	
    public GladLibMap(String source){
	initializeFromSource(source);
	myRandom = new Random();
    }
	
    private void initializeFromSource (String source) {
        String [] label = {"adjective", "color", "country", "noun", "animal", "name", "timeframe", "verb", "fruit"};
        for(String s : label) {
            ArrayList<String> list = readIt(source+"/"+s+".txt");
            myMap.put(s, list);
        }
    }
    
    
    private String randomFrom (ArrayList <String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute (String label) {
        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;
        }
        return randomFrom(myMap.get(label));
    }
    
    private String processWord (String w) {
        int firstIndex = w.indexOf("<");
        int lastIndex = w.indexOf(">");
        if (firstIndex == -1 || lastIndex == -1) {
            return w;
        }
        String prefixW = w.substring(0, firstIndex);
        String substituteW = getSubstitute (w.substring(firstIndex+1, lastIndex));
        String suffixW = w.substring(lastIndex+1);
        return prefixW + substituteW + suffixW;
    }
    
    private void printOut (String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.println(w + "");
            charsWritten += w.length() +1;
        }
    }
    
    public void  makeStoryfromTemplate () {
        String story = "";
        FileResource resource = new FileResource ();
        for (String word : resource.words()) {
            story = story + processWord(word);
            if (word.contains(".")) {
                story = story + "\n";
            }
            else {
               story = story  + " ";
            }
        }
        System.out.println(story);
        totalWordsInMap ();
        totalWordsConsidered ();
        //printOut(story, 60);
    }
    
    private ArrayList <String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        FileResource resource = new FileResource (source);
        for (String line : resource.lines()) {
            list.add(line);
        }
        return list;
    }
    
    private void totalWordsInMap () {
        int totalWords = 0;
        for (String s : myMap.keySet()) {
            totalWords += myMap.get(s).size(); 
            System.out.println(myMap.get(s) + "\t" + s);
            System.out.println(myMap.get(s).size() + "\t" + s);
        }
        System.out.println("Total words in map is  " + totalWords);   
    }
    
    private void totalWordsConsidered () {
        int totalWordsUsed = 0;
        HashMap <String, Integer> wordConsideredMap = new HashMap <String, Integer>();
        FileResource resource = new FileResource ();
        for (String word : resource.words()) {
          int firstIndex = word.indexOf("<");
          int lastIndex = word.indexOf(">");
          if (firstIndex != -1 && lastIndex != -1) {
            String label = word.substring(firstIndex+1, lastIndex);
            
            if (!wordConsideredMap.containsKey(label)) {
                wordConsideredMap.put(label,1);
            }
            else {
                wordConsideredMap.put(label, wordConsideredMap.get(label) +1);
            }
            
          }
        }
        for (String s : wordConsideredMap.keySet()) {              
           System.out.println(wordConsideredMap.get(s) + "\t" + s);
           //totalWordsUsed += wordConsideredMap.get(s);
           for (String smap : myMap.keySet()) {
               if (smap.equals(s)) {
                   totalWordsUsed += myMap.get(smap).size();
               }
           }
        }
        System.out.println("The total words considered in the GladLib is " + totalWordsUsed);
    }
}
