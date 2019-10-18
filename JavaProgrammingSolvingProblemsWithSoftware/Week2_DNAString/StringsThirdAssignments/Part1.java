
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
    
    /*public void testFindGene() {
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
        dna = "atgtaattttttatgccctagttttttatgggggggtttttttagaaaaaaatgtttgggaaataa";
        System.out.println("The dna strand is " + dna);
        validGene = findGene(dna);
        System.out.println("The valid gene is " + validGene);
        dna = "ATGCTCTCTTTAATTTTTTTATGTGTAGCCATGCACACACACACATAAATGAAATAG";
        System.out.println("The dna strand is " + dna);
        StorageResource gene = getAllGenes(dna);
        for (String s : gene.data()) {
            System.out.println("The valid gene is " + s);
        }
    }*/
    
    public void printAllGenes(String dna) {
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
            int index = startIndex + validGene.length(); 
            dna = dna.substring(index);
            startIndex = dna.indexOf("atg"); 
        }
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
    
    public void testGeneWithFile() {
        FileResource fr = new FileResource();
        StorageResource sr = new StorageResource();
        String dna = fr.asString();
        dna = dna.toLowerCase();
        //printAllGenes(dna);
        sr = getAllGenes(dna);
        for (String s : sr.data()) {
            System.out.println(s);
        }
    }
}
