
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunner {
    public void runMarkovModel() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int markovClass = 7;
        MarkovModel markov = new MarkovModel(markovClass);
        
        markov.setTraining(st);
        //markov.getFollows("t");
        
        markov.setRandom(953);
        
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    } 
    
    public void runMarkovFour() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovFour markov = new MarkovFour();
        
        markov.setTraining(st);
        //markov.getFollows("t");
        
        markov.setRandom(715);
        
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }
    
    public void runMarkovOne() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //st = "this is a test yes this is a test.";
        //MarkovZero markov = new MarkovZero();
        MarkovOne markov = new MarkovOne();
        //MarkovTwo markov = new MarkovTwo();
        //MarkovThree markov = new MarkovThree();
        
        markov.setTraining(st);
        //markov.getFollows("t");
        
        markov.setRandom(365);
        
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }
    
    
    public void runMarkovZero() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //st = "this is a test yes this is a test.";
        MarkovZero markov = new MarkovZero();
                
        markov.setTraining(st);
        //markov.getFollows("t");
        
        markov.setRandom(1024);
        
        for(int k=0; k < 3; k++){
            String text = markov.getRandomText(500);
            printOut(text);
        }
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
