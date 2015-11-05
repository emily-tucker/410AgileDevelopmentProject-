/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestHarness;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class OneWordParseTest {

/*
    A one word parse test that takes a single word/phrase querey, and returns an Array List of MovieComposite
    objects that contain that the querey. Currently untested, but leaves little room for error. 
    
    Usage: ArrayList<MovieComposite> foo = Class.parseOneWord()
    
    This creates a new array list with a parsed set of data, prompt is included in the method. May be more
    practical to move the prompt later. 
    */
    public static void main(String[] args) {
        /*
        ArrayList<MovieComposite> selectedMovies = parseOneWord();
        for(MovieComposite m: selectedMovies )
        {
            System.out.print("Movies are: " +m.movie.movie_name+",");   
        }
    }

    public static ArrayList<MovieComposite> parseOneWord() {
        Hashtable movies = DataAggregator.GetCompositeMovies();
        
        Scanner input = new Scanner(System.in);
        System.out.print("Type a keyword to query");
        String query = input.next();

        ArrayList<MovieComposite> parsedTitles = new ArrayList<>();
        
        for(MovieComposite m: movies)
        {
            if(m.plot_summary.summary.toLowerCase().contains(query))
            {
                parsedTitles.add(m);
            }
            else if(m.movie.movie_name.toLowerCase().contains(query))
            {
                parsedTitles.add(m);
            }
        }
        return parsedTitles;
                */
    }
}
