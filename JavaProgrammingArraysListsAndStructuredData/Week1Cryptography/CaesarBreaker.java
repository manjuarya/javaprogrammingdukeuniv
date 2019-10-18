
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class CaesarBreaker {
    
    private int[] countLetters (String msg, int[] count) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        for (int i=0; i<msg.length(); i++) {
            char ch = msg.charAt(i);
            int chidx = alph.indexOf(Character.toLowerCase(ch));
            if (chidx !=-1) {
                count[chidx] +=1;
            }
        }
        for (int k=0; k<count.length; k++){
            System.out.println(k + "   =   " + count[k]);
        }
        return count;
    }
    
    private int maxIndex( int[] freqs) {
        int maxValue = 0;
        int maxIndex=0;
        for (int i=0; i<freqs.length; i++) {
            if (maxValue < freqs[i]) {
                maxIndex = i; 
                maxValue = freqs[i];
            }
            
        }
        System.out.println("The maxIndex value is   " + maxIndex);
        return maxIndex;
    }
    
    private String decrypt(String msg) {
        CaesarCipher cc = new CaesarCipher();
        int[] count = new int[26];
        int[] freqs = countLetters(msg, count);
        int maxIdx = maxIndex(freqs);
        int dKey;
        if (maxIdx < 4) {
            dKey = 26 - (4 - maxIdx);
        }
        else {
            dKey = maxIdx - 4;
        }
        System.out.println("The Key is " + dKey);       
              
        return cc.encrypt(msg, 26-dKey);
    }
    
    private int getKey(String msg) {
        int[] count = new int[26];
        int[] freqs = countLetters(msg, count);
        int maxIdx = maxIndex(freqs);
        //System.out.println("The maxIdx is  " + maxIdx);
        int dKey;
        if (maxIdx < 4) {
            dKey = 26 - (4 - maxIdx);
        }
        else {
            dKey = maxIdx - 4;
        }
        return dKey;        
    }
    
    private StringBuilder halfOfString (String msg, int start) {
        StringBuilder halfString = new StringBuilder();
        for (int i=start; i<msg.length(); i+=2) {
           halfString.append(msg.charAt(i));
        }
        return halfString;
    }
    
    private String decryptTwoKeys (String msg) {
        CaesarCipher cc = new CaesarCipher();
        int i;
        StringBuilder half0 = new StringBuilder();
        StringBuilder half1 = new StringBuilder();
        half0 = halfOfString (msg, 0);
        //System.out.println("The half0 String is " + half0);
        int dKey1 = getKey(half0.toString());
        System.out.println("The key1 is " + dKey1);
        half1 = halfOfString (msg, 1);
        int dKey2 = getKey(half1.toString());
        System.out.println("The key2 is " + dKey2);
        //return cc.encryptTwoKey (msg, 26-dKey1, 26-dKey2);
        return cc.encryptTwoKey (msg, 9, 9);
    }
    
    public void testDecrypt() {
         FileResource fr = new FileResource();
         String encrptMsg = fr.asString(); 
         //String encrptMsg = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
         
         String decryptedMsg1Key = decrypt(encrptMsg);
         System.out.println("The decrypted message of with one Key " + encrptMsg + " is " + decryptedMsg1Key);
         
         //String decryptedMsg2Key = decryptTwoKeys(encrptMsg);
         //System.out.println("The decrypted message of with 2 Key " + encrptMsg + " is " + "\n" + decryptedMsg2Key);
    } 
}
