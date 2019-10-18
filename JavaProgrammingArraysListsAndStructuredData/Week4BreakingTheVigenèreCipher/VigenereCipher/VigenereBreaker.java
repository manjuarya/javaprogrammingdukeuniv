
/**
 * Write a description of VigenereBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class VigenereBreaker {
    private HashSet<String> set = new HashSet<String>();
    
    public String  sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder slice = new StringBuilder();
        for (int i=whichSlice; i<message.length(); i+=totalSlices) {
           slice.append(message.charAt(i));
        }
        return slice.toString();    
    }
    
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        StringBuilder sb = new StringBuilder(encrypted);
        for (int l=0; l<klength; l++){         
            String slice = sliceString(encrypted, l, klength);
            CaesarCracker cc = new CaesarCracker(mostCommon);
            key[l] = cc.getKey(slice);
        }
        // for (int l=0; l<klength; l++) {
            // System.out.println("The key is  " + key[l]);
        // }
        return key;
    }
    
    //This method for known langauge english and known keylength//
    //public void breakVigenere() {
        //FileResource fr = new FileResource();
        //String message = fr.asString(); 
        //int[] key = tryKeyLength(message, 4, 'e');
        //VigenereCipher vc = new VigenereCipher(key);
        //String decryptedMessage = vc.decrypt(message);
        //System.out.println("The decrypted message is  " + decryptedMessage);
    //} 
    
    //This method for unknown keylength and known language english//
    public void breakVigenere() {
        HashMap<String, HashSet<String>> map = new HashMap<String, HashSet<String>>();  
        HashSet<String> setNew = new HashSet<String>();
        FileResource fr = new FileResource();
        String message = fr.asString(); 
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource dictionary = new FileResource(f);
            setNew = readDictionary(dictionary);
            String name = f.getName();
            map.put(name, setNew);
        }
              
        String decryptedMessage = breakForAllLangs(message, map);
        System.out.println("The decrypted message is  " + decryptedMessage);
    } 
    
    
    public HashSet<String> readDictionary(FileResource dictionary) {
        HashSet<String> setNew = new HashSet<String>();
        for (String line : dictionary.lines()) {
            line = line.toLowerCase();
            //set.add(line);  
            setNew.add(line); 
        }    
        return setNew;
    }
    
    public int countWords(String message, HashSet<String> set) {
        int i=0;
        for (String word : message.split("\\W+")) {
            word = word.toLowerCase();
            if (set.contains(word)) {
                i++;
            }
        }
        return i;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> set) {
        int maxCount = 0;
        int accurateKeyLength = 0;
        int[] key;
        String decryptedMessage;
        char mostCommonChar = mostCommonCharIn(set);
        System.out.println("The most common character is  " + mostCommonChar);
        for (int k=1; k<=100; k++) {
            key = tryKeyLength(encrypted, k, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(key);
            decryptedMessage = vc.decrypt(encrypted);
            int l = countWords(decryptedMessage, set);
            if (maxCount < l) {
                maxCount = l;
                accurateKeyLength = k;
            }            
        }
        System.out.println("The total  " + maxCount + " words matches out of " + encrypted.length() + " words");
        System.out.println("The accurate key length is  " + accurateKeyLength);
        key = tryKeyLength(encrypted, accurateKeyLength, mostCommonChar);
        // for(int a=0; a<key.length; a++) {
            // System.out.println("The exact key for this message to decrypt is  " + key[a]);
        // }
        
        VigenereCipher vc = new VigenereCipher(key);
        return decryptedMessage = vc.decrypt(encrypted);         
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];        
        for (String words : dictionary) {
            
            for(int k=0; k < words.length(); k++){
                int dex = alph.indexOf(Character.toLowerCase(words.charAt(k)));
                if (dex != -1){
                    counts[dex] += 1;
                }
            }            
        } 
        int maxDex = 0;
        for(int k=0; k < counts.length; k++){
            if (counts[k] > counts[maxDex]){
                maxDex = k;
            }
        }
        
        return alph.charAt(maxDex);
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> language) {
        int maxCount = 0;
        String accurateLangauge = "";
        String decryptedMessage;
        for (String s : language.keySet()) {
            System.out.println("For the file " + s);
            decryptedMessage = breakForLanguage(encrypted, language.get(s));
            for (String name : language.keySet()) {
                int l = countWords(decryptedMessage, language.get(name));
                if (maxCount < l) {
                    maxCount = l;
                    accurateLangauge = name;
                }            
            }
            
        } 
        System.out.println("the exact language is  " + accurateLangauge);
        return decryptedMessage = breakForLanguage(encrypted, language.get(accurateLangauge));        
    }
}
