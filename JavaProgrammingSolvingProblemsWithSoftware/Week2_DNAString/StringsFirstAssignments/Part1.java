
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    private String findSimpleGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = dna.indexOf("TAA", startIndex+3);
        if (stopIndex == -1) {
            return "";
        }
        String result = dna.substring(startIndex, stopIndex+3);  
        int Diff =stopIndex - startIndex;
        if (Diff % 3 == 0) {
            return result.toLowerCase();
        }  
        else {
            return "not found";
        }
    }
    
    public void testSimpleGene() {
        String A = "AAATATGAAATATTAATTTTTTTTTT";
        String Gene = findSimpleGene(A);
        System.out.println("The Gene Strand is " + A + "  & its Valid Gene is  " + Gene);
        A = "AATATGAAATAATAGTGASSSS";
        Gene = findSimpleGene(A);
        System.out.println("The Gene Strand is " + A + "  & its Valid Gene is  " + Gene);
        A = "ATGCTCTCTTGATTTTTTTATGTGTAGCCATGCACACACACACATAAGA";
        Gene = findSimpleGene(A);
        System.out.println("The Gene Strand is " + A + "  & its Valid Gene is  " + Gene);
        A = "AAAATGCTCTCTTGATTTTATGTGTAGCCATGCACACACACACATAAGA";
        Gene = findSimpleGene(A);
        System.out.println("The Gene Strand is " + A + "  & its Valid Gene is  " + Gene);
    }
}
