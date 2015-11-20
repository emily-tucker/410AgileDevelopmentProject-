/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QandA;

import Fact.Fact;
import java.util.ArrayList;
import Token.*;

/**
 *
 * @author stu852511 SO WHAT WE DONE HERE. Is we made a class that doesn't have
 * a constructor, that is private, and a main method. A bit like Tokenizer. It
 * takes a stream of tokens (a sentence), and then tries to process  to the
 * query dudes. This will not be plugged into the UI. Anyway. This looks at the
 * structure of a question. Like, if it's a who, what, where, when question.
 * Obviously we can't answer every question- WHY questions are right out. As are
 * oddly-worded ones. In the future, we'll be able to recognize questions that
 * start with verbs. Specifically, IS-verbs. Is, are, was, etc. We don't do that
 * yet.
 */
public class TokensToQuestion {

    private TokensToQuestion() {
    }

    public static Fact MakeQuestion(TokenStream stream) {
        String currentToken = stream.peek().body;
        Token nextToken = stream.away(2);
        //System.out.println(currentToken);
        //System.out.println(nextToken);
        //System.out.println("STARTING ANALISYS");
        
//       Wha
         Token yoda = new Token(TokenType.noun, "Yoda");
        Token is = new Token(TokenType.verb, "is");
        Token in = new Token(TokenType.verb, "in");
        Token Q = new Token(TokenType.adjective, "?");
        Token green = new Token(TokenType.adjective, "green");
        Token jedi = new Token(TokenType.noun, "jedi");
        Token starWars = new Token(TokenType.noun, "Star Wars");
        Token iJ = new Token(TokenType.noun, "Indiana Jones");
       
        Fact pool2 = new Fact(starWars, in, yoda);
        Fact pool3 = new Fact(green, is, yoda);
        Fact pool4 = new Fact(jedi, is, yoda);
        Fact query = new Fact(Q, is, yoda);
        Fact[] pool = {pool2,pool4,pool3};
        
        Fact returnFact = null;
        if (Tokenizer.isWhWord(currentToken)) {
            //System.out.println("Okay, so it's a WH-word.");
            if (currentToken.equalsIgnoreCase("who")) {
                //System.out.println("WE FOUND A 'WHO'");
                //System.out.println(nextToken);
                if (nextToken.type == TokenType.verb) {
                    //System.out.println("The next word is a verb! Let's get jiggy with it.");
                    TokenStream returnStream = new TokenStream();
                    returnStream.addToken(new Token(TokenType.propernoun, "?")); //Start building the response. A tokenstream, starting with an unknown person.
                    returnStream.addToken(nextToken); //Then we add in the verb we just found.
                    //System.out.println(returnStream.peek());
                    //System.out.println(returnStream.away(1));
                    //System.out.println(returnStream.away(2));
                    nounStripper(stream, returnStream); //Run it through our custom-made noun extractor...
                    returnFact = new Fact(returnStream.next(), returnStream.next(), returnStream.next());
                }
            } else if (currentToken.equalsIgnoreCase("what")) { //Similar to Who, but we return a noun of some kind. Not a proper noun, a regular one.
                //System.out.println("WE FOUND A 'WHAT'");
                if (nextToken.type == TokenType.verb) {
                    TokenStream returnStream = new TokenStream();
                    returnStream.addToken(new Token(TokenType.adjective, "?")); //Or adjective, whatever.
                    returnStream.addToken(nextToken);
                    nounStripper(stream, returnStream); //Run it through our custom-made noun extractor...
                    returnFact = new Fact(returnStream.next(), returnStream.next(), returnStream.next());

                }
            } else if (currentToken.equalsIgnoreCase("where")) { //Similar to Who, except with a 'place' as the return type.
                //System.out.println("WE FOUND A 'WHERE'");
                if (nextToken.type == TokenType.verb) {
                    TokenStream returnStream = new TokenStream();
                    returnStream.addToken(new Token(TokenType.propernoun, "?")); //Places are proper nouns, technically. If there's more functionality later, we'll add that in. For now it's just a proper noun.
                    returnStream.addToken(nextToken); //Then we add in the verb we just found.
                    nounStripper(stream, returnStream); //Run it through our custom-made noun extractor...
                    returnFact = new Fact(returnStream.next(), returnStream.next(), returnStream.next());
                }
            } else if (currentToken.equalsIgnoreCase("when")) {
                //System.out.println("WE FOUND A 'WHEN'");
                if (nextToken.type == TokenType.verb) {
                    TokenStream returnStream = new TokenStream(); //This is very similar to the Who question, except that it can only return a movie's release date, or date information.
                    returnStream.addToken(new Token(TokenType.number, "?")); //It's gotta be a number.
                    returnStream.addToken(nextToken); //Then we add in the verb we just found.
                    nounStripper(stream, returnStream); //Run it through our custom-made noun extractor...
                    returnFact = new Fact(returnStream.next(), returnStream.next(), returnStream.next());

                }
            } else if (currentToken.equalsIgnoreCase("why")) {
                //System.out.println("WE FOUND A 'WHY'");
                System.out.println("Do not ask me WHY. I cannot answer philosophical questions.");
                TokenStream returnStream = new TokenStream(); //WE CAN'T DO WHY'S.
                returnStream.addToken(new Token(TokenType.unknown, "NOPE"));
                returnStream.addToken(new Token(TokenType.unknown, "NOPE"));
                returnStream.addToken(new Token(TokenType.unknown, "NOPE"));//WE NEED THREE OKAY
                returnFact = new Fact(returnStream.next(), returnStream.next(), returnStream.next());
            }
        } else if (Tokenizer.isVerb(currentToken)) { //THIS ONE DOESN'T WORK YET
            //System.out.println("WE FOUND A VERB");
            TokenStream returnStream = new TokenStream();
            //THERE IS NO YES/NO, WE JUST FEED IT A COMPLETE FACT AND HOPE THAT IT MATCHES SOMETHING.
            returnStream.addToken(new Token(currentToken)); //We add in the verb we just found.
            nounStripper(stream, returnStream); //Run it through our custom-made noun extractor...
            returnFact = new Fact(returnStream.next(), returnStream.next(), returnStream.next());
        }
        if (returnFact == null) { //Failure clause. If nothing was found, the fact is still NULL.
            System.out.println("No question words recognized. Maybe it's a fact?");
            stream.next();
            returnFact = new Fact(stream.next(), stream.next(), stream.next());
        }
        System.out.println(returnFact.types[0]);
        System.out.println(returnFact);
        return returnFact.matchQuerey(pool);
    }

    public static void nounStripper(TokenStream inStream, TokenStream outStream) {
        Token currentToken;
        while (inStream.next() != Token.EOF) {
            currentToken = inStream.peek();
            if (currentToken.type == TokenType.noun || currentToken.type == TokenType.propernoun) {
                outStream.addToken(currentToken);
            }
        }

    }
}
