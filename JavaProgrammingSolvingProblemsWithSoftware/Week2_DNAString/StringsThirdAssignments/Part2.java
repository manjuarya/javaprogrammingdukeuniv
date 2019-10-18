
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class Part2 {
    private float cgRatio(String dna) {
        int countC = countCNG("C", dna); 
        int countG = countCNG("G", dna); 
        int dnaLength = dna.length();
        float cgRatio = (float)(countC + countG) / dnaLength;
        return cgRatio;
    } 
     
    private int countCNG(String stringa, String stringb) {
        int indexStringA = stringb.indexOf(stringa);
        int count = 0;
        while (indexStringA != -1) {
            count += 1;
            int lengthStringA = stringa.length();
            stringb = stringb.substring(indexStringA + lengthStringA);
            indexStringA = stringb.indexOf(stringa);
        }
        return count;
    }
    
    private int countCTG(String dna) {
        String stringa = "CTG";
        String stringb = dna;
        int indexStringA = stringb.indexOf(stringa);
        int count = 0;
        if (indexStringA == -1) {
            return 0;
        }
        while (indexStringA != -1) {
            count += 1;
            int lengthStringA = stringa.length();
            stringb = stringb.substring(indexStringA + lengthStringA);
            indexStringA = stringb.indexOf(stringa);
        }
        return count;
    }
    
    public void test() {
        String dna = "ATGCCATAG";
        float cgRatio = cgRatio(dna);
        System.out.println("The cgRatio is:  " + cgRatio);
        int count = countCTG(dna);
        System.out.println(count + " times CTG was there in dna string");
    }
}
