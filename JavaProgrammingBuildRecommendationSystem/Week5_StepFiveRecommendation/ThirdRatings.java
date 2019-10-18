
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class ThirdRatings {
    //private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings ft = new FirstRatings();
        //myMovies = ft.loadMovies(moviefile);
        myRaters = ft.loadRaters(ratingsfile);
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private double getAverageByID(String movieID, int minimalRaters) {
        double count = 0.0;
        double totalRatingPerMovieId = 0.0;
        for(int i=0; i<myRaters.size(); i++){
            double rating = myRaters.get(i).getRating(movieID);
            if(rating != -1) {
                totalRatingPerMovieId += rating;
                count += 1;
            }
        }
        
        double avgRating = 0.0;
        if(count >= minimalRaters){
            avgRating = totalRatingPerMovieId/count;
            return avgRating;
        }
        
        return 0.0;
    } 
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> averageRatingMovie = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(int j=0; j<movies.size(); j++) {
            String movieID = movies.get(j);
            double avgRating = getAverageByID( movieID, minimalRaters);
            Rating rt = new Rating(movieID,avgRating);
            averageRatingMovie.add(rt);           
        }
        return averageRatingMovie; 
    }
        
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) { 
        ArrayList<Rating> averageRatingMovie = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(int j=0; j<movies.size(); j++) {
            String movieID = movies.get(j);
            double avgRating = getAverageByID( movieID, minimalRaters);
            Rating rt = new Rating(movieID,avgRating);
            averageRatingMovie.add(rt); 
        }
        return averageRatingMovie;
    } 
}
