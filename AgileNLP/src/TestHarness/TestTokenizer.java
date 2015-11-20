/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestHarness;

import Token.*;
import java.util.Scanner;
import resources.MrMovieLogo;

/**
 *
 * @author stu650304
 */
public class TestTokenizer {
    public static void main(String[] args) 
    {
    Scanner kybd = new Scanner(System.in);
      
        
            System.out.println("Input plot to tokenize: \n");
            String question = kybd.nextLine();
            
            System.out.println("");
            TokenStream toks = Tokenizer.tokenizePlot(question);
            toks = Tokenizer.tagger(toks);
            //Facts database = FactBuilder.glean(toks);
            //toks = Tokenizer.parse(toks);
            
            System.out.println("");
            System.out.println("Here is the token stream: ");

            Token t;
            while ((t = toks.next()) != Token.EOF) {
                System.out.println(" " + t + "   (" + t.type + ")");
            }
            System.out.println("");
            
            
            

        
    }
    
}
