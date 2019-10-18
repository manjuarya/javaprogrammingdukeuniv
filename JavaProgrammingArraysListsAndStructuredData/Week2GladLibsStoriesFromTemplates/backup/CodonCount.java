
/**
 * Write a description of CodonCount here.
 * 
 * @author (Manju) 
 * @version (28/08/2019)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class CodonCount {
    private HashMap<String, Integer> codonMap;
    
    public CodonCount () {
        codonMap = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap (int start, String dna) {
        
        HashMap<String, Integer> newMap = new HashMap<String, Integer>();
        newMap.clear();
        for(int i=start; i<= dna.length()-3; i+=3) {
          String codon = dna.substring(i, i+3);
          
          if (!newMap.containsKey(codon)) {
              newMap.put(codon, 1);
              codonMap.put(codon, 1);
          }
          else {
              newMap.put(codon, newMap.get(codon)+1);
              codonMap.put(codon, codonMap.get(codon)+1);
          }
        }
        for (String s :newMap.keySet()) {
            System.out.println("For start position " + start + "   the label is " + s + "  and its value is  " + newMap.get(s));                       
        }
    }
    
    private String getMostCommonCodon () {
        int i=0;
        String commonCodon = "";
        for (String s :codonMap.keySet()) {
            if (i<codonMap.get(s)) {
                commonCodon = s;
                i = codonMap.get(s);
            }
            
        }
        return commonCodon;
    }
    
    private void printCodonCount(int start, int end) {
        for (String s :codonMap.keySet()) {
            if (start<=codonMap.get(s) && end>=codonMap.get(s)) {
                System.out.println("The codon between value  " + start + "  &  " + end + "  is " + s + "\t" + codonMap.get(s));
            }
            
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        for (String s : fr.words()) {
            s = s.toUpperCase();
            for (int k=0; k<3; k++) {
                buildCodonMap(k, s);
            }
            System.out.println("The Codon String is  " + s);
            
        }
        for (String s :codonMap.keySet()) {
            System.out.println(s + "\t" + codonMap.get(s));                       
        }
        String mostCommonCodon = getMostCommonCodon ();
        System.out.println("The most common codon is " + mostCommonCodon + "  & its count is  " + codonMap.get(mostCommonCodon)); 
        printCodonCount(5, 7);
    }
}

