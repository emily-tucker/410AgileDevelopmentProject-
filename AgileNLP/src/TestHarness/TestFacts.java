package TestHarness;

import Fact.*;
import FileReader.DataAggregator;
import FileReader.MovieCharacter;
import FileReader.MovieComposite;
import Token.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Set;

public class TestFacts {

    public static void main(String[] args) {
        test2();
    }

    public static void found(MovieComposite m) {
        System.out.println("Found it! " + m.movie.movie_name);

        for (Object o : m.characters) {
            MovieCharacter c = (MovieCharacter) o;
            System.out.println("Character: " + c.character_name);
        }

        System.out.println(m.plot_summary.summary);

    }

    public static void test1() {

        String path = "C:\\Users\\stu450355\\Desktop\\starWarsSynopsis.txt";

        String file_contents = getContentsofFile(path);

// TokenStream ts1 = Tokenizer.lexer("What movie is Yoda in?");
        TokenStream ts2 = Tokenizer.tokenizePlot(file_contents);
        ts2 = Tokenizer.tagger(ts2);
        System.out.println("" + ts2.tokens);

        ArrayList<Token> d = FactBuilder.glean(ts2);

        System.out.println("" + d);

    }
    
    public static void test2()
    {
        Hashtable composite_movies = DataAggregator.GetCompositeMovies();

        Set<Integer> composite_movie_keys = composite_movies.keySet();  // movie key set  

        composite_movie_keys.stream().forEach((composite_movie_key) -> {
            MovieComposite cm1 = (MovieComposite)composite_movies.get(composite_movie_key);

            String plot_summary = cm1.plot_summary.summary;

            System.out.println(plot_summary);
            
            TokenStream ts2 = Tokenizer.tokenizePlot(plot_summary);
            ts2 = Tokenizer.tagger(ts2);
            System.out.println("" + ts2.tokens);

            ArrayList<Token> d = FactBuilder.glean(ts2);

            System.out.println("" + d);
            
        });
        
    }

    public static String getContentsofFile(String path) {

        try {

            Charset encoding = Charset.defaultCharset();

            byte[] encoded = Files.readAllBytes(Paths.get(path));

            return new String(encoded, encoding);

        } catch (Exception ex) {

            return "";

        }

    }

}
