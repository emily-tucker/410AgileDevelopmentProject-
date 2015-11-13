/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestHarness;

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
    public static void test_loading_cache()
    {
        Hashtable<Integer, MovieComposite> ht_composite_movies = DataAggregator.GetCompositeMovies();
        Cache.Cache.PersistToCache(ht_composite_movies);
        
        System.out.println("Reading records from cache now: " + new Date());
        ArrayList a_composite_movies = Cache.Cache.LoadCacheArrayList();
        System.out.println("Loaded records from cache: " + a_composite_movies.size() + "; " + new Date());
    }
}
