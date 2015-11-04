/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agilenlp;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 *
 * @author bdoyle
 */
public class CharacterLoader {
    public ArrayList<MovieCharacter> CharactersArrayList;
    public Hashtable CharactersHashtable;
    
    public CharacterLoader()
    {
        CharactersArrayList = new ArrayList<MovieCharacter>();
        CharactersHashtable = new Hashtable<String, MovieCharacter>();
        
    }

    public void Add(MovieCharacter c)
    {
        if(c.freebase_character_id != "" &&  c.wikipedia_movie_id != 0)
        {
            

            CharactersArrayList.add(c);
            CharactersHashtable.put(c.freebase_character_id, c);
        }
    }
}
