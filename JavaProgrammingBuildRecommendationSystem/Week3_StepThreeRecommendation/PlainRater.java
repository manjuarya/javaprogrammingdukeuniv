
/**
 * Write a description of class Rater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
 
public class PlainRater implements Rater {
    private String myID;
    public ArrayList<Rating> myRatings; 

    
    public PlainRater(String id) { 
        myID = id;
        myRatings = new ArrayList<Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.add(new Rating(item,rating)); 
    }
    
    public ArrayList<Rating> getMyRatings() 
    {
        return myRatings;
    }

    public boolean hasRating(String item) {
        for(int k=0; k < myRatings.size(); k++){ 
            if (myRatings.get(k).getItem().equals(item)){
                return true;
            }
        }
        
        return false;
    }

    public String getID() { 
        return myID;
    }

    public double getRating(String item) {
        for(int k=0; k < myRatings.size(); k++){
            String m = myRatings.get(k).getItem();
            if (m.equals(item)){
                return myRatings.get(k).getValue();
            }
        }
        
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(int k=0; k < myRatings.size(); k++){
            list.add(myRatings.get(k).getItem());
        }
        
        return list;
    } 
}
