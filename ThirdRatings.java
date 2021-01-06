
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    public ThirdRatings(String ratingfile){
        FirstRatings fr=new FirstRatings();
        myRaters=fr.loadRaters("data/"+ratingfile);
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    private double getAverageByID(String ID,int minimalRaters){
        double average=0,total=0;
        int countRater=0;
        for(Rater r : myRaters){
            if(r.hasRating(ID)){
                countRater+=1;
                total+=r.getRating(ID);
            }
        }
        if(countRater>= minimalRaters){
            average=total/countRater;
        }
        return average;
    }
    public ArrayList<Rating> getAverageRating(int minimalRaters){
        ArrayList<Rating> ans=new ArrayList<Rating>();
        ArrayList<String> movies=MovieDatabase.filterBy(new TrueFilter());
        for(String id : movies){
            double value=getAverageByID(id,minimalRaters);
            if(value>0.0){
                Rating rg=new Rating(id,value);
                ans.add(rg);
            }
        }
        return ans;
    }
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,
                                                    Filter filterCriteria){
       ArrayList<Rating> ans=new ArrayList<Rating>();
       ArrayList<String> movies=MovieDatabase.filterBy(filterCriteria);
       for(String id : movies){
           double avg=getAverageByID(id,minimalRaters);
           if(avg>0.0){
               Rating rg=new Rating(id,avg);
               ans.add(rg);
            }
        }
       return ans;
    }
}
