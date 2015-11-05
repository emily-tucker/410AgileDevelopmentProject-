/***************************************************************************
* function(param 1, param 2)
* -----------------------------------
* Description
* What this function does
* Continued
* **************************************************************************
*/  

/***************************************************************************
* CLASS MovieCharacter
* -----------------------------------
* Description
* Object which represents each individual charachter and all fields associated with the character
* THE CHARACHTER CLASS REPRESENTS ALL FIELDS LOADED VIA CHARACTER TSV FILE
* CONSTRUCTOR TAKES STRING ARRAY WHICH REPRESENTS SINGLE LINE FROM TSV FILE 
* DATA EXAMPLE
* 975900	/m/03vyhn	2001-08-24	Akooshay	1958-08-26	F	1.62		Wanda De Jesus	42
* **************************************************************************
*/


package FileReader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MovieCharacter {
    public int wikipedia_movie_id = 0;
    public String freebase_movie_id = "";
    public Date release_date = null;
    public String character_name = "";
    public Date actor_dob = null;
    public String actor_gender = "";
    public double actor_height_in_meters = 0;
    public String actor_ethnicity = "";
    public String actor_name = "";
    public int actor_age_at_movie_release = 0;
    public String freebase_character_actor_map_id = "";
    public String freebase_character_id = "";
    public String freebase_actor_id = "";
    
        
   public MovieCharacter()
   {
       
   }

   public MovieCharacter (String [] string_array)
    {
    //languages
    //countries
    //genres
        try
        {
            if(string_array.length == 13)
            {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

                wikipedia_movie_id = Integer.parseInt(string_array[0]);
                freebase_movie_id = string_array[1];
                release_date = format.parse(string_array[2]);
                character_name = string_array[3];
                actor_dob = format.parse(string_array[4]);
                actor_gender = string_array[5];
                actor_height_in_meters = Double.parseDouble(string_array[6]);
                actor_ethnicity = string_array[7];
                actor_name = string_array[8];
                actor_age_at_movie_release = Integer.parseInt(string_array[9]);
                freebase_character_actor_map_id = string_array[10];
                freebase_character_id = string_array[11];
                freebase_actor_id = string_array[12];
            }
        }
        catch (Exception ex)
        {
            //handle an error
        }
        
    }
}

