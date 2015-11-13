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
import FileReader.FileIO;
import FileReader.MovieComposite;
import java.io.BufferedWriter;
import java.util.Hashtable;
import java.util.Set;
import com.google.gson.Gson;
import java.util.ArrayList;
import FileReader.DataLoader;

public class Cache {
    public static String cache_file = ".cache";
    
    public static Hashtable<String, MovieComposite> LoadFromCache(Hashtable<String, MovieComposite> ht)
    {
        Hashtable<String, MovieComposite> ht_loaded = new Hashtable<String, MovieComposite>();

        return ht_loaded;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<MovieComposite> LoadCacheArrayList()
    {
        ArrayList<MovieComposite> a = (ArrayList)FileReader.DataLoader.GetData(false, cache_file, "", DataLoader.DataType.array_list, DataLoader.ObjectType.movie_composite);
        System.out.println("Retrieved Records from Cache: " + a.size());
        return a;
    }
    
    public static Boolean PersistToCache(Hashtable<Integer, MovieComposite> ht)
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
