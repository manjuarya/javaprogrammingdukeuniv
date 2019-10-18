
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class WordFrequencies {
    private ArrayList <String> myWords;
    private ArrayList <Integer> myFreqs;
    
    public WordFrequencies () {
        myWords = new ArrayList <String> ();
        myFreqs = new ArrayList <Integer> ();    
    }

    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource resource = new FileResource();
        for (String word : resource.words()) {
            word = word.toLowerCase();
            int index = myWords.indexOf(word);
            if (index == -1) {
                myWords.add(word);
                myFreqs.add(1);             
            }
            else {
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }
    
    public int findIndexOfMax () {
        int maxFreqs = 0;
        int indexOfMax = 0;
        for (int k=0; k<myFreqs.size(); k++) {
            if (maxFreqs < myFreqs.get(k)) {
                maxFreqs = myFreqs.get(k);
                indexOfMax = k;
            }
        }
        return (indexOfMax);
    }
    
    public void tester() {
        findUnique ();
        System.out.println("The total no of uniq words are  " + myWords.size());
        for (int i=0; i<myWords.size(); i++) {
            System.out.println(myWords.get(i) + "\t  " + myFreqs.get(i));
        }
        
        int indexOfMax = findIndexOfMax ();
        System.out.println("The max freqency word is  " + myWords.get(indexOfMax) + "  and it value is   " + myFreqs.get(indexOfMax));
    }    
}
