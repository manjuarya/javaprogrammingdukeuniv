
/**
 * Write a description of ValidSimpleGene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ValidSimpleGene {
    public String FindGene(String s) {
        int startIndex = s.indexOf("ATG");
        int stopIndex = s.indexOf("TAA", startIndex+3);
        String result = s.substring(startIndex, stopIndex+3);  
        int Diff =stopIndex - startIndex;
        if (Diff % 3 == 0) {
            return result.toLowerCase();
        }  
        else {
            return "not found";
        }
    }
        
    public void testGene() {
        String A = "AAATATGAAATATTAATTTTTTTTTT";
        String Gene = FindGene(A);
        System.out.println("The Gene Strand is " + A + "  & its Valid Gene is  " + Gene);
        A = "AATATGAAATAATAGTGASSSS";
        Gene = FindGene(A);
        System.out.println("The Gene Strand is " + A + "  & its Valid Gene is  " + Gene);
        A = "ATGCTCTCTTGATTTTTTTATGTGTAGCCATGCACACACACACATAAGA";
        Gene = FindGene(A);
        System.out.println("The Gene Strand is " + A + "  & its Valid Gene is  " + Gene);
        A = "AAAATGCTCTCTTGATTTTATGTGTAGCCATGCACACACACACATAAGA";
        Gene = FindGene(A);
        System.out.println("The Gene Strand is " + A + "  & its Valid Gene is  " + Gene);
    }
}
