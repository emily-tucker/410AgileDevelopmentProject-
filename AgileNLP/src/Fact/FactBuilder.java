/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fact;
import Token.*;
import static Token.Tokenizer.*;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

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
    public static Facts glean(TokenStream toks){
        Facts facts = new Facts();
        if(toks.peek() == Token.EOF){
            return facts;
        }
        
        while (toks.peek().type != TokenType.propernoun){
            toks.next();
        }
        System.out.println("We found a propernoun: "+toks.peek());
        String nameOne = toks.next().body;
        while(toks.peek().type != TokenType.verb || (toks.peek().body == "," && toks.following().type==TokenType.article)){
            toks.next();
        }
        System.out.println("We found a linkinVerb: "+toks.peek());
        String linkingVerb = toks.next().body;
        
        while(toks.peek().type != TokenType.verb && toks.peek().body != "."){
            toks.next();
        }
        if(toks.peek().body != "."){
            System.out.println("We found a second proper noun: "+toks.peek());
            Fact f = new Fact(nameOne, linkingVerb, toks.peek().body);
            facts.addFact(f);
        }
        toks.next();
        return glean(toks);
        
    }
    
}
