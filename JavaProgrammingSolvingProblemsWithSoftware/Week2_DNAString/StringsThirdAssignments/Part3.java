
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class Part3 {
    private int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
    
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff%3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);            
            }
        }
        //if no stop codon
        return dna.length();
        //return currIndex;
    }
    
    private String findGene(String dna, int startIndex) {
        int stopIndexTaa = findStopCodon(dna, startIndex, "taa");    
        int stopIndexTag = findStopCodon(dna, startIndex, "tag");
        int stopIndexTga = findStopCodon(dna, startIndex, "tga");
        if (stopIndexTaa == dna.length() && stopIndexTag == dna.length() && stopIndexTga == dna.length()) {
            //System.out.println("There is no stop codon TTA, TAG & TGA in this string");
            return "";
        }
        int temp = Math.min(stopIndexTaa, stopIndexTag);
        int minIndex = Math.min(temp, stopIndexTga);
        return dna.substring(startIndex, minIndex+3);    
    }
    
    public StorageResource getAllGenes(String dna) {
        StorageResource sr = new StorageResource();
        int startIndex = dna.indexOf("atg"); 
        //System.out.println("The start index is: " + startIndex);
        if (startIndex == -1) {
            //System.out.println("The is no start Codon");
        }
        
        while (startIndex != -1) {
            String validGene = findGene(dna, startIndex);
            System.out.println("The valid gene is  " + validGene);
            if (validGene.isEmpty()) {
                break;
            }
            sr.add(validGene);
            int index = startIndex + validGene.length(); 
            dna = dna.substring(index);
            startIndex = dna.indexOf("atg"); 
        }             
        return sr;
    }
    
    private void processGenes(StorageResource sr) {
        int count = 0;
        System.out.println("The strings, whose length is greater than 60 character is");
        for (String s : sr.data()) {
            if (s.length() > 60) {
                System.out.println("\n" + s);
                count += 1;
            }
        }
        System.out.println("There are total " + count + "  strings are there, whose length is greater than 60 character.");
        System.out.println("The strings, whose cgRatio is greater than 0.35 is");
        count = 0;
        for (String s : sr.data()) {
            float cgRatio = cgRatio(s);
            if (cgRatio > 0.35) {
                System.out.println("\n" + s);
                count += 1;
            }
        }
        System.out.println("There are total " + count + "  strings are there, whose cgRatio is greater than 0.35.");
        String longestGene = "";
        for (String s : sr.data()) {
            if (s.length() > longestGene.length()) {
                longestGene = s;
            }
        }
        System.out.println("The longest gene is: " + longestGene + "  & its length is: " + longestGene.length());
    }
        
    private float cgRatio(String dna) {
        int countC = countCNG("c", dna); 
        int countG = countCNG("g", dna); 
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
        String stringa = "ctg";
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
    
    public void testProcessGenes() {
        FileResource fr = new FileResource();
        StorageResource sr = new StorageResource();
        String dna = fr.asString();
        dna = dna.toLowerCase();        
        sr = getAllGenes(dna);
        System.out.println("The total Genes in this file is  " + sr.size());
        processGenes(sr);
        int count = countCTG(dna);
        System.out.println("The CTG count is  " + count);
    }
}
