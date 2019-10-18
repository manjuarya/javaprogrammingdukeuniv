
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String typeOfRequest; 
    private String phrase; 
        
    public PhraseFilter(String request, String word) { 
        typeOfRequest = request;
        phrase = word;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        if(typeOfRequest.equals("start")) {
            if (qe.getInfo().startsWith(phrase)) {
                return true;
            } 
        }
        if(typeOfRequest.equals("end")) {
            if (qe.getInfo().endsWith(phrase)) {
                return true;
            } 
        }
        if(typeOfRequest.equals("any")) {
            if (qe.getInfo().contains(phrase)) {
                return true;
            } 
        }
        return false;
    }
    
    public String getName() {
        String nameOfTheClass = "PhraseFilter";
        return nameOfTheClass;
    }
}
