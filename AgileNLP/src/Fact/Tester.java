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
        Token Q = new Token(TokenType.unknown, "?");
        Token green = new Token(TokenType.noun, "Green");
        Token starWars = new Token(TokenType.noun, "Star Wars");
        Token iJ = new Token(TokenType.noun, "Indiana Jones");
        Fact pool1 = new Fact(yoda, is, green);
        Fact pool2 = new Fact(yoda, in, starWars);
        Fact query = new Fact(yoda, is, Q);
        Fact[] pool = {pool2, pool1};
        
        System.out.println(query);
        System.out.println(query.ms(pool));

    }
}
