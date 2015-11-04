
package agilenlp;

import java.util.Scanner;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner kybd = new Scanner(System.in);
        MrMovieLogo logo = new MrMovieLogo();
        System.out.println(logo);
        System.out.println("Welcome to Mr. Movie! ");
        System.out.println("");
        System.out.println("What would you like to know?");
        String question = kybd.nextLine();
        System.out.println("You asked: " + question);
        TokenStream toks = Tokenizer.lexer(question);

        Token t;
        
        while ((t = toks.next()) != Token.EOF) {
            System.out.println(" " + t + "   (" + t.type + ")");
        }
        
        
    }
    
}
