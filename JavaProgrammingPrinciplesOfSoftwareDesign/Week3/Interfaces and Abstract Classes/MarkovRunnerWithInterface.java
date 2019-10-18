
/**
 * Write a description of MarkovRunnerWithInterface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void testHashMap() {
        FileResource fr = new FileResource();
	String st = fr.asString();
	st = st.replace('\n', ' ');
	//String st = "yes-this-is-a-thin-pretty-pink-thistle";
	int size = 200;
	int seed = 531 ;
		
        EfficientMarkovModel emm = new EfficientMarkovModel(5);
        runModel(emm, st, size, seed);        
        emm.printHashMapInfo();
    
    }
    
    public void runModel(EfficientMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        markov.buildMap();
        
        String order = markov.getMarkovOrder();
        System.out.println("running with " + order);
        /*for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }*/
    }
    
    /*public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 200;
		
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

    }*/

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
