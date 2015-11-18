/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestHarness;

import Fact.Fact;
import FileReader.DataAggregator;
import FileReader.MovieComposite;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author bdoyle
 */
public class TestCache {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) 
    {
        test_persist_facts_to_cache();
        load_facts_cache();
    }

    public static void test_persist_facts_to_cache()
    {
        System.out.println("Start Persist Fact Cache: " + new Date());
        Cache.Cache.PersistsFactsToCache();
        System.out.println("End Persist Fact Cache: " + new Date());
    }

    public static void load_facts_cache()
    {
        System.out.println("Start Load Fact Cache: " + new Date());
        ArrayList<Fact> facts = Cache.Cache.LoadFactCache();
        System.out.println("End Load Fact Cache: " + new Date());
    }
    public static void test_loading_cache()
    {
        Hashtable<Integer, MovieComposite> ht_composite_movies = DataAggregator.GetCompositeMovies();
        Cache.Cache.PersistMovieCompositeToCache(ht_composite_movies);
        
    }
    
    
}
