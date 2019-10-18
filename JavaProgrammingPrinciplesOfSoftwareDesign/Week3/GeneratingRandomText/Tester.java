
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Tester {
    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //st = "this is a test yes this is a test.";
        //MarkovZero markov = new MarkovZero();
        //MarkovOne markov = new MarkovOne();
        MarkovTwo markov = new MarkovTwo();
        //MarkovThree markov = new MarkovThree();
        
        markov.setTraining(st);
        ArrayList<String> follows = new ArrayList<String>();
        follows = markov.getFollows("he");
        System.out.println("The follows size is " + follows.size());
    } 
    
    public void testGetFollows() {
        String st = "this is a test yes this is a test.";
        MarkovOne markov = new MarkovOne();
                
        markov.setTraining(st);
        ArrayList<String> follows = new ArrayList<String>();
        follows = markov.getFollows("t");
        System.out.println("The follows are: " + follows + " & its size " + follows.size());
        
        //markov.setRandom(101);
        
        /*for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }*/
    }
    
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
}
