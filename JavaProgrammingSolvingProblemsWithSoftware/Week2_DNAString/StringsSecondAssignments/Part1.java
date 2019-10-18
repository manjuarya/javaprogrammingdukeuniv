
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class Part1 {
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
    
    public void testFindStopCodon() {
        String dna = "AAATATGAAATATTAATTTTTTTTTT";
        int startIndex = dna.indexOf("ATG"); 
        System.out.println("The start index is: " + startIndex);
        if (startIndex == -1) {
            System.out.println("The is no start Codon");
        }
        int stopIndexTaa = findStopCodon(dna, startIndex, "TAA");
        System.out.println("The stopIndexTaa is: " + stopIndexTaa);
    
        int stopIndexTag = findStopCodon(dna, startIndex, "TAG");
        System.out.println("The stopIndexTag is: " + stopIndexTag);
    
        int stopIndexTga = findStopCodon(dna, startIndex, "TGA");
        System.out.println("The stopIndexTga is: " + stopIndexTga);
    
    
    }
    
    private String findGene(String dna) {
        int startIndex = dna.indexOf("ATG"); 
        //System.out.println("The start index is: " + startIndex);
        if (startIndex == -1) {
            //System.out.println("The is no start Codon");
            return "";
        }
        int stopIndexTaa = findStopCodon(dna, startIndex, "TAA");    
        int stopIndexTag = findStopCodon(dna, startIndex, "TAG");
        int stopIndexTga = findStopCodon(dna, startIndex, "TGA");
        if (stopIndexTaa == dna.length() && stopIndexTag == dna.length() && stopIndexTga == dna.length()) {
            //System.out.println("There is no stop codon TTA, TAG & TGA in this string");
            return "";
        }
        int temp = Math.min(stopIndexTaa, stopIndexTag);
        int minIndex = Math.min(temp, stopIndexTga);
        return dna.substring(startIndex, minIndex+3);    
    }
    
    public void testFindGene() {
        String dna = "AAATATGAAATAGTAATTTTGATTTTTT";
        System.out.println("The dna strand is " + dna);
        String validGene = findGene(dna);
        System.out.println("The valid gene is " + validGene);
        dna = "AATATAAAATAATAGTGASSSS";
        System.out.println("The dna strand is " + dna);
        validGene = findGene(dna);
        System.out.println("The valid gene is " + validGene);
        dna = "ATGCTCTCTTTAATTTTTTTATGTGTAGCCATGCACACACACACATAAATGAAATAG";
        System.out.println("The dna strand is " + dna);
        //validGene = findGene(dna);
        printAllGenes(dna);
        //System.out.println("The valid gene is " + validGene);
        dna = "AAAATGCTCTCTTATATTTTATGTGTAGGCCATGCACACACACACATAGA";
        System.out.println("The dna strand is " + dna);
        //validGene = findGene(dna);
        printAllGenes(dna);
        //System.out.println("The valid gene is " + validGene);
        dna = "ATGAAAAAAAAAAAA";
        System.out.println("The dna strand is " + dna);
        validGene = findGene(dna);
        System.out.println("The valid gene is " + validGene);
    }
    
    public void printAllGenes(String dna) {
        int startIndex = dna.indexOf("ATG"); 
        //System.out.println("The start index is: " + startIndex);
        if (startIndex == -1) {
            //System.out.println("The is no start Codon");
        }
        
        while (true) {
            String validGene = findGene(dna);
            System.out.println("The valid gene is  " + validGene);
            if (validGene.isEmpty()) {
                break;
            }
            int index = startIndex + validGene.length(); 
            dna = dna.substring(index);
        }
    }
}
