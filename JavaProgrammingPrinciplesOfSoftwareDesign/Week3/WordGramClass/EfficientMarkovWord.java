
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> followMap;
    
    public EfficientMarkovWord(int N) {
        myRandom = new Random();
        myOrder = N;
        followMap = new HashMap<WordGram, ArrayList<String>>();
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
        //int key = kGram.hashCode();
        for (WordGram hash : followMap.keySet()) {
            if (hash.equals(kGram)){
                follows = followMap.get(hash);
            }
        } 
        return follows;
    }
    
    public void buildMap() {
        int counter = 0;
        int flag = 0;
        for(int i=0; i<= myText.length-myOrder; i++) { 
            WordGram mapKey = new WordGram( myText, i, myOrder);
            
            ArrayList<String> follows = findFollows(mapKey);
            if (follows.size() == 0) {
                ArrayList<String> values = new ArrayList<String>();
                values.add("of");
                followMap.put(mapKey, values);
                break;
            }
            for (WordGram w : followMap.keySet()) { 
                if (mapKey.equals(w)) {
                    flag = 1;
                }
            } 
            
            if (flag == 0)
            {
                followMap.put(mapKey, follows); 
                
            }
            flag = 0;                
        }  
    }  
    
    private ArrayList<String> findFollows(WordGram kGram) {
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
    
    public void printHashMapInfo() {
        ArrayList<String> follows = new ArrayList<String>();
        
        for (WordGram hash : followMap.keySet()) {
            follows = followMap.get(hash);
            //System.out.println("The key is: " + hash + "  & its follows are " + follows);
        } 
        
        System.out.println("The number of key in the hashmap is: " + followMap.size());
        
        int maxSize = 0;
        WordGram maxKey = new WordGram(myText, 0, myOrder);
        for (WordGram hash : followMap.keySet()) {
            follows = followMap.get(hash);
            int size = follows.size();
            if (maxSize < size) {
                maxSize = size;
                maxKey = hash;
            }
        } 
        
        System.out.println("The maximum value is: " + maxSize + "  & its key: " + maxKey);
    } 
}
