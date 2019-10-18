
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class MovieRunnerAverage {
    
    public void printAverageRatings () {
        String moviefile = "data/ratedmoviesfull.csv"; 
        String ratingsfile = "data/ratings.csv";
        SecondRatings sr = new SecondRatings(moviefile, ratingsfile);
        //System.out.println("The total movies in the file are: " + sr.getMovieSize());
        //System.out.println("The total raters in the file are: " + sr.getRaterSize());
        int minimalRater = 12;
        ArrayList<Rating> avgRating = sr.getAverageRatings(minimalRater);
        System.out.println("The average rating per movies with minimal rater " + minimalRater + " is");
        for(int i=0; i<avgRating.size(); i++){            
            if(avgRating.get(i).getValue() != 0){
                System.out.println(avgRating.get(i).getValue() + "   " + sr.getTitle(avgRating.get(i).getItem()));
            }            
        }
    }
    
    public void getAverageRatingOneMovie () {
        String moviefile = "data/ratedmoviesfull.csv"; 
        String ratingsfile = "data/ratings.csv";
        SecondRatings sr = new SecondRatings(moviefile, ratingsfile);
        //System.out.println("The total movies in the file are: " + sr.getMovieSize());
        //System.out.println("The total raters in the file are: " + sr.getRaterSize());
        int minimalRater = 0;
        ArrayList<Rating> avgRating = sr.getAverageRatings(minimalRater);        
        String movieTitle = "Vacation";
        System.out.println("The average rating of " + movieTitle + " is ");
        for(int i=0; i<avgRating.size(); i++){            
            if( sr.getTitle(avgRating.get(i).getItem()).equals(movieTitle)) {
                System.out.println(avgRating.get(i).getValue() + "   " + sr.getTitle(avgRating.get(i).getItem()));
            }            
        }
    }
    
    public void printAverageRatingsCopy () {
        String moviefile = "data/ratedmoviesfull.csv"; 
        String ratingsfile = "data/ratings.csv";
        SecondRatings sr = new SecondRatings(moviefile, ratingsfile);
        //System.out.println("The total movies in the file are: " + sr.getMovieSize());
        //System.out.println("The total raters in the file are: " + sr.getRaterSize());
        int minimalRater = 50;
        ArrayList<Rating> avgRating = sr.getAverageRatings(minimalRater);        
        System.out.println("The average rating per movies with minimal rater " + minimalRater + " is");
        double maxValue = 0.0;
        int maxIndex = 0;
        String id = "";
        ArrayList<Rating> avgRatingWithMinimalRater = new ArrayList<Rating>();
        for(int i=0; i<avgRating.size(); i++){            
            if(avgRating.get(i).getValue() != 0){
                if(maxValue < avgRating.get(i).getValue()){
                    maxIndex = i;                    
                }                
            }    
            maxValue = avgRating.get(maxIndex).getValue();
            id = sr.getTitle(avgRating.get(maxIndex).getItem());
            Rating rt = new Rating(id, maxValue);
            avgRatingWithMinimalRater.add(rt);
            avgRating.remove(i);
            //i = 0;
        }
        
        for (int j=0; j<avgRatingWithMinimalRater.size(); j++){
            System.out.println(avgRating.get(j).getValue() + "   " + sr.getTitle(avgRating.get(j).getItem()));
        }
    }
}
