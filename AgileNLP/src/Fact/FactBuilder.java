/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fact;
import Token.*;
import static Token.Tokenizer.isAdjective;
import static Token.Tokenizer.isAdverb;
import static Token.Tokenizer.isArticle;
import static Token.Tokenizer.isConjection;
import static Token.Tokenizer.isGerund;
import static Token.Tokenizer.isPreposition;
import static Token.Tokenizer.isProNoun;
import static Token.Tokenizer.isProperNoun;
import static Token.Tokenizer.isVerb;
import static Token.Tokenizer.isWhWord;
import static Token.Tokenizer.isYesNo;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.StringReader;

/**
 *
 * @author Chris
 */
public class FactBuilder {
    
    String corpus;
    Facts facts = new Facts();
    
    public FactBuilder(String c){
        corpus = c;
    }
    
    
}
