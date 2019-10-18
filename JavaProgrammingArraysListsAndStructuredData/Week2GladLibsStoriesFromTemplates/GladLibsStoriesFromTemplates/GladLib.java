
/**
 * Write a description of GladLib here.
 * 
 * @author (Manju) 
 * @version (28/08/2019)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class GladLib {
    private ArrayList <String> adjectiveList;
    private ArrayList <String> colorList;
    private ArrayList <String> countryList;
    private ArrayList <String> nounList;
    private ArrayList <String> animalList;
    private ArrayList <String> nameList;
    private ArrayList <String> timeframeList;
    private ArrayList <String> verbList;
    private ArrayList <String> fruitList;
        
    private Random myRandom;
    
    private String dataSourceDirectory = "Data";
    private String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    
    public GladLib(){
    initializeFromSource(dataSourceDirectory);
    myRandom = new Random();
    }
    
    public GladLib(String source){
    initializeFromSource(source);
    myRandom = new Random();
    }
    
    private void initializeFromSource (String source) {
        adjectiveList = readIt(source + "/adjective.txt");
        colorList = readIt(source + "/color.txt");
        countryList = readIt(source + "/country.txt");
        nounList = readIt(source + "/noun.txt");
        animalList = readIt(source + "/animal.txt");
        nameList = readIt(source + "/name.txt");
        timeframeList = readIt(source + "/timeframe.txt");
        verbList = readIt(source + "/verb.txt");
        fruitList = readIt(source + "/fruit.txt");
    }
    
    
    private String randomFrom (ArrayList <String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute (String label) {
        if (label.equals("adjective")) {
            return randomFrom (adjectiveList);
        }
        
        if (label.equals("color")) {
            return randomFrom (colorList);
        }
        
        if (label.equals("country")) {
            return randomFrom (countryList);
        }
        
        if (label.equals("noun")) {
            return randomFrom (nounList);
        }
        
        if (label.equals("animal")) {
            return randomFrom (animalList);
        }
        
        if (label.equals("name")) {
            return randomFrom (nameList);
        }
        
        if (label.equals("timeframe")) {
            return randomFrom (timeframeList);
        }
        
        if (label.equals("verb")) {
            return randomFrom (verbList);
        }
        
        if (label.equals("fruit")) {
            return randomFrom (fruitList);
        }
        
        if (label.equals("number")) {
            return "" + myRandom.nextInt(50) + 5;
        }
        return "**UNKNOWN**";
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
       
}
    

