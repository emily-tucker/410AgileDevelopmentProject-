/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestHarness;

import FileReader.DataAggregator;
import FileReader.*;
import FileReader.MovieComposite;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Set;

public class OneWordParseTest {

    public static void found(MovieComposite m) {
        System.out.println("Found it!  " + m.movie.movie_name);
        for(Object o: m.characters)
        {
            MovieCharacter c = (MovieCharacter) o;
            System.out.println("Character: " + c.character_name);
        }
        System.out.println(m.plot_summary.summary);

    }

    public static void main(String[] args) {
        System.out.println("Start main");

        Hashtable movieList = DataAggregator.GetCompositeMovies();

        String initial = "star";
        String query = initial.toLowerCase();

        boolean found = false;
        Set<Integer> keys = movieList.keySet();
        for (Integer key : keys) {
            MovieComposite cm = (MovieComposite) movieList.get(key);
            if (cm.movie.movie_name.toLowerCase().contains(query.toLowerCase())) {
                found(cm);
                found = true;
            }
//            if (cm.plot_summary.summary.toLowerCase().contains(query)) {
//                found(cm);
//                found = true;
//            }
//            for (Object o : cm.characters) {
//                MovieCharacter c = (MovieCharacter) o;
//                if (c.character_name.toLowerCase().contains(query)) {
//                    found(cm);
//                    break;
//                }
//            }
            if (found) {
                break;
            }
        }
    }
}
