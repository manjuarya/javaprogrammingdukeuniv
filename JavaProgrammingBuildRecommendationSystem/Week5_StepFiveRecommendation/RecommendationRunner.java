
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate (){
        String moviefile = "ratedmoviesfull.csv"; 
        MovieDatabase.initialize(moviefile);
        
        String genre = "Comedy";
        GenreFilter GF = new GenreFilter(genre);
        
        int minNoMinutes = 100;
        int maxNoMinutes = 200;
        MinutesFilter  MF = new MinutesFilter(minNoMinutes, maxNoMinutes);
        
        int year = 2010;
        YearAfterFilter YAF = new YearAfterFilter(year);
        
        AllFilters filter = new AllFilters();
        filter.addFilter(GF);
        filter.addFilter(MF);
        filter.addFilter(YAF);      
        
        ArrayList<String> movies = MovieDatabase.filterBy(filter);
        ArrayList<String> moviesToShow = new ArrayList<String>();
        
        /*for(int k=0; k<10; k++){
            moviesToShow.add(movies.get(k));
        }*/
           
        movies.subList(0, 15);
        System.out.println(movies.size());
        return new ArrayList <> (movies.subList(0, 15));
    }  
    
    public void test(){
        String rtfile = "ratings.csv";
        FourthRatings  fr = new FourthRatings (rtfile);
        String moviefile = "ratedmoviesfull.csv"; 
        MovieDatabase.initialize(moviefile);
        
        String raterId = "71";
        int numSimilarRaters = 20;
        int minimalRater = 5;
        Filter filterCriteria = new TrueFilter();
        ArrayList<Rating> similarRatings = fr.getSimilarRatings( raterId, numSimilarRaters, minimalRater);
        System.out.println(similarRatings.size());
        Collections.sort(similarRatings, Collections.reverseOrder());         
        
        for(int i=0; i<5; i++){            
            System.out.println(MovieDatabase.getTitle(similarRatings.get(i).getItem()));   
            //System.out.println(similarRatings.get(i).getItem());    
        }
    }  
    
    public void printRecommendationsFor (String webRaterID){
        
        String rtfile = "ratings.csv";
        FourthRatings  fr = new FourthRatings (rtfile);
        String moviefile = "ratedmoviesfull.csv"; 
        MovieDatabase.initialize(moviefile);
                
        int numSimilarRaters = 20;
        int minimalRater = 5;
        Filter filterCriteria = new TrueFilter();
        ArrayList<Rating> similarRatings = fr.getSimilarRatings( webRaterID, numSimilarRaters, minimalRater);
        
        Collections.sort(similarRatings, Collections.reverseOrder());         
        
        System.out.println("We recommend that you will like below movies: "); 
        for(int i=0; i<similarRatings.size(); i++){     
            
            System.out.println(i+1 + ") " + MovieDatabase.getTitle(similarRatings.get(i).getItem()));  
            
            if(i > 4) {
                break;
            }
  
        }  
    }
}
