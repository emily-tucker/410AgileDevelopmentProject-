package Fact;

import FileReader.DataAggregator;
import FileReader.MovieCharacter;
import FileReader.MovieComposite;
import static TestHarness.OneWordParseTest.found;
//import static TestHarness.TestFacts.found;
import Token.*;
import static Token.Tokenizer.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class FactBuilder {

    String corpus;
    Facts facts = new Facts();
    public FactBuilder(String c) {
        corpus = c;
    }

    public static ArrayList<Token> glean(TokenStream toks) {
        ArrayList<Token> tar = new ArrayList<>();
        if (toks.peek() == Token.EOF) {
            return tar;
        }
        while (toks.peek().type != TokenType.propernoun) {
            toks.next();
        }
        Token first = toks.peek();
        tar.add(first);
        System.out.println("Found PN: " + first.body);

        while (toks.peek().type != TokenType.verb) {
            toks.next();
        }

        Token second = toks.peek();
        tar.add(second);
        System.out.println("Found A: " + second.body);
        while (toks.peek().type != TokenType.noun) {
            toks.next();
        }
        Token third = toks.peek();
        tar.add(third);
        System.out.println("Found N: " + third.body);

        if (toks.peek() == Token.EOF) {
            System.out.print("");
        }
        toks.next();
        return glean(toks);

    }

}
