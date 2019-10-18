
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void compareMethods() {
        
    } 
    
    public void testHashMap() {
    FileResource fr = new FileResource();
    String st = fr.asString();
    st = st.replace('\n', ' ');
    //st = "this is a test yes this is really a test yes a test this is wow";
    int size = 50;
    int seed = 65 ;
        
    EfficientMarkovWord emw = new EfficientMarkovWord(2);
    runModel(emw, st, size, seed); 
    emw.buildMap();
    emw.printHashMapInfo();
    
    }
    
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        
        //markov.buildMap();
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            markov.setRandom(seed);
            String st = markov.getRandomText(size); 
            printOut(st); 
        }
        //markov.printHashMapInfo();
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //st = "this is a test this is a test this is a test of words";
        MarkovWord markovWord = new MarkovWord(5);  
        runModel(markovWord, st, 50, 844); 
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
