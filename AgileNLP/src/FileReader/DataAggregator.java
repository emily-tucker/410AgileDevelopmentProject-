/***************************************************************************
* function(param 1, param 2)
* -----------------------------------
* Description
* What this function does
* Continued
* **************************************************************************
*/

package FileReader;

import Error.HandleError;
import java.util.Date;
import java.util.Hashtable;
import java.util.Set;

/***************************************************************************
* CLASS DataAggregator
* -----------------------------------
* Description
* ------------------------------------
* DataAggregator is going to load all the data and then merge the data together into a single
* Entity that can be used for searching.
* Thinking about the idea of hashing at this juncture as well, but need to give that some thought.
* -------------------------------------------------------------------------------------------------
* PURPOSE
* -------------------------------------------------------------------------------------------------
* The idea behind it is movie will be merged with plot summary, and perhaps a list of character names for the movie, genres, etc.  
* So one object will contain ALL data merged together.
*            
* The DataAggregator will be responsible for this.
* The entity MovieComposite will be the object that contains all this data together.
*
* Lastly, the idea is that our main program will call into DataAggregator, return an array of MovieComposite objects.
* DataAggregator will call each loader function from DataLoader, and do it's magic munging together.
* **************************************************************************
*/  

public class DataAggregator {
    
    /***************************************************************************
    * GetCompositeMovies()
    * -----------------------------------
    * returns a hashtable of composite movie objects The composite movie
    * objects allow us to group character into movies It also the plot summary
    * with the movie name and ID
    * **************************************************************************
    */  
    @SuppressWarnings("unchecked")
    public static Hashtable GetCompositeMovies() {

        try
        {
            System.out.println("Starting Data Aggregator: " + new Date());

            //get movies
            Hashtable<Integer, Movie> movies = (Hashtable<Integer, Movie>) DataLoader.GetObjectData(DataLoader.DataType.hash_table, DataLoader.ObjectType.movie);
            System.out.println("Loaded Movies Hashtable: " + new Date());

            //get plot summaries
            Hashtable<Integer, PlotSummary> plot_summaries = (Hashtable<Integer, PlotSummary>) DataLoader.GetObjectData(DataLoader.DataType.hash_table, DataLoader.ObjectType.plot_summary);
            System.out.println("Loaded Plot Summaries: " + new Date());

            Hashtable<Integer, MovieComposite> composite_movies = new Hashtable<>();

            //get character loader
            CharacterLoader cl = DataLoader.GetCharacterLoader();
            System.out.println("Loaded Character Loader: " + new Date());

            Set<Integer> movie_keys = movies.keySet();  // movie key set  
            Set<String> character_keys; // character key set

            movie_keys.stream().forEach((movie_key) -> {
                MovieComposite mc = new MovieComposite();

                Movie m = (Movie) movies.get(movie_key);
                mc.movie = m;   // Assigns the movie object

                // This block will assign the plot summary portion
                if (plot_summaries.containsKey(m.wikipedia_movie_id)) {
                    PlotSummary ps = (PlotSummary) plot_summaries.get(m.wikipedia_movie_id);
                    if (ps != null) {
                        mc.plot_summary = ps;
                    }
                }

                composite_movies.put(movie_key, mc);
            });
            System.out.println("Completed Composite Movie Movie and Plot Summary Assignment: " + new Date());

            //loop over all the characters
            //determine if movie exists in our movie array
            //if so, go ahead and add into composite movies
            //get all the characters for the movie and put into an array list
            //loop over all characters array
            //do we have the movie in movie hashtable
            //get character object out of character hashtable
            //add to movie charachters array
            // This loop adds all the characters of a movie into an object to group them
            cl.CharactersArrayList.stream().forEach((c) -> {
                MovieComposite mc1 = (MovieComposite) composite_movies.get(c.wikipedia_movie_id);

                if (mc1 != null) {

                    mc1.characters.add(c);
                }

                //Boolean b = composite_movies.containsValue(c.wikipedia_movie_id);
                //if(b)
                //{
                //    Character c1 = (Character)cl.CharactersHashtable.get(c.freebase_character_id);
                //    cl.CharactersArrayList.add(c1);
                //}
                if (mc1 != null) {
                    mc1.characters.add(c);
                }
            });
            System.out.println("Completed Composite Movie Character Assignment: " + new Date());

            return composite_movies;
        }
        catch (Exception ex)
        {
            HandleError.HandleError(ex);
            return new Hashtable();
        }
    }
}
