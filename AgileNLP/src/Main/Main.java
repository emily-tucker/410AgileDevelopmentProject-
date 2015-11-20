package Main;

import Fact.Fact;
import Fact.FactBuilder;
import Fact.Facts;
import resources.MrMovieLogo;
import Token.Tokenizer;
import Token.Token;
import Token.TokenStream;
import Token.TokenType;
import QandA.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    /**
     * @param args the command line arguments
     */
    private static ArrayList<Fact> facts = new ArrayList<Fact>();
    
    public static void main(String[] args) 
    {
        // TODO code application logic here
        Scanner kybd = new Scanner(System.in);
        MrMovieLogo logo = new MrMovieLogo();
        boolean askAnother = true;
        System.out.println(logo);
        System.out.println("Welcome to Mr. Movie! ");
        System.out.println("");
        while (askAnother) {
            System.out.println("What would you like to know?\n");
            String question = kybd.nextLine();
            System.out.println("");
            System.out.println("You asked: " + question);
            System.out.println("");
            TokenStream toks = Tokenizer.lexer(question);
            toks = Tokenizer.tagger(toks);
            Fact f = TokensToQuestion.MakeQuestion(toks);
            System.out.println(f);
            
            //Facts database = FactBuilder.glean(toks);
            //toks = Tokenizer.parse(toks);
            
//            System.out.println("");
//            System.out.println("Here is the token stream: ");
//
//            Token t;
//            while ((t = toks.next()) != Token.EOF) {
//                System.out.println(" " + t + "   (" + t.type + ")");
//            }
//            System.out.println("");
            System.out.println("Here are the facts you produced: ");
            //System.out.println(database);
            System.out.println("Would you like to ask another? Y/N");
            if (kybd.nextLine().contains("N")) {
                askAnother = false;
            }

        }
        
    }
    
    public static void lets_do_simple_main()
    {
        Boolean b = Cache.Cache.FactCacheExists();
        
        if (!b)
        {
            Cache.Cache.PersistsFactsToCache();
        }
        
        facts = Cache.Cache.LoadFactCache();
        
        String input = get_input_from_user();
        String output = "";
        Token t = new Token(TokenType.unknown, input);

        for(Fact f: facts)
        {
            if(input.equals(f.bodies[0]))
            {
                output = input + " is " + f.bodies[1].toString() + " " + f.bodies[2];
            }

            if(input.equals(f.bodies[2]))
            {
                output = input + " is " + f.bodies[1].toString() + " " + f.bodies[0];
            }

        }
        
        System.out.println(output);
    }

    
    public static String get_input_from_user()
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Ask Western Geeeeves.......");
            String s = br.readLine();
            return s;
        }
        catch (Exception ex)
        {
            return "";
        }
    }
}
