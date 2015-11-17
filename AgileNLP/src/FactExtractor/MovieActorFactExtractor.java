
/**
 *
 * @author stu450355
 */
package FactExtractor;

import Fact.Fact;
import FileReader.Movie;
import FileReader.MovieCharacter;
import FileReader.NameCluster;
import java.util.ArrayList;
import Error.HandleError;
import Token.Token;
import Token.TokenType;

public class MovieActorFactExtractor {
    public static ArrayList<Fact> CompileActorMovieFacts(Movie m, ArrayList<MovieCharacter> arraylist_c)
    {
        try
        {
            ArrayList<Fact> a = new ArrayList<Fact>();
            Fact f;

            for(MovieCharacter mc: arraylist_c)
            {
                Token in = new Token(TokenType.verb, "in");
                Token actor = new Token(TokenType.propernoun,mc.actor_name );
                Token movie = new Token(TokenType.propernoun, m.movie_name);
                
                //add base character fact
                String actor_name = mc.actor_name;
                f = new Fact(actor,in, movie);
                a.add(f);

                f = new Fact(movie, in, actor);
                a.add(f);

                //lets split the names apart now
                if(actor_name.contains(" "))
                {
                    String [] names = actor_name.split(" ");
                    for(String s: names)
                    {
                        Token name = new Token(TokenType.propernoun, s);
                       
                        f = new Fact(name, in, movie);
                        a.add(f);

                        f = new Fact(movie, in, name);
                        a.add(f);

                    }
                }

            }

            return a;
        }
        catch (Exception ex)
        {
            HandleError.HandleError(ex);
            return new ArrayList<Fact>();
        }
    }
}

