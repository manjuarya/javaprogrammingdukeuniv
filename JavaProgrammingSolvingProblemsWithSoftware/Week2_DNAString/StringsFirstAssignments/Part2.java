
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    private String findSimpleGene(String dna, String startCodon, String stopCodon) {
        char ch = dna.charAt(1);
        if(Character.isLowerCase(ch)){
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        } 
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) {
            return "";
        }
        int stopIndex = dna.indexOf(stopCodon, startIndex+3);
        if (stopIndex == -1) {
            return "";
        }
        String result = dna.substring(startIndex, stopIndex+3);  
        int Diff =stopIndex - startIndex;
        if (Diff % 3 == 0) {
            return result;
        }  
        else {
            return "not found";
        }
    }
    
    public void testSimpleGene() {
        String A = "gatgctataat";
        String Gene = findSimpleGene(A, "ATG", "TAA");
        System.out.println("The Gene Strand is " + A + "  & its Valid Gene is  " + Gene);
        A = "AATATGAAATAATAGTGASSSS";
        Gene = findSimpleGene(A, "ATG", "TAA");
        System.out.println("The Gene Strand is " + A + "  & its Valid Gene is  " + Gene);
        A = "ATGCTCTCTTGATTTTTTTATGTGTAGCCATGCACACACACACATAAGA";
        Gene = findSimpleGene(A, "ATG", "TAA");
        System.out.println("The Gene Strand is " + A + "  & its Valid Gene is  " + Gene);
        A = "AAAATGCTCTCTTGATTTTATGTGTAGCCATGCACACACACACATAAGA";
        Gene = findSimpleGene(A, "ATG", "TAA");
        System.out.println("The Gene Strand is " + A + "  & its Valid Gene is  " + Gene);
    }
}
