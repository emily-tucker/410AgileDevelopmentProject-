/***************************************************************************
* function(param 1, param 2)
* -----------------------------------
* Description
* What this function does
* Continued
* **************************************************************************
*/  

package FileReader;

import java.util.ArrayList;
import java.util.Hashtable;

/***************************************************************************
* CLASS CharacterLoader
* -----------------------------------
* Description
* This is to store the characters as a hashtable representation
* as well as an arraylist representation.
* **************************************************************************
*/

public class CharacterLoader {
    public ArrayList<MovieCharacter> CharactersArrayList;
    public Hashtable CharactersHashtable;
    
    public CharacterLoader()
    {
        CharactersArrayList = new ArrayList<MovieCharacter>();
        //CharactersHashtable = new Hashtable<String, MovieCharacter>();
        
    }

    /***************************************************************************
    * Add(MovieCharacter c)
    * -----------------------------------
    * Description
    * Populates the arraylist and hashtable from dataloader
    * **************************************************************************
    */
    protected void Add(MovieCharacter c)
    {
        if(c.freebase_character_id != "" &&  c.wikipedia_movie_id != 0)
        {
            

            CharactersArrayList.add(c);
            //CharactersHashtable.put(c.freebase_character_id, c);
        }
    }
}
