
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {
    private double getAverageByID(String ID,int minimalRaters){
        double average=0,total=0;
        int countRater=0;
        for(Rater r : RaterDatabase.getRaters()){
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
    
    private double dotProduct(Rater me,Rater r){
        double ans=0;
        ArrayList<String> itemRatedByme=me.getItemsRated();
        for(String itemID : itemRatedByme){
            if(r.hasRating(itemID)){
                double rRating=r.getRating(itemID)-5;
                double myRating=me.getRating(itemID)-5;
                ans=ans+(rRating*myRating);
            }
        }
        return ans;
    }
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> ans =new ArrayList<Rating>();
        Rater me=RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters()){
            String raterID=r.getID();
            if(!raterID.equals(id)){
                double similarValue=dotProduct(me,r);
                if(similarValue>0){
                    Rating similarRating=new Rating(raterID,similarValue);
                    ans.add(similarRating);
                }
            }
        }
        Collections.sort(ans,Collections.reverseOrder());
        return ans;
    }
    public ArrayList<Rating> getSimilarRatings(String id,int numSimilarRaters,int minimalRaters){
         ArrayList<Rating> ans =new ArrayList<Rating>();
         ArrayList<Rating> simList=getSimilarities(id);
         ArrayList<String> movieList=MovieDatabase.filterBy(new TrueFilter());
         for(String mvid : movieList){
            double weightedAverage=0;
            double sum=0;
            int countRaters=0;
            for(int i=0;i<numSimilarRaters;i++){
                Rating r=simList.get(i);
                double weight=r.getValue();
                String raterID = r.getItem();
                Rater myRater = RaterDatabase.getRater(raterID);
                if(myRater.hasRating(mvid)){
                    countRaters++;
                    sum=sum+(weight*myRater.getRating(mvid));
                }
            }
            if(countRaters>=minimalRaters){
                weightedAverage=sum/countRaters;
                ans.add(new Rating(mvid,weightedAverage));
            }
         }
         Collections.sort(ans,Collections.reverseOrder());
         return ans;
    }
    public ArrayList<Rating>  getSimilarRatingsByFilter(String id,int numSimilarRaters,
                                    int minimalRaters,Filter filterCriteria){
         ArrayList<Rating> ans =new ArrayList<Rating>();
         ArrayList<Rating> simList=getSimilarities(id);
         ArrayList<String> movieList=MovieDatabase.filterBy(filterCriteria);
         for(String mvid : movieList){
            double weightedAverage=0;
            double sum=0;
            int countRaters=0;
            for(int i=0;i<numSimilarRaters;i++){
                Rating r=simList.get(i);
                double weight=r.getValue();
                String raterID = r.getItem();
                Rater myRater = RaterDatabase.getRater(raterID);
                if(myRater.hasRating(mvid)){
                    countRaters++;
                    sum=sum+(weight*myRater.getRating(mvid));
                }
            }
            if(countRaters>=minimalRaters){
                weightedAverage=sum/countRaters;
                ans.add(new Rating(mvid,weightedAverage));
            }
         }
         Collections.sort(ans,Collections.reverseOrder());
         return ans;
    }
}
