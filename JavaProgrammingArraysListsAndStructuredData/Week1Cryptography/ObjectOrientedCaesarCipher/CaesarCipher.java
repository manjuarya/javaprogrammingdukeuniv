package ObjectOrientedCaesarCipher;



/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class CaesarCipher {
    private String Alph;
    private String ShiftedAlph;
    
    public CaesarCipher( int key ) {
        Alph = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        ShiftedAlph = Alph.substring(key) + Alph.substring(0, key);
    }
    
    public String encrypt (String input) {
        StringBuilder sb = new StringBuilder(input);
        
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
    
    public String decrypt(String input) {
        return encrypt (input);   
    }
}
