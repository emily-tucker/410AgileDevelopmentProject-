
package Main;

import resources.MrMovieLogo;
import Token.Tokenizer;
import Token.Token;
import Token.TokenStream;
import java.util.Scanner;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner kybd = new Scanner(System.in);
        MrMovieLogo logo = new MrMovieLogo();
        boolean askAnother = true;
        System.out.println(logo);
        System.out.println("Welcome to Mr. Movie! ");
        System.out.println("");
        while(askAnother){
            System.out.println("What would you like to know?\n");
            String question = kybd.nextLine();
            System.out.println("");
            System.out.println("You asked: " + question);
            System.out.println("");
            TokenStream toks = Tokenizer.tokenizePlot(question);

            Token t;
            System.out.println("");
            System.out.println("Here is the token stream: ");

            while ((t = toks.next()) != Token.EOF) {
                System.out.println(" " + t + "   (" + t.type + ")");
            }
            System.out.println("");
            System.out.println("Would you like to ask another? Y/N");
            if(kybd.nextLine().contains("N")){
                askAnother = false;
            }
            
        }
        
        
        
    }
    
}
