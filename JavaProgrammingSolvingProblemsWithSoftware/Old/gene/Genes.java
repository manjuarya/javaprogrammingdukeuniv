
/**
 * Write a description of Genes here.
 * 
 * @author (Manju) 
 * @version (17.07.2019)
 */
import java.util.Scanner; 

public class Genes {
    
public int FindStopCodon(String a,int startIndex, String Codon) {
    int currIndex = a.indexOf(Codon);
    while (currIndex != -1) {
        int diff = currIndex - startIndex;
        if (diff%3 == 0) {
            return currIndex;
        }
        else {
            currIndex = a.indexOf(Codon, startIndex + currIndex);
        }
    }
    System.out.println("There is no "+ Codon);  
    //return a.lenght();
    return currIndex;
}

public void main() {
    Scanner reader = new Scanner(System.in);
    System.out.println("Enter the string here: ");
    String dna = reader.next();
    int startIndex = dna.indexOf("ATG");
    if (startIndex == -1) {
        System.out.println("There is no start codon");
    }
    int stopIndextaa = FindStopCodon(dna, startIndex,"TAA");
    int stopIndextag = FindStopCodon(dna, startIndex,"TAG");
    int stopIndextga = FindStopCodon(dna, startIndex,"TGA");
    if (stopIndextaa == -1 && stopIndextag == -1 && stopIndextga == -1) {
        System.out.println("There is no stop codon TTA, TAG & TGA in this string");
    }
    int temp = Math.min(stopIndextaa, stopIndextag);
    int minIndex = Math.min(temp, stopIndextga);
    String result = dna.substring(startIndex, minIndex+3);
}
}
