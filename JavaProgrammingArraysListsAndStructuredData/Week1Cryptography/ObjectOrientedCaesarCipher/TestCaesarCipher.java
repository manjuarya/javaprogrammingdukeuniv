package ObjectOrientedCaesarCipher;


/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class TestCaesarCipher {
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
            //System.out.println(k + "   =   " + count[k]);
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
    
    private String breakCaesarCipher(String input) {
        int[] count = new int[26];
        int[] freqs = countLetters(input, count);
        int maxIdx = maxIndex(freqs);
        //System.out.println("The maxIdx is  " + maxIdx);
        int dKey;
        if (maxIdx < 4) {
            dKey = 26 - (4 - maxIdx);
        }
        else {
            dKey = maxIdx - 4;
        }
        
        System.out.println("The Key used to encrypt this message was  " + dKey);
        CaesarCipher cc = new CaesarCipher(26-dKey);
        String decryptedMessage = cc.decrypt(input);
        return decryptedMessage;
    }
     
    public void simpleTests() {
        //FileResource fr = new FileResource();
        //String message = fr.asString(); 
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int key = 15;
        CaesarCipher cc = new CaesarCipher(key);
        String encryptedMessage = cc.encrypt(message);
        System.out.println("The encrypted message is  " + encryptedMessage);
        //String decryptedMessage = breakCaesarCipher(message);
        //System.out.println("The decrypted message is  " + decryptedMessage);
    }
}
