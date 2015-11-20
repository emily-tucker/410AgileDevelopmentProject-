/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fact;

import Fact.Fact;
import Token.Token;
import Token.TokenType;

/**
 *
 * @author SAM
 */
public class Tester {

    public static void main(String[] args) {

        Token yoda = new Token(TokenType.noun, "Yoda");
        Token is = new Token(TokenType.verb, "is");
        Token in = new Token(TokenType.verb, "in");
        Token Q = new Token(TokenType.adjective, "?");
        Token green = new Token(TokenType.adjective, "Green");
        Token jedi = new Token(TokenType.noun, "jedi");
        Token starWars = new Token(TokenType.noun, "Star Wars");
        Token iJ = new Token(TokenType.noun, "Indiana Jones");
        Token prof = new Token(TokenType.noun, "professor");
        Token dagoba = new Token(TokenType.noun, "Dagoba");
        Fact pool2 = new Fact(starWars, in, yoda);
        Fact pool3 = new Fact(green, is, yoda);
        Fact pool4 = new Fact(jedi, is, yoda);
        Fact pool5 = new Fact(iJ, is, in);
        Fact pool6 = new Fact(jedi, is, yoda);
        Fact pool7 = new Fact(jedi, in, starWars);
        Fact pool8 = new Fact(dagoba, is, starWars);
        Fact pool9 = new Fact(iJ, is, prof);
        Fact query = new Fact(yoda, in, jedi);
        
        
        Fact[] pool = {pool2, pool3, pool4, pool5, pool6, pool7, pool8, pool9};

        System.out.println(query);
        System.out.println(query.matchNouns(pool));

    }
}
