/*
 *This class simply contains all the types a token can be. There is no functional difference between token types, so we use an enum instead of a class
 *TokenType.java
 *
 * Created on November 1, 2015
 *
 * 
 * 
 */

package agilenlp;

/**
 *
 * @author Chris Esterlein
 */
public enum TokenType {
    noun, pronoun, propernoun, verb, adjective, adverb, article, preposition, conjunction, interjection, number, punctuation, unknown;  
}
