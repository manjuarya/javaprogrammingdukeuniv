
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FourthRatings {
    private ArrayList<Rater> myRaters;
    
    public FourthRatings() {
        // default constructor
        RaterDatabase.initialize("ratings.csv");
        myRaters = RaterDatabase.getRaters();
    }
    
    public FourthRatings(String ratingsfile) {
        RaterDatabase.initialize(ratingsfile);
        myRaters = RaterDatabase.getRaters();
    } 
    
    public int getRaterSize() {
        return RaterDatabase.size();
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
    
    private ArrayList<Rating> getRatingIndividual(Rater m){
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> movies = m.getItemsRated();
        for (int i=0; i<movies.size(); i++){
            String movie = movies.get(i);
            double rating = m.getRating(movie);
            Rating rt = new Rating(movie, rating);
            ratings.add(rt);
        }
        return ratings;
    }
    
    private double dotProduct(Rater me, Rater r) {
        ArrayList<Rating> newMeRatings = new ArrayList<Rating>();
        ArrayList<Rating> meRatings = getRatingIndividual(me);
        for(int k=0; k<meRatings.size(); k++){
            String item = meRatings.get(k).getItem();
            double newRating = meRatings.get(k).getValue() -5;
            Rating rt = new Rating(item, newRating);
            newMeRatings.add(rt);
        }
        
        ArrayList<Rating> newRRatings = new ArrayList<Rating>(); 
        ArrayList<Rating> rRatings = getRatingIndividual(r);
        for(int k=0; k<rRatings.size(); k++){
            String item = rRatings.get(k).getItem();
            double newRating = rRatings.get(k).getValue() -5;
            Rating rt = new Rating(item, newRating); 
            newRRatings.add(rt);
        }
        
        double sum = 0.0;
        for(int i=0; i<newMeRatings.size(); i++){
            for(int j=0; j<newRRatings.size(); j++){
                if(newMeRatings.get(i).getItem().equals(newRRatings.get(j).getItem())){ 
                    sum += (newMeRatings.get(i).getValue())*(newRRatings.get(j).getValue());                    
                }
            }
        } 
        return sum;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters()){
            if(me != r){
                double dp = dotProduct(me, r);
                //System.out.println(dp);
                if(dp >=0){
                    String it = r.getID();
                    Rating raterSimilarity = new Rating(it, dp);
                    list.add(raterSimilarity);
                }                
            }
        }
        Collections.sort(list, Collections.reverseOrder());        
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String raterId, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> listItemWithWeightedAvgRating = new ArrayList<Rating>();
        ArrayList<Rating> list = getSimilarities(raterId);
        /*for(int j=0; j<list.size(); j++){
            System.out.println(j + "   " + list.get(j));
        }*/
        
        ArrayList<Rating> listWeighted = new ArrayList<Rating>();
        for(int i=0; i<numSimilarRaters; i++){
            String idRater = list.get(i).getItem();
            double dotValue = list.get(i).getValue();
            Rater rt = RaterDatabase.getRater(idRater);
            ArrayList<Rating> raterRatings = getRatingIndividual(rt);
            for(int j=0; j<raterRatings.size(); j++){
                String itemMovie = raterRatings.get(j).getItem();
                double value = raterRatings.get(j).getValue();
                value = value*dotValue;
                Rating ratingObject = new Rating(itemMovie, value);
                listWeighted.add(ratingObject);
            }
        }
        
        /*for(int k=0; k<listWeighted.size(); k++){
            System.out.println(listWeighted.get(k).getItem() + "   " + listWeighted.get(k).getValue());
        }*/
        
        ArrayList<String> uniqueMovieInListWeighted = new ArrayList<String>();
        for(int x=0; x<listWeighted.size(); x++){
            if(!uniqueMovieInListWeighted.contains(listWeighted.get(x).getItem())){
                uniqueMovieInListWeighted.add(listWeighted.get(x).getItem());
            }
        }
        /*for(int k=0; k<uniqueMovieInListWeighted.size(); k++){
            System.out.println(k + "   " + uniqueMovieInListWeighted.get(k));
        }*/
        
        int count = 0;
        double total = 0.0;
        for(int i=0; i<uniqueMovieInListWeighted.size(); i++){  
            String mId = uniqueMovieInListWeighted.get(i);
            
            for(int j=0; j<listWeighted.size(); j++){
                if(mId.equals(listWeighted.get(j).getItem())) {
                   count += 1;
                   total = total + listWeighted.get(j).getValue();
                }
            }
            if(count>=minimalRaters){
                total = total/count;
                Rating rt = new Rating(mId, total);
                listItemWithWeightedAvgRating.add(rt);
            }
            
            count = 0;
            total = 0.0;
        }
        return listItemWithWeightedAvgRating;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String raterId, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> listItemWithWeightedAvgRating = getSimilarRatings( raterId, numSimilarRaters, minimalRaters);
        ArrayList<Rating> itemsWithWeightedAvgRatingWithFilter = new ArrayList<Rating>();
        for(int j=0; j<movies.size(); j++) {
            String movieID = movies.get(j);
            for(int k=0; k<listItemWithWeightedAvgRating.size(); k++) {
                if(movieID.equals(listItemWithWeightedAvgRating.get(k).getItem())){
                    Rating mv = listItemWithWeightedAvgRating.get(k);
                    itemsWithWeightedAvgRatingWithFilter.add(mv);
                }            
            }
        }
        return itemsWithWeightedAvgRatingWithFilter;
    }
    
}
