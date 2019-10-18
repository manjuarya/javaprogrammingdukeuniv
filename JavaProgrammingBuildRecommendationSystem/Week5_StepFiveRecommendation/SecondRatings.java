
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings ft = new FirstRatings();
        myMovies = ft.loadMovies(moviefile);
        myRaters = ft.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
    } 
   
    public int getRaterSize() {
        return myRaters.size();
    }
    
    /*private double getAverageByMinimumRating(String movieID, int minimumRating) {
        int count = 0;
        int totalRatingPerMovieId = 0;
        for(int i=0; i<myRaters.size(); i++){
            double rating = myRaters.get(i).getRating(movieID);
            if(rating >= minimumRating) {
                totalRatingPerMovieId += rating;
                count += 1;
            }
        }
        
        if(count == 0){
            return 0;
        }
        double avgRating = totalRatingPerMovieId/count;
        return avgRating;
    }*/ 
    
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
        for(int j=0; j<myMovies.size(); j++) {
            String movieID = myMovies.get(j).getID();
            double avgRating = getAverageByID( movieID, minimalRaters);
            Rating rt = new Rating(movieID,avgRating);
            averageRatingMovie.add(rt);
        }
        return averageRatingMovie; 
    }
    
    public String getTitle(String movieID) {
        for(int j=0; j<myMovies.size(); j++) {
            if(myMovies.get(j).getID().equals(movieID)) {
                return myMovies.get(j).getTitle();
            }            
        }
        return "ID was not found";
    }
    
    public String getID (String title) {
        for(int j=0; j<myMovies.size(); j++) {
            if(myMovies.get(j).getTitle().equals(title)) {
                return myMovies.get(j).getID();
            }            
        }
        return "NO SUCH TITLE";
    }
}
