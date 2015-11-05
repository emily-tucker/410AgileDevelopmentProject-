/**
 * *************************************************************************
 * function(param 1, param 2) ----------------------------------- Description
 * What this function does Continued
 * **************************************************************************
 */
package FileReader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * *************************************************************************
 * CLASS Movie ----------------------------------- PURPOSE: THE MOVIE CLASS
 * REPRESENTS ALL FIELDS LOADED VIA MOVIE TSV FILE
 *
 * NOTES: CONSTRUCTOR TAKES STRING ARRAY WHICH REPRESENTS SINGLE LINE FROM TSV
 * FILE  *
 * DATA EXAMPLE 975900	/m/03vyhn	Ghosts of Mars	2001-08-24	14010832	98.0
 * {"/m/02h40lc": "English Language"}
 * **************************************************************************
 */
public class Movie {

    public int wikipedia_movie_id = 0;
    public String freebase_movie_id = "";
    public String movie_name = "";
    public Date release_date = null;
    public int box_office_revenue = 0;
    public int runtime = 0;
    public String languages = "";
    public String countries = "";
    public String genres = "";

    public Movie() {

    }

    public Movie(String[] string_array) {
    //languages
        //countries
        //genres
        try {
            if (string_array.length == 9) {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

                wikipedia_movie_id = Integer.parseInt(string_array[0]);
                freebase_movie_id = string_array[1];
                movie_name = string_array[2];
                release_date = format.parse(string_array[3]);
                box_office_revenue = Integer.parseInt(string_array[4]);
                runtime = Integer.parseInt(string_array[5]);
                languages = string_array[6];
                countries = string_array[7];
                genres = string_array[8];
            }
        } catch (Exception ex) {
            //do some error handling here
        }
    }
}
