
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        String moviename="ratedmovies_short.csv";
        String ratername="ratings_short.csv";
        FourthRatings fr=new FourthRatings();
        RaterDatabase.initialize(ratername);
        System.out.println("Number of Raters: "+ RaterDatabase.size());
        MovieDatabase.initialize(moviename);
        System.out.println("Number of Movies in DataBase: "+ MovieDatabase.size());
        int minimalRaters=0;
        ArrayList<Rating> avgRating=fr.getAverageRating(minimalRaters);
        Collections.sort(avgRating);
        System.out.println("Number of movies with minimal raters: "+avgRating.size());
        for(Rating rg : avgRating){
            System.out.println(rg.getValue()+" "+MovieDatabase.getTitle(rg.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        String moviename="ratedmovies_short.csv";
        String ratername="ratings_short.csv";
        FourthRatings fr=new FourthRatings();
        RaterDatabase.initialize(ratername);
        System.out.println("Number of Raters: "+ RaterDatabase.size());
        MovieDatabase.initialize(moviename);
        System.out.println("Number of Movies in DataBase: "+ MovieDatabase.size());
        int minimalRaters=0;
        int year=1990;
        String genre="Drama";
        Filter yf = new YearAfterFilter(year);
        Filter gf=new GenreFilter(genre);
        AllFilters filtersList = new AllFilters();
        filtersList.addFilter(yf);
        filtersList.addFilter(gf);
        ArrayList<Rating> list = fr.getAverageRatingsByFilter(minimalRaters,filtersList);
        Collections.sort(list);
        System.out.println("Number of movies with minimal raters and applied filter: "+list.size());
        /*for(Rating r : list){
            String Title = MovieDatabase.getTitle(r.getItem());
            String Genre = MovieDatabase.getGenres(r.getItem());
            int Year = MovieDatabase.getYear(r.getItem());

            System.out.println(r.getValue()+ " " + Year +" "+ Title );
            System.out.println("       "+ Genre);
        }*/
    }
    public void printSimilarRatings(){
        String moviename="ratedmoviesfull.csv ";
        String ratername="ratings.csv";
        FourthRatings fr=new FourthRatings();
        RaterDatabase.initialize(ratername);
        System.out.println("Number of Raters: "+ RaterDatabase.size());
        MovieDatabase.initialize(moviename);
        System.out.println("Number of Movies in DataBase: "+ MovieDatabase.size());
        String RaterID="71";
        int numSimilarRaters=20;
        int minimalRaters=5;
        ArrayList<Rating> list=fr.getSimilarRatings(RaterID,numSimilarRaters,minimalRaters);
        System.out.println("Number of Movies Similar: "+list.size());
        for(Rating r : list){
            System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
            
        }
    }
    public void printSimilarRatingsByGenre(){
        String moviename="ratedmoviesfull.csv ";
        String ratername="ratings.csv";
        FourthRatings fr=new FourthRatings();
        RaterDatabase.initialize(ratername);
        System.out.println("Number of Raters: "+ RaterDatabase.size());
        MovieDatabase.initialize(moviename);
        System.out.println("Number of Movies in DataBase: "+ MovieDatabase.size());
        String RaterID="964";
        int numSimilarRaters=20;
        int minimalRaters=5;
        String genre="Mystery";
        Filter fg=new GenreFilter(genre);
        ArrayList<Rating> list=fr.getSimilarRatingsByFilter(RaterID,numSimilarRaters,minimalRaters,fg);
        System.out.println("Number of Movies Similar: "+list.size());
        int i=1;
        for(Rating r : list){
            if(i<=15){
                System.out.println(i+" "+r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
                System.out.println("    "+MovieDatabase.getGenres(r.getItem()));
                i++;
            }
        }
    }
    public void printSimilarRatingsByDirector(){
        String moviename="ratedmoviesfull.csv ";
        String ratername="ratings.csv";
        FourthRatings fr=new FourthRatings();
        RaterDatabase.initialize(ratername);
        System.out.println("Number of Raters: "+ RaterDatabase.size());
        MovieDatabase.initialize(moviename);
        System.out.println("Number of Movies in DataBase: "+ MovieDatabase.size());
        String RaterID="120";
        int numSimilarRaters=10;
        int minimalRaters=2;
        String director="Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        Filter fg=new DirectorsFilter(director);
        ArrayList<Rating> list=fr.getSimilarRatingsByFilter(RaterID,numSimilarRaters,minimalRaters,fg);
        System.out.println("Number of Movies Similar: "+list.size());
        int i=1;
        for(Rating r : list){
            if(i<=15){
                System.out.println(i+" "+r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
                System.out.println("    "+MovieDatabase.getDirector(r.getItem()));
                i++;
            }
        }
    }
    public void printSimilarRatingsByGenreAndMinutes(){
        String moviename="ratedmoviesfull.csv ";
        String ratername="ratings.csv";
        FourthRatings fr=new FourthRatings();
        RaterDatabase.initialize(ratername);
        System.out.println("Number of Raters: "+ RaterDatabase.size());
        MovieDatabase.initialize(moviename);
        System.out.println("Number of Movies in DataBase: "+ MovieDatabase.size());
        String RaterID="168";
        int numSimilarRaters=10;
        int minimalRaters=3;
        int minMinutes=80,maxMinutes=160;
        String genre="Drama";
        Filter one=new MinutesFilter(minMinutes,maxMinutes);
        Filter two=new GenreFilter(genre);
        AllFilters fg=new AllFilters();
        fg.addFilter(one);
        fg.addFilter(two);
        ArrayList<Rating> list=fr.getSimilarRatingsByFilter(RaterID,numSimilarRaters,minimalRaters,fg);
        System.out.println("Number of Movies Similar: "+list.size());
        int i=1;
        for(Rating r : list){
            if(i<=15){
                System.out.println(i+" "+r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
                System.out.println("    "+MovieDatabase.getGenres(r.getItem()));
                i++;
            }
        }
    }
    public void printSimilarRatingsByYearAfterAndMinutes(){
        String moviename="ratedmoviesfull.csv ";
        String ratername="ratings.csv";
        FourthRatings fr=new FourthRatings();
        RaterDatabase.initialize(ratername);
        System.out.println("Number of Raters: "+ RaterDatabase.size());
        MovieDatabase.initialize(moviename);
        System.out.println("Number of Movies in DataBase: "+ MovieDatabase.size());
        String RaterID="314";
        int numSimilarRaters=10;
        int minimalRaters=5;
        int minMinutes=70,maxMinutes=200;
        int year=1975;
        Filter one=new MinutesFilter(minMinutes,maxMinutes);
        Filter two=new YearAfterFilter(year);
        AllFilters fg=new AllFilters();
        fg.addFilter(one);
        fg.addFilter(two);
        ArrayList<Rating> list=fr.getSimilarRatingsByFilter(RaterID,numSimilarRaters,minimalRaters,fg);
        System.out.println("Number of Movies Similar: "+list.size());
        int i=1;
        for(Rating r : list){
            if(i<=15){
                System.out.println(i+" "+r.getValue()+" "+MovieDatabase.getTitle(r.getItem())
                                    +" Year: "+MovieDatabase.getYear(r.getItem())+" "+
                                       " Minutes: "+MovieDatabase.getMinutes(r.getItem()));
                i++;
            }
        }
    }
}
