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
 * takes a stream of tokens (a sentence), and then tries to process what kind of
 * question is being asked. It returns a FACT, which will be passed in to the query dudes.
 * This will not be plugged into the UI.
 * Anyway. This looks at the
 * structure of a question. Like, if it's a who, what, where, when question.
 * Obviously we can't answer every question- WHY questions are right out. As are
 * oddly-worded ones. In the future, we'll be able to recognize questions that
 * start with verbs. Specifically, IS-verbs. Is, are, was, etc. We don't do that
 * yet.
 */
public class TokensToQuestion {

    private TokensToQuestion() {
    }

    public Fact MakeQuestion(TokenStream stream) {
        String currentToken = stream.peek().body;
        Token nextToken = stream.away(1);
        if (Tokenizer.isWhWord(currentToken)) {
            if (currentToken.equals("who")) {
                if (nextToken.type == TokenType.verb) {
                    TokenStream returnStream = new TokenStream();
                    returnStream.addToken(new Token(TokenType.propernoun, "?")); //Start building the response. A tokenstream, starting with an unknown person.
                    returnStream.addToken(nextToken); //Then we add in the verb we just found.
                    nounStripper(stream, returnStream); //Run it through our custom-made noun extractor...
                    Fact returnFact = new Fact(returnStream.peek(), returnStream.next(), returnStream.next());
                    return returnFact;
                    
                }
            } else if (currentToken.equals("what")) { //Similar to Who, but we return a noun of some kind. Not a proper noun, a regular one.
                if (nextToken.type == TokenType.verb) {
                    TokenStream returnStream = new TokenStream();
                    returnStream.addToken(new Token(TokenType.noun, "?"));
                    returnStream.addToken(nextToken);
                    nounStripper(stream, returnStream); //Run it through our custom-made noun extractor...
                    Fact returnFact = new Fact(returnStream.peek(), returnStream.next(), returnStream.next());
                    return returnFact;
                }
            } else if (currentToken.equals("where")) { //Similar to Who, except with a 'place' as the return type.
                if (nextToken.type == TokenType.verb) {
                    TokenStream returnStream = new TokenStream();
                    returnStream.addToken(new Token(TokenType.propernoun, "unknown")); //Places are proper nouns, technically. If there's more functionality later, we'll add that in. For now it's just a proper noun.
                    returnStream.addToken(nextToken); //Then we add in the verb we just found.
                    nounStripper(stream, returnStream); //Run it through our custom-made noun extractor...
                    Fact returnFact = new Fact(returnStream.peek(), returnStream.next(), returnStream.next());
                    return returnFact;
                }
            } else if (currentToken.equals("when")) {
                if (nextToken.type == TokenType.verb) {
                    TokenStream returnStream = new TokenStream(); //This is very similar to the Who question, except that it can only return a movie's release date, or date information.
                    returnStream.addToken(new Token(TokenType.number, "?")); //It's gotta be a number.
                    nounStripper(stream, returnStream); //Run it through our custom-made noun extractor...
                    Fact returnFact = new Fact(returnStream.peek(), returnStream.next(), returnStream.next());
                    return returnFact;
                }
            } else if (currentToken.equals("why")) {
                TokenStream returnStream = new TokenStream(); //WE CAN'T DO WHY'S.
                returnStream.addToken(new Token(TokenType.unknown, "NOPE"));
                returnStream.addToken(new Token(TokenType.unknown, "NOPE"));
                returnStream.addToken(new Token(TokenType.unknown, "NOPE"));//WE NEED THREE OKAY
                Fact returnFact = new Fact(returnStream.peek(), returnStream.next(), returnStream.next());
                return returnFact;
            }
        } else if (Tokenizer.isVerb(currentToken)) { //THIS ONE DOESN'T WORK YET
            TokenStream returnStream = new TokenStream();
            //THERE IS NO YES/NO, WE JUST FEED IT A COMPLETE FACT AND HOPE THAT IT MATCHES SOMETHING.
            returnStream.addToken(new Token(currentToken)); //We add in the verb we just found.
            nounStripper(stream, returnStream); //Run it through our custom-made noun extractor...
            Fact returnFact = new Fact(returnStream.peek(), returnStream.next(), returnStream.next());
            return returnFact;
        }

        TokenStream returnStream = new TokenStream(); //Failure clause.
        returnStream.addToken(new Token(TokenType.unknown, "NOPE"));
        returnStream.addToken(new Token(TokenType.unknown, "NOPE"));
        returnStream.addToken(new Token(TokenType.unknown, "NOPE"));
        Fact returnFact = new Fact(returnStream.peek(), returnStream.next(), returnStream.next());
        return returnFact;
    }

    public void nounStripper(TokenStream inStream, TokenStream outStream) {
        Token currentToken;
        while (inStream.next() != Token.EOF) {
            currentToken = inStream.peek();
            if (currentToken.type == TokenType.noun) {
                outStream.addToken(currentToken);
            }
        }

    }
}
