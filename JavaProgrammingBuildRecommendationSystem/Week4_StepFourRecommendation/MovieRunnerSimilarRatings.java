
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class MovieRunnerSimilarRatings {  
    
    public void printSimilarRatings () {
        String moviefile = "ratedmoviesfull.csv"; 
        String rtfile = "ratings.csv";
        FourthRatings  fr = new FourthRatings (rtfile);
        MovieDatabase.initialize(moviefile);
        
        System.out.println("The total movies in the file are: " + MovieDatabase.size());
        System.out.println("The total raters in the file are: " + RaterDatabase.size());
        
        String raterId = "71";
        int numSimilarRaters = 20;
        int minimalRater = 5;
        Filter filterCriteria = new TrueFilter();
        
        ArrayList<Rating> similarRatings = fr.getSimilarRatings( raterId, numSimilarRaters, minimalRater);
        
        Collections.sort(similarRatings, Collections.reverseOrder()); 
        
        System.out.println("The  number of movies found with similar rating is:  " + similarRatings.size());
        for(int i=0; i<similarRatings.size(); i++){            
            System.out.println(similarRatings.get(i).getValue() + "   " + MovieDatabase.getTitle(similarRatings.get(i).getItem()));           
        }
        
    } 
    
    public void printSimilarRatingsByGenre () {
        String moviefile = "ratedmoviesfull.csv"; 
        String rtfile = "ratings.csv";
        FourthRatings  fr = new FourthRatings (rtfile);
        MovieDatabase.initialize(moviefile);
        
        //System.out.println("The total movies in the file are: " + MovieDatabase.size());
        //System.out.println("The total raters in the file are: " + RaterDatabase.size());
        
        String raterId = "964";
        int numSimilarRaters = 20;
        int minimalRater = 5;
        String genre = "Mystery";
        GenreFilter GF = new GenreFilter(genre);
        
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter( raterId, numSimilarRaters, minimalRater, GF);
        
        Collections.sort(similarRatings, Collections.reverseOrder()); 
        
        System.out.println("The  number of movies found with similar rating is:  " + similarRatings.size());
        for(int i=0; i<similarRatings.size(); i++){            
            System.out.println(similarRatings.get(i).getValue() + "   " + MovieDatabase.getTitle(similarRatings.get(i).getItem()));           
        }
        
    } 
    
    public void printSimilarRatingsByDirector () {
        String moviefile = "ratedmoviesfull.csv"; 
        String rtfile = "ratings.csv";
        FourthRatings  fr = new FourthRatings (rtfile);
        MovieDatabase.initialize(moviefile);
        
        //System.out.println("The total movies in the file are: " + MovieDatabase.size());
        //System.out.println("The total raters in the file are: " + RaterDatabase.size());
        
        String raterId = "120";
        int numSimilarRaters = 10;
        int minimalRater = 2;
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        DirectorsFilter  DF = new DirectorsFilter(directors); 
        
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter( raterId, numSimilarRaters, minimalRater, DF);
        
        Collections.sort(similarRatings, Collections.reverseOrder()); 
        
        System.out.println("The  number of movies found with similar rating is:  " + similarRatings.size());
        for(int i=0; i<similarRatings.size(); i++){            
            System.out.println(similarRatings.get(i).getValue() + "   " + MovieDatabase.getTitle(similarRatings.get(i).getItem()));           
        }        
    } 
    
    public void printSimilarRatingsByGenreAndMinutes () {
        String moviefile = "ratedmoviesfull.csv"; 
        String rtfile = "ratings.csv";
        FourthRatings  fr = new FourthRatings (rtfile);
        MovieDatabase.initialize(moviefile);
        
        //System.out.println("The total movies in the file are: " + MovieDatabase.size());
        //System.out.println("The total raters in the file are: " + RaterDatabase.size());
        
        String raterId = "168";
        int numSimilarRaters = 10;
        int minimalRater = 3;
        
        String genre = "Drama";
        GenreFilter GF = new GenreFilter(genre);
        
        int minNoMinutes = 80;
        int maxNoMinutes = 160;
        MinutesFilter  MF = new MinutesFilter(minNoMinutes, maxNoMinutes);
        
        AllFilters filter = new AllFilters();
        filter.addFilter(GF);
        filter.addFilter(MF);
        
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter( raterId, numSimilarRaters, minimalRater, filter);
        
        Collections.sort(similarRatings, Collections.reverseOrder()); 
        
        System.out.println("The  number of movies found with similar rating is:  " + similarRatings.size());
        for(int i=0; i<similarRatings.size(); i++){            
            System.out.println(similarRatings.get(i).getValue() + "   " + MovieDatabase.getTitle(similarRatings.get(i).getItem()));           
        }        
    } 
    
    public void printSimilarRatingsByYearAfterAndMinutes () {
        String moviefile = "ratedmoviesfull.csv"; 
        String rtfile = "ratings.csv";
        FourthRatings  fr = new FourthRatings (rtfile);
        MovieDatabase.initialize(moviefile);
        
        //System.out.println("The total movies in the file are: " + MovieDatabase.size());
        //System.out.println("The total raters in the file are: " + RaterDatabase.size());
        
        String raterId = "314";
        int numSimilarRaters = 10;
        int minimalRater = 5;
        
        int year = 1975;
        YearAfterFilter YAF = new YearAfterFilter(year);
        
        int minNoMinutes = 70;
        int maxNoMinutes = 200;
        MinutesFilter  MF = new MinutesFilter(minNoMinutes, maxNoMinutes);
        
        AllFilters filter = new AllFilters();
        filter.addFilter(YAF);
        filter.addFilter(MF);
        
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter( raterId, numSimilarRaters, minimalRater, filter);
        
        Collections.sort(similarRatings, Collections.reverseOrder()); 
        
        System.out.println("The  number of movies found with similar rating is:  " + similarRatings.size());
        for(int i=0; i<similarRatings.size(); i++){            
            System.out.println(similarRatings.get(i).getValue() + "   " + MovieDatabase.getTitle(similarRatings.get(i).getItem()));           
        }        
    } 
    
}
