
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class CaesarCipher {

    public String encrypt (String input, int key) {
        StringBuilder sb = new StringBuilder(input);
        String Alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String ShiftedAlph = Alph.substring(key) + Alph.substring(0, key);
        for (int i=0; i<input.length(); i++) {
            char ch = sb.charAt(i);
            if (Character.isLowerCase(ch)) {
                String AlphL = Alph.toLowerCase();
                String ShiftedAlphL = ShiftedAlph.toLowerCase();
                int idxL = AlphL.indexOf(ch);
                if (idxL != -1) {
                    char newL = ShiftedAlphL.charAt(idxL);
                    sb.setCharAt(i, newL);
                }
            }
            else {
                int idxU = Alph.indexOf(ch);
                if (idxU != -1) {
                    char newU = ShiftedAlph.charAt(idxU);
                    sb.setCharAt(i, newU);
                }
            }
        }
        return sb.toString();
    }
    
    public String encryptTwoKey (String input, int key1, int key2) {
        StringBuilder encrypt = new StringBuilder(input);
        String Alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i=0; i<input.length(); i++) { 
            if (i%2==0) {
                String ShiftedAlphKey1 = Alph.substring(key1) + Alph.substring(0, key1);
                char chKey1 = encrypt.charAt(i);
                if (Character.isLowerCase(chKey1)) {
                    String AlphLKey1 = Alph.toLowerCase();
                    String ShiftedAlphLKey1 = ShiftedAlphKey1.toLowerCase();
                    int idxLKey1 = AlphLKey1.indexOf(chKey1);
                    if (idxLKey1 != -1) {
                        char newLKey1 = ShiftedAlphLKey1.charAt(idxLKey1);
                        encrypt.setCharAt(i, newLKey1);
                    }
                }
                else {
                    int idxUKey1 = Alph.indexOf(chKey1);
                    if (idxUKey1 != -1) {
                        char newUKey1 = ShiftedAlphKey1.charAt(idxUKey1);
                        encrypt.setCharAt(i, newUKey1);
                    }
                }     
            }
            if (i%2!=0) {
                String ShiftedAlphKey2 = Alph.substring(key2) + Alph.substring(0, key2);
                char chKey2 = encrypt.charAt(i);
                if (Character.isLowerCase(chKey2)) {
                    String AlphLKey2 = Alph.toLowerCase();
                    String ShiftedAlphLKey2 = ShiftedAlphKey2.toLowerCase();
                    int idxLKey2 = AlphLKey2.indexOf(chKey2);
                    if (idxLKey2 != -1) {
                        char newLKey2 = ShiftedAlphLKey2.charAt(idxLKey2);
                        encrypt.setCharAt(i, newLKey2);
                    }
                }
                else {
                    int idxUKey2 = Alph.indexOf(chKey2);
                    if (idxUKey2 != -1) {
                        char newUKey2 = ShiftedAlphKey2.charAt(idxUKey2);
                        encrypt.setCharAt(i, newUKey2);
                    }
                }         
            }
        }
        return encrypt.toString();
    }
    
    public void testCaesar () {
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Top ncmy qkff vi vguv vbg ycpx";
        int key = 17;
        int key1 = 24;
        int key2 = 6;
        //String encrypted = encrypt (message, key);
        //System.out.println("The encrypted message of " + message + "  with 1 key is  " + encrypted);
        String messageWithTwoKey = encryptTwoKey (message, key1, key2);
        System.out.println("The encrypted message with 2 key of the message  " + message + "  is  " + messageWithTwoKey);
    }
    
}
