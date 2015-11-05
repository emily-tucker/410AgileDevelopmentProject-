/*
PURPOSE:    UnitTest for the DataLoader
*/


package TestHarness;

import FileReader.DataLoader;
import java.util.ArrayList;
import java.util.Date;

public class TestDataLoader {
    public static void run()
    {
        Date start_time;
        Date end_time;

        start_time = new Date();
        ArrayList movies = DataLoader.GetMovies();
        ArrayList plotsummaries = DataLoader.GetPlotSummaries();
        ArrayList characters = DataLoader.GetCharacters();
        ArrayList namecluster = DataLoader.GetNameClusters();
        end_time = new Date();

        //debugging code
        System.out.println("Movie Array Size: " + movies.size());
        System.out.println("Plot Summaries Array Size: " + plotsummaries.size());
        System.out.println("Characters Array Size: " + characters.size());
        //not getting all the characters for some reason?
        System.out.println("Name Cluster Array Size: " + namecluster.size());
        System.out.println("Start Time: " + start_time);
        System.out.println("End Time: " + end_time);
        
    }
}
