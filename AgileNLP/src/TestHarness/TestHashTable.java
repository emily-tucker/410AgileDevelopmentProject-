/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestHarness;


import java.util.ArrayList;
import java.util.Hashtable;
//import com.google.gson.Gson;


/**
 *
 * @author bdoyle
 */
public class TestHashTable {

    public static void run()
    {
        //Hashtable<Movie, Integer> table = new Hashtable<Movie, Integer>();

        /*
        Hashtable<String, Integer> table = new Hashtable<String, Integer>();
        ArrayList<Movie> movies = DataLoader.GetMovies();
        int i = 0;
        
        for(Movie m: movies)
        {
            Gson gson = new Gson();
            String json = gson.toJson(m);
            table.put(json, i);
            i++;
        }
        table.contains("Spliced");
        System.out.println(table.get("Spliced"));                
        /*
        table.put("Daniel", 20);
        table.put("Chris", 18);
        table.put("Joseph", 16);        
        */
        //System.out.println(table.get("Daniel"));
    }
}
