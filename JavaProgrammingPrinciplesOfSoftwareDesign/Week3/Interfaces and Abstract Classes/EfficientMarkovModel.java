
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int myOrder;
    //private HashMap<String, ArrayList<String>> followMap;
            
    public EfficientMarkovModel(int order) {
        myOrder = order;
        //followMap = new HashMap<String, ArrayList<String>>();
    }
        
    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - myOrder);
        String key = myText.substring(index, index + myOrder);
        sb.append(key);
        //buildMap();
                            
        for(int k=0; k < numChars - myOrder; k++){
            ArrayList<String> follows = getFollows(key);
            
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
    
        
    public void buildMap() {
        
        for(int i=0; i<=myText.length()-myOrder; i++) {
            String mapKey = myText.substring(i, i + myOrder);
            ArrayList<String> follows = findFollows(mapKey);
            if (follows.size() == 0) {
                ArrayList<String> values = new ArrayList<String>();
                values.add("n");
                followMap.put(mapKey, values);
                break;
            }
            followMap.put(mapKey, follows);
        }         
        
    }    
    
    public ArrayList<String> findFollows (String key) {
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
        int t = follows.size();
        return follows;
    }   
    
    public void printHashMapInfo() {
        ArrayList<String> follows = new ArrayList<String>();
        
        for (String s : followMap.keySet()) {
            follows = followMap.get(s);
            //System.out.println("The key is: " + s + "  & its follows are " + follows);
        }
        
        System.out.println("The number of key in the hashmap is: " + followMap.size());
        
        int maxSize = 0;
        String maxKey = "";
        for (String s : followMap.keySet()) {
            follows = followMap.get(s);
            int size = follows.size();
            if (maxSize < size) {
                maxSize = size;
                maxKey = s;
            }
        }
        System.out.println("The maximum value is: " + maxSize + "  & its key: " + maxKey);
    } 
    
    public String getMarkovOrder() {
        return "MarkovModel of order" + myOrder;
    }
    
}
