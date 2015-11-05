/*
 *This class contains a word and its type. In this case, our type will be its part of speech
 *This is the same token class used in Data Structures 
 * Author: Prof Peterson
 *
 */

package Token;
import Token.TokenType;
import static Token.TokenType.*;
public class Token {
    public TokenType type;
    public String body;

    public Token(TokenType type, String body) {
        this.type = type;
        this.body = body;
    }
    public String toString() {
        return body;
    }
    //noun, pronoun, verb, adjective, adverb, article, preposition, conjuntion, interjection;  
    public boolean sameToken(Token t) {
        return body.equals(t.body);
    }
    
    public boolean isNoun() {
        return type == noun;
    }
    public boolean isPronoun() {
        return type == pronoun;
    }
    public boolean isVerb() {
        return type == verb;
    }
    public boolean isAdjective() {
        return type == adjective;
    }
    public boolean isAdverb() {
        return type == adverb;
    }
    public boolean isArticle() {
        return type == article;
    }
    public boolean isPreposition() {
        return type == preposition;
    }
    public boolean isConjection() {
        return type == conjunction;
    }
    public boolean isIntergection() {
        return type == conjunction;
    }
    public boolean isEOF() {
        return this == EOF;
    }
    public static Token EOF = new Token(TokenType.punctuation, "<EOF>");
}
