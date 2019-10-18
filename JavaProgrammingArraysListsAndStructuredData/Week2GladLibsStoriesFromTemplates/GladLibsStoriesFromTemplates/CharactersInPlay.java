
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;

public class CharactersInPlay {
    private ArrayList <String> namesOfCharacter;
    private ArrayList <Integer> countsOfCharacter;
    
    public CharactersInPlay () {
        namesOfCharacter = new ArrayList <String> ();
        countsOfCharacter = new ArrayList <Integer> ();    
    }
    
    public void update (String person) {
        if (person.contains(".")) {
                int indexOfPeriod = person.indexOf(".");
                String character = person.substring(0, indexOfPeriod);
                character = character.toLowerCase();
                int indexOfChar = namesOfCharacter.indexOf(character);
                if (indexOfChar == -1) {
                  namesOfCharacter.add(character);
                  countsOfCharacter.add(1);             
                }
                else {
                  int value = countsOfCharacter.get(indexOfChar);
                  countsOfCharacter.set(indexOfChar, value+1);
                }             
        }
    }
    
    public void findAllCharacters () { 
        namesOfCharacter.clear();
        countsOfCharacter.clear();
        FileResource resource = new FileResource();
        for (String line : resource.lines()) { 
            update (line);
        }
    }
    
    public void charactersWithNumParts (int num1, int num2) {
        for (int i=0; i<namesOfCharacter.size(); i++) {
            if (countsOfCharacter.get(i) < num2 & countsOfCharacter.get(i) > num1) {
                System.out.println(namesOfCharacter.get(i) + "\t" + countsOfCharacter.get(i));
            }
        }
    }
    
    public void tester () {
        findAllCharacters ();
        System.out.println(namesOfCharacter.size());
        for (int i=0; i<namesOfCharacter.size(); i++) {
            //System.out.println(namesOfCharacter.get(i) + "\t" + countsOfCharacter.get(i));
        }
        charactersWithNumParts (10, 15);
    }
}
