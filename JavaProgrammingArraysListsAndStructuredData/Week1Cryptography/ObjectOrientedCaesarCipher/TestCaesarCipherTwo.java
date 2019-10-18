package ObjectOrientedCaesarCipher;


/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class TestCaesarCipherTwo {
     public StringBuilder halfOfString (String msg, int start) {
        StringBuilder halfString = new StringBuilder();
        for (int i=start; i<msg.length(); i+=2) {
           halfString.append(msg.charAt(i));
        }
        return halfString;
    }
        
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
    
    private String breakCaesarCipher(String input) {
        StringBuilder half0 = new StringBuilder();
        StringBuilder half1 = new StringBuilder();
        half0 = halfOfString (input, 0);
        int dKey1 = getKey(half0.toString());
        half1 = halfOfString (input, 1);
        int dKey2 = getKey(half1.toString());      
        System.out.println("The Keys used to encrypt this message was  " + dKey1 + " & " + dKey2);
        CaesarCipherTwo ccTwo = new CaesarCipherTwo(26-dKey1, 26-dKey2);
        String decryptedMessage = ccTwo.decrypt(input);
        return decryptedMessage;
    }
    
    public void simpleTests() {
        //FileResource fr = new FileResource();
        //String message = fr.asString(); 
        String message = "Top ncmy qkff vi vguv vbg ycpx";
        //CaesarCipherTwo ccTwo = new CaesarCipherTwo(12, 2);
        //String encryptedMsgWithTwoKey = ccTwo.encrypt(message);
        //System.out.println("The encrypted message with 2 Key is  " + encryptedMsgWithTwoKey);
        String decryptedMessageWithTwoKey = breakCaesarCipher(message);
        System.out.println("The decrypted message with 2 Key is  " + decryptedMessageWithTwoKey);
    }
    
}
