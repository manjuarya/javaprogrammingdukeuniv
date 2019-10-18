
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class WordPlay {

    public boolean isVowel (char ch) {
    if (ch=='a'|| ch=='e'|| ch=='i'|| ch=='o'|| ch=='u') {
        return true;
    }
    else {
        return false;
    }
    }
    
    public String replaceVowels (String phrase, char ch) {
        StringBuilder sb = new StringBuilder (phrase);
        for (int k=0; k<sb.length(); k++) {
            char currCh = sb.charAt(k);
            if (isVowel(currCh)) {
                sb.setCharAt(k, ch);
            }
        }
        return sb.toString();
    }
    
    public String emphasize (String phrase, char ch) {
        StringBuilder sb = new StringBuilder (phrase);
        for (int k=0; k<sb.length(); k++) {
            char currCh = Character.toLowerCase( sb.charAt(k) );
            if (currCh == ch) {
                int idx = phrase.indexOf(currCh, k);
                if (idx%2==0) {
                    sb.setCharAt(k, '*');
                }
                else {
                    sb.setCharAt(k, '+');
                }
            }
        }
        return sb.toString();
    }
    
    public void test() {
        char ch1 = 'b';
        boolean a = isVowel(ch1);
        System.out.println(ch1 + " is Vowel? " + a);
        String s = "Hello World";
        char ch2 = '*';
        String replaceS = replaceVowels(s, ch2);
        System.out.println("The string " + s + " vowels has been replaced with character " + ch2 + " & the replaced String is " + replaceS);
        String s1 = "Mary Bella Abracadabra";
        s1 = s1.toLowerCase();
        char ch3 = 'a';
        String emphasized = emphasize(s1, ch3);
        System.out.println("The string " + s1 + " character " + ch3 + " emphasized with * and + and the new string is " + emphasized);        
    }
}
