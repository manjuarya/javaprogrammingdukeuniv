
/**
 * Write a description of ManyGenesnCodon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner; 
public class ManyGenesnCodon {
  
public int FindStopCodon(String a,int startIndex, String Codon) {
    Scanner reader = new Scanner(System.in);
    int currIndex = a.indexOf(Codon, startIndex+2);
    System.out.println("shifting currIndex before whileloop: " + currIndex);
    reader.next();
    while (currIndex != -1) {
        int diff = currIndex - startIndex;
        System.out.println("diff under whileloop: " + diff);
        reader.next();
        if (diff%3 == 0) {
            System.out.println( Codon + " gene is: " + a.substring(startIndex, currIndex + 3));
            return currIndex;
        }
        else {
            currIndex = a.indexOf(Codon, currIndex + 2);
            System.out.println("shifting start index" + startIndex + 
            "and current index: " +currIndex);
            reader.next();
        }
    }
    System.out.println("There is no "+ Codon);  
    return a.length();
    //return currIndex;
}

public void main() {
    Scanner reader = new Scanner(System.in);
    System.out.println("Enter the string here: ");
    String dna = reader.next();
    int startIndex = dna.indexOf("ATG"); 
    System.out.println("The start index is: " + startIndex);
    while ( startIndex != -1) {
           
        int stopIndextaa = FindStopCodon(dna, startIndex,"TAA");    
        int stopIndextag = FindStopCodon(dna, startIndex,"TAG");
        int stopIndextga = FindStopCodon(dna, startIndex,"TGA");
        //startIndex = dna.indexOf("ATG", startIndex + 2);   
        //System.out.println("There is no start codon");
        if (stopIndextaa == -1 && stopIndextag == -1 && stopIndextga == -1) {
            System.out.println("There is no stop codon TTA, TAG & TGA in this string");
        }
        int temp = Math.min(stopIndextaa, stopIndextag);
        int minIndex = Math.min(temp, stopIndextga);
        String result = dna.substring(startIndex, minIndex+3);    
        System.out.println("The valid gene is:" + result);
        startIndex = dna.indexOf("ATG", minIndex + 3);
    }
    System.out.println("There is no start codon");
}
}


