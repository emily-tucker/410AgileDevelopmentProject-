/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cache;

/**
 *
 * @author bdoyle
 */
import Error.HandleError;
import Fact.Fact;
import FileReader.DataAggregator;
import FileReader.FileIO;
import FileReader.MovieComposite;
import java.io.BufferedWriter;
import java.util.Hashtable;
import java.util.Set;
import com.google.gson.Gson;
import java.util.ArrayList;
import FileReader.DataLoader;
import java.util.Date;
import Error.HandleError;
import java.io.BufferedReader;
import java.io.File;

public class Cache {
    public static String cache_file = ".cache";
    public static String fact_cache_file = ".fact.cache";
    

    
    public static Boolean PersistsFactsToCache()
    {
        try
        {
            Boolean b = false;
            Gson g = new Gson();
            int i = 0;
            
            Hashtable<Integer, MovieComposite> ht_composite_movies = DataAggregator.GetCompositeMovies();
            Set<Integer> composite_movie_keys = ht_composite_movies.keySet();  // movie key set  
            BufferedWriter bw = FileIO.GetBufferedWriter(fact_cache_file);
            ArrayList<Fact> facts_composite = new ArrayList<Fact>();

            composite_movie_keys.stream().forEach((composite_movie_key) -> {
                MovieComposite cm1 = (MovieComposite)ht_composite_movies.get(composite_movie_key);
                facts_composite.addAll(cm1.facts);
            });

            //now that I have all facts, lets loop over them and write to cache
            for(Fact f: facts_composite)
            {
                    String json = g.toJson(f);

                    try
                    {
                        bw.write(json);
                        bw.newLine();
                        bw.flush();
                    }
                    catch (Exception ex)
                    {
                        HandleError.HandleError(ex);
                    }
                    i = i + 1;
            }

            bw.close();
            
            System.out.println(facts_composite.size());
            System.out.println("done" + new Date());
            return b;
        }
        catch (Exception ex)
        {
            HandleError.HandleError(ex);
            return false;
        }
        
    }

    public static ArrayList<Fact> LoadFactCache()
    {
        ArrayList<Fact> facts = new ArrayList<Fact>();
        
        BufferedReader reader;
        String read_line = "";
        String temp = "";
        Gson g = new Gson();
        
        try
        {
            reader = FileIO.GetBufferedReader(false, fact_cache_file);

            while ((read_line = reader.readLine()) != null) {
                Fact f = (Fact)g.fromJson(read_line, Fact.class);
                facts.add(f);
            }
        }
        catch (Exception ex)
        {
            HandleError.HandleError(ex);
        }
        
        return facts;
        
    }
    
    public static Boolean FactCacheExists()
    {
        File varTmpDir = new File(fact_cache_file); 
        return varTmpDir.exists();        
    }
    
    public static Boolean PersistMovieCompositeToCache(Hashtable<Integer, MovieComposite> ht)
    {
        Boolean b = false;
        Gson g = new Gson();
        Set<Integer> composite_movie_keys = ht.keySet();  // movie key set  
        BufferedWriter bw = FileIO.GetBufferedWriter(cache_file);

        composite_movie_keys.stream().forEach((composite_movie_key) -> {
            MovieComposite cm1 = (MovieComposite)ht.get(composite_movie_key);
            String json = g.toJson(cm1);

            try
            {
                bw.write(json);
                bw.newLine();
            }
            catch (Exception ex)
            {

            }

        });

        System.out.println("Written records to cache: " + ht.size());
        return b;
    }
}
