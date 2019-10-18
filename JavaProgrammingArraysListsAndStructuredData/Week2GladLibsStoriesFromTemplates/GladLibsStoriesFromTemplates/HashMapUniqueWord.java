
/**
 * Write a description of HashMapUniqueWord here.
 * 
 * @author (Manju) 
 * @version (28/08/2019)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class HashMapUniqueWord {
    HashMap<String, Integer> map;
    
    public HashMapUniqueWord () {
        map = new HashMap<String, Integer>();
    }
    
    public void countWordsMap() {
        FileResource resource = new FileResource();
        for (String word : resource.words()) {
           if (!map.containsKey(word) ) {
              map.put(word, 1);
           }
           else {
              map.put(word, map.get(word)+1);
           } 
         }
        
    }
    
    public void tester() {
       countWordsMap ();
       for (String s : map.keySet()) {
            if (map.get(s) > 100) {
                System.out.println(s + "\t" + map.get(s));
            }
       }
    }
}
