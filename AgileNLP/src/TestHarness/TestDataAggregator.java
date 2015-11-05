/***************************************************************************
* function(param 1, param 2)
* -----------------------------------
* Description
* What this function does
* Continued
* **************************************************************************
*/  

/***************************************************************************
* CLASS TestDataAggregator
* -----------------------------------
* Description
* Tests the DataAggregator
* This is one of Barry's tests
* **************************************************************************
*/  
package TestHarness;

import FileReader.DataAggregator;
import java.util.Date;
import java.util.Hashtable;

/**
 *
 * @author bdoyle
 */
public class TestDataAggregator {
    public static void run()
    {
        Date start_time;
        Date end_time;

        start_time = new Date();
        System.out.println("Start Time: " + start_time);
        
        // This gets the composite movie object
        Hashtable composite_movies = DataAggregator.GetCompositeMovies();
        end_time = new Date();

        //debugging code
        System.out.println("Composize Movie Array Size: " + composite_movies.size());
        System.out.println("End Time: " + end_time);
        
    }
    
}
