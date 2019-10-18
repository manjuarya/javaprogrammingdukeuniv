
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientRater implements Rater{
    private String myID;
    //public ArrayList<Rating> myRatings;
    public HashMap<String,Rating> myMap;

    public EfficientRater(String id) { 
        myID = id;
        //myRatings = new ArrayList<Rating>();
        myMap = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) { 
        myMap.put(item,new Rating(item,rating));  
    }   

    public boolean hasRating(String item) {  
        if (myMap.get(item).getItem().equals(item)){
                return true;
        } 
        
        return false;
    }  

    public String getID(){  
        return myID;
    }

    public double getRating(String item) { 
        for(String id : myMap.keySet()){ 
            if (myMap.get(id).getItem().equals(item)){
                return myMap.get(id).getValue();
            } 
        }      
        return -1;
    }

    public int numRatings() { 
        return myMap.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String id : myMap.keySet()){ 
            list.add(myMap.get(id).getItem());
        }
        
        return list;
    }
    
    //this is a dump method just to make implements rater
    public ArrayList<Rating> getMyRatings(){
        ArrayList<Rating> myRatings = new ArrayList<Rating>();
        for(String id : myMap.keySet()){ 
            myRatings.add(myMap.get(id));
        }
        return myRatings;
    }
}
