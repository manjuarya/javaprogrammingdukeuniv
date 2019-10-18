
/**
 * Write a description of AbstractMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected HashMap <String, ArrayList<String>> followMap;
        
    public AbstractMarkovModel() {
        myRandom = new Random();
        followMap = new HashMap <String, ArrayList<String>>();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);    
       
    abstract public String getMarkovOrder();

    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        
        for (String s : followMap.keySet()) {
            if (s.equals(key)) {
                follows = followMap.get(s);
            }
        } 
        return follows;
    }
    
    
    
    
    /*protected ArrayList<String> getFollows (String key) {
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
    }*/ 
}
