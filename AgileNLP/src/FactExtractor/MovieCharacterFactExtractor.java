/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FactExtractor;

/**
 *
 * @author bdoyle
 */
import Fact.Fact;
import FileReader.Movie;
import FileReader.MovieCharacter;
import java.util.ArrayList;
import Error.HandleError;
import Token.*;

public class MovieCharacterFactExtractor {

    public static ArrayList<Fact> CompileCharacterMovieFacts(Movie m, ArrayList<MovieCharacter> arraylist_c) {
        try {
            ArrayList<Fact> a = new ArrayList<Fact>();
            Fact f;

            for (MovieCharacter mc : arraylist_c) {
                //add base character fact
                String character_name = mc.character_name;
                f = new Fact(new Token(character_name), new Token("in"), new Token(m.movie_name));
                a.add(f);

                //lets split the names apart now
                if (character_name.contains(" ")) {
                    String[] names = character_name.split(" ");
                    for (String s : names) {
                        String name = s;
                        f = new Fact(new Token(name), new Token("in"), new Token(m.movie_name));
                        a.add(f);

                    }
                }

            }

            return a;
        } catch (Exception ex) {
            HandleError.HandleError(ex);
            return new ArrayList<Fact>();
        }
    }
}
