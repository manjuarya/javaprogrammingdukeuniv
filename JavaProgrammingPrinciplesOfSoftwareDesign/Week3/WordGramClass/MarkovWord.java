
/**
 * Write a description of MarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;


public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int N) {
        myRandom = new Random();
        myOrder = N;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  
        WordGram kGram = new WordGram(myText,index,myOrder);
        String st = kGram.toString();
        sb.append(st);
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(kGram);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next); 
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        
        while (pos < myText.length) {
            int start = indexOf(myText, kGram, pos);
            if (start == -1) {
                break;
            }
            if (start + kGram.length() > myText.length-1) {
                break;
            }
            String next = myText[start + kGram.length()];
            follows.add(next);
            pos = start + kGram.length();     
        }
        
        return follows;
    }

    private int indexOf(String[] myText, WordGram target, int start) {
        
        for (int k = start; k<= myText.length - myOrder; k++) {            
            WordGram wg = new WordGram(myText,k,myOrder);
            //System.out.println("the wg is "+wg);
            if (wg.equals(target)) {
                return k;
            }
            
        }
        return -1;
    }
    
    public void buildMap() {
    
    } 
}
