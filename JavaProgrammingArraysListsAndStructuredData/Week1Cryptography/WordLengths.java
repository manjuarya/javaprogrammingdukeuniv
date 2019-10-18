
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;


public class WordLengths {
    
    private int [] countWordLength(FileResource resource, int[] counts) {
        
        for (String s : resource.words()) { 
            String newString = s;
            
            if ( !Character.isLetter(s.charAt(0)) ) {
                newString = newString.substring(1);                    
            }
                
            if ( !Character.isLetter(s.charAt(s.length()-1) ) ){
                newString = newString.substring(0, s.length()-1);
            }
            
            if (newString.length() < counts.length) {
                //System.out.println(newString + "\t" + newString.length());
                int length = newString.length();
                counts[length] +=1;
            }
            else {
                counts[counts.length-1] +=1;
            }
        }
        for (int i=0; i<counts.length; i++) {
            System.out.println("The  " + i + "  length word in this File is  " + counts[i]);
        }
        return counts;
    }
    
    private int indexOfMax (int[] values) {
        int maxValue = 0;
        for (int i=0; i<values.length; i++) {
            if (maxValue < values[i]) {
                maxValue = values[i];
            }
        }
        return maxValue;
    }
    
    public void testCountWordLengths () {
        FileResource fr = new FileResource();
        int[] counts = new int[20];
        int[] wordValue = countWordLength(fr, counts);
        int maxIdx = indexOfMax (wordValue);
        System.out.println("The " + maxIdx + "  letters word occure most." );
    }

}
