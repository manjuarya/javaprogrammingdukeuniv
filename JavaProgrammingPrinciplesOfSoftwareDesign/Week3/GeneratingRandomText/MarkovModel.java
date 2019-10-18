
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovModel {
    private String myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovModel(int N) {
        myRandom = new Random();
        myOrder = N;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
        
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-myOrder);
        String key = myText.substring(index, index+myOrder);
        sb.append(key);
                    	    		
        for(int k=0; k < numChars-myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
		
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
	return sb.toString();
    }
    
    public ArrayList<String> getFollows (String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length()) {
            int start = myText.indexOf(key, pos);
            if (start == -1) {
                break;
            }
            if (start + key.length() > myText.length()-1) {
                break;
            }
            String next = myText.substring(start+key.length(), start+key.length() +1);
            follows.add(next);
            pos = start + key.length();     
        }
        return follows;
    }
}
