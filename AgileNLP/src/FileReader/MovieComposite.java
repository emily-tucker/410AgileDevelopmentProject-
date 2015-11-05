/*
PURPOSE:    Movie composite will be an object which represents all movie, character, plot summary, etc. objects merged together into one
            Single data entity.  Probably going to hash all of the values together as well, so that searching can easily be performed.
*/

package FileReader;

import java.util.ArrayList;

public class MovieComposite {
    public Movie movie;
    public PlotSummary plot_summary;
    public ArrayList characters = new ArrayList();

}
