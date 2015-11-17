/***************************************************************************
* function(param 1, param 2)
* -----------------------------------
* Description
* What this function does
* Continued
* **************************************************************************
*/  

/***************************************************************************
* CLASS MovieComposite
* -----------------------------------
* Description
* This is the most important class of this package.
* This object will store all the information about a movie, the characters,
* the plot and the movie information
* **************************************************************************
*/  

package FileReader;

import com.google.gson.Gson;
import java.util.ArrayList;

public class MovieComposite {
    public Movie movie =  new Movie();
    public PlotSummary plot_summary = new PlotSummary();
    public ArrayList characters = new ArrayList();
    public ArrayList facts = new ArrayList();
    
    public MovieComposite()
    {
        
    }

    public MovieComposite(String json)
    {
        Gson g = new Gson();
        
        MovieComposite mc = g.fromJson(json, MovieComposite.class);
        this.movie = mc.movie;
        this.plot_summary = mc.plot_summary;
        this.characters = mc.characters;
        this.facts = mc.facts;
    }

}
