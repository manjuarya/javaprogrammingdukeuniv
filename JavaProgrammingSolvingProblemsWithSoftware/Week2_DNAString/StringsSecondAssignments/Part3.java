
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
    
    private int countGenes(String dna) {
        int count = 0;
        int startIndex = dna.indexOf("ATG");
        while (true) {
            String validGene = findGene(dna);
            System.out.println("The valid gene is  " + validGene);
            if (validGene.isEmpty()) {
                break;
            }
            count += 1; 
            int index = startIndex + validGene.length(); 
            dna = dna.substring(index);
        }
        return count;
    }
    
    public void testCountGenes() {
        String dna = "AATGCTAACTAGCTGACTAAT";
        System.out.println("The dna strand is " + dna);
        //printAllGenes(dna);
        int count = countGenes(dna);
        System.out.println("The count of valid gene in the strand is:  " + count);
        dna = "AATATAAAATAATAGTGASSSS";
        System.out.println("The dna strand is " + dna);
        //printAllGenes(dna);
        count = countGenes(dna);
        System.out.println("The count of valid gene in the strand is:  " + count);
        dna = "ATGCTCTCTTTAATTTTTTTATGTGTAGCCATGCACACACACACATAAATGAAATAG";
        System.out.println("The dna strand is " + dna);
        //printAllGenes(dna);
        count = countGenes(dna);
        System.out.println("The count of valid gene in the strand is:  " + count);
        dna = "AAAATGCTCTCTTATATTTTATGTGTAGGCCATGCACACACACACATAGA";
        System.out.println("The dna strand is " + dna);
        //printAllGenes(dna);
        count = countGenes(dna);
        System.out.println("The count of valid gene in the strand is:  " + count);
        dna = "ATGAAAAAAAAAAAA";
        System.out.println("The dna strand is " + dna);
        //printAllGenes(dna);
        count = countGenes(dna);
        System.out.println("The count of valid gene in the strand is:  " + count);        
        dna = "ATGTAAGATGCCCTAGT";
        System.out.println("The dna strand is " + dna);
        //printAllGenes(dna);
        count = countGenes(dna);
        System.out.println("The count of valid gene in the strand is:  " + count);
    } 
}
