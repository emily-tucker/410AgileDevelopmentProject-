/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QandA;

import java.util.ArrayList;
import Token.*;

/**
 *
 * @author stu852511 SO WHAT WE DONE HERE. Is we made a class that doesn't have
 * a constructor, that is private, and a main method. A bit like Tokenizer. It
 * takes a stream of tokens (a sentence), and then tries to process what kind of
 * question is being asked. It returns a string at the moment, because that's
 * what the user is going to expect. Imagine this plugged right into the UI.
 * That will probably change, though, because I have no clue how the
 * architecture of this project is going to turn out. Anyway. This looks at the
 * structure of a question. Like, if it's a who, what, where, when question.
 * Obviously we can't answer every question- WHY questions are right out. As are
 * oddly-worded ones. In the future, we'll be able to recognize questions that
 * start with verbs. Specifically, IS-verbs. Is, are, was, etc. We don't do that
 * yet.
 */
public class TokensToQuestion {

    private TokensToQuestion() {
    }

    public TokenStream MakeQuestion(TokenStream stream) {
        String currentToken = stream.peek().body;
        Token nextToken = stream.away(1);
        if (Tokenizer.isWhWord(currentToken)) {
            if (currentToken.equals("who")) {
                if (nextToken.type == TokenType.verb) {
                    TokenStream returnStream = new TokenStream();
                    returnStream.addToken(new Token(TokenType.propernoun, "unknown")); //Start building the response. A tokenstream, starting with an unknown person.
                    returnStream.addToken(nextToken); //Then we add in the verb we just found.
                    nounStripper(stream, returnStream); //Run it through our custom-made noun extractor...
                    return returnStream; //When we're done tacking on the nouns, we just send back the stream. Even if there weren't any. Hey, not our problem.
                }
            } else if (currentToken.equals("what")) { //Similar to Who, but we return a noun of some kind. Not a proper noun, a regular one.
                if (nextToken.type == TokenType.verb) {
                    TokenStream returnStream = new TokenStream();
                    returnStream.addToken(new Token(TokenType.noun, "unknown"));
                    returnStream.addToken(nextToken);
                    nounStripper(stream, returnStream); //Run it through our custom-made noun extractor...
                    //return "This is a question about WHAT " + nextToken + dump;
                    return returnStream;
                }
            } else if (currentToken.equals("where")) { //Similar to Who, except with a 'place' as the return type.
                if (nextToken.type == TokenType.verb) {
                    TokenStream returnStream = new TokenStream();
                    //returnStream.addToken(new Token(TokenType.place, "unknown")); //Start building the response. A tokenstream, starting with an unknown person.
                    returnStream.addToken(nextToken); //Then we add in the verb we just found.
                    nounStripper(stream, returnStream); //Run it through our custom-made noun extractor...
                    return returnStream; //RIGHT NOW WE DON'T HAVE THE STRUCTURE FOR RETURNING A PLACE.
                }
            } else if (currentToken.equals("when")) {
                if (nextToken.type == TokenType.verb) {
                    TokenStream returnStream = new TokenStream(); //This is very similar to the Who question, except that it can only return a movie's release date, or date information.
                    returnStream.addToken(new Token(TokenType.number, "unknown")); //It's gotta be a number.
                    nounStripper(stream, returnStream); //Run it through our custom-made noun extractor...
                    return returnStream; //And return.
                }
            } else if (currentToken.equals("why")) {
                TokenStream returnStream = new TokenStream(); //WE CAN'T DO WHY'S.
                returnStream.addToken(new Token(TokenType.unknown, "NOPE"));
                return returnStream;
            }
        } else if (Tokenizer.isVerb(currentToken)) { //THIS ONE DOESN'T WORK YET
            TokenStream returnStream = new TokenStream();
            //WE NEED A YES/NO HERE
            returnStream.addToken(new Token(TokenType.unknown, "unknown")); //Start building the response. A tokenstream, starting with WHAT IS SUPPOSED TO BE A YES/NO.
            returnStream.addToken(new Token(currentToken)); //Then we add in the verb we just found.
            nounStripper(stream, returnStream); //Run it through our custom-made noun extractor...
            return returnStream;
        }

        TokenStream returnStream = new TokenStream(); //Failure clause.
        returnStream.addToken(new Token(TokenType.unknown, "NOPE"));
        return returnStream;
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
