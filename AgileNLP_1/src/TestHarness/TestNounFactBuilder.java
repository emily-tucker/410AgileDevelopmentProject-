/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestHarness;

import FileReader.DataAggregator;
import FileReader.MovieComposite;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author bdoyle
 */
@SuppressWarnings("unchecked")

public class TestNounFactBuilder 
{
    public static void main(String[] args) {
        //get my composite movies data structure hash table
        Hashtable movieList = DataAggregator.GetCompositeMovies();

        //get a facts array list from the console entered by user
        ArrayList<SimpleTestFact> input_facts = getFactsFromConsole();

        //get keys to loop over hash table of composite movies
        Set<Integer> keys = movieList.keySet();

        //initialize some variables for usage in loop below
        Boolean b_found = false;
        int movie_hash_code = 0;
        int input_hash_code = 0;
        MovieComposite cm = new MovieComposite();
        
        for (Integer key : keys) 
        {
            cm = (MovieComposite) movieList.get(key);

            //get the facts for the movie
            ArrayList<SimpleTestFact> movie_facts = BuildNounFactArrayList(cm.plot_summary.summary);
            
            for (SimpleTestFact input_fact: input_facts)
            {
                input_hash_code = input_fact.hashCode();
                
                for(SimpleTestFact movie_fact: movie_facts)
                {
                    /*
                    movie_hash_code = movie_fact.hashCode();

                    if(input_hash_code == movie_hash_code)
                    {
                        b_found = true;
                        break;
                    }
                    */
                    
                    if(input_fact.fact_1.equals(movie_fact.fact_1) && input_fact.fact_2.equals(movie_fact.fact_2))
                    {
                        b_found = true;
                        break;                        
                    }
                }
                
                if(b_found)
                    break;
            }
            
            if(b_found)
                break;
        }
        
        if(b_found)
        {
            System.out.println("I FOUND IT!  YEEHAW!!!!!!!!!!!!!!!!!!!!!!!!1");
            System.out.println("Your movie is: " + cm.movie.movie_name);
        }
        else
        {
            System.out.println("DAMNIT!!!!!");            
        }
    }

    
    public static ArrayList getFactsFromConsole()
    {
        try
        {
            //write message to user
            System.out.print("Please enter a string for crying out loud: ");

            //read in from user
            InputStreamReader inputStreamReader = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String input = reader.readLine();

            //initialize array list to be used
            ArrayList input_facts = new ArrayList();

            if(input.length() > 0)
            {
                input_facts = BuildNounFactArrayList(input);

                if(input_facts.size() == 0)
                {
                    System.out.println("YOU MAKE ME SO ANGRY!!!!!!!!!!!!!!!!!!!!!!!  LISTEN!!!!!!!!!!!!!!!!!!!!!!!!!");

                }
            }
            else
            {
                System.out.println("WHY MUST YOU BE THAT WAY!!!!!!!!!!!!!!!!!!!!!!!");
            }

            return input_facts;

        }
        catch (Exception ex)
        {
            return new ArrayList();
        }
    }
        
        public static ArrayList BuildNounFactArrayList(String input_string)
    {
        ArrayList a = new ArrayList();

        String [] s_split = input_string.split(" ");
        
        Boolean b_first = false;
        String s_first = "";
        String s_last = "";
        String s_guid = "";
        
        int i = 0;
        
        for(String s: s_split)
        {
            Boolean b_match = s.matches("^[A-Z].*");
            
            //clean it up by removing all non characters and numbers (R2D2)
            s = s.replaceAll("[^a-zA-Z]", "");
            
            if(b_match)
            {
                if(s_first.length() == 0)
                {
                    //haven't assigned first yet, let's do so
                    s_first = s;
                }
                else
                {
                    s_last = s;

                    SimpleTestFact f = new SimpleTestFact(s_first, s_last);
                    
                    a.add(f);

                    //reset the first to the last word loaded.
                    s_first = s_last;
                }
            }
        }
        return a;
        
    }

}


