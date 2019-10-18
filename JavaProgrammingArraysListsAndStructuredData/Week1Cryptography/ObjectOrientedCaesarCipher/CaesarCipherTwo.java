package ObjectOrientedCaesarCipher;


/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }
    
    public String encrypt (String input) {
        StringBuilder encrypt = new StringBuilder(input);
        
        for (int i=0; i<input.length(); i++) { 
            if (i%2==0) {
                char chKey1 = encrypt.charAt(i);
                
                if (Character.isLowerCase(chKey1)) {
                    String AlphLKey1 = alphabet.toLowerCase();
                    String ShiftedAlphLKey1 = shiftedAlphabet1.toLowerCase();
                    int idxLKey1 = AlphLKey1.indexOf(chKey1);
                    if (idxLKey1 != -1) {
                        char newLKey1 = ShiftedAlphLKey1.charAt(idxLKey1);
                        encrypt.setCharAt(i, newLKey1);
                    }
                }
                else {
                    int idxUKey1 = alphabet.indexOf(chKey1);
                    if (idxUKey1 != -1) {
                        char newUKey1 = shiftedAlphabet1.charAt(idxUKey1);
                        encrypt.setCharAt(i, newUKey1);
                    }
                }     
            }
            if (i%2!=0) {
                char chKey2 = encrypt.charAt(i);
                
                if (Character.isLowerCase(chKey2)) {
                    String AlphLKey2 = alphabet.toLowerCase();
                    String ShiftedAlphLKey2 = shiftedAlphabet2.toLowerCase();
                    int idxLKey2 = AlphLKey2.indexOf(chKey2);
                    if (idxLKey2 != -1) {
                        char newLKey2 = ShiftedAlphLKey2.charAt(idxLKey2);
                        encrypt.setCharAt(i, newLKey2);
                    }
                }
                else {
                    int idxUKey2 = alphabet.indexOf(chKey2);
                    if (idxUKey2 != -1) {
                        char newUKey2 = shiftedAlphabet2.charAt(idxUKey2);
                        encrypt.setCharAt(i, newUKey2);
                    }
                }         
            }
        }
        return encrypt.toString();
    }
        
        
    public String decrypt (String msg) {
        return encrypt (msg);        
    }
    
}
