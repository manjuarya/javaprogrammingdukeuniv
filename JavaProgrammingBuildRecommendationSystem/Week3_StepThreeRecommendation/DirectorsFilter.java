
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter {
    private String[] myDirector;
	
	public DirectorsFilter(String director) {
		myDirector = director.split(",");
	} 
	
	@Override
	public boolean satisfies(String id) {
	    for (String a : myDirector){
	        if(MovieDatabase.getDirector(id).contains(a)){
	            return true;
	        }
	    }    
	    return false;
	}

}
