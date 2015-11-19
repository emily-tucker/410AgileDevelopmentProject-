/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fact;

/**
 *
 * @author Chris
 */
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import Token.*;

public class Fact {

    public String[] bodies = new String[3];
    public TokenType[] types = new TokenType[3];
    public Token unknown; //The unknown token

    public Fact(Token n1, Token v, Token n2) {
        //Bodies array is given the body (string)
        bodies[0] = n1.body;
        bodies[1] = v.body;
        bodies[2] = n2.body;
        //Type array is the relevant types (part of speech) 
        types[0] = n1.type;
        types[1] = v.type;
        types[2] = n2.type;

    }
    
    public void setQuery()
    {
        //Fill the unknown using the MS (Movie Searcher Function)
        //This is used by Tokens to Question
    }

    public boolean isRelevant(String s) {
        for (int i = 0; i < bodies.length; i++) {
            if (bodies[i].equalsIgnoreCase(s)) {
                return true;
            }

        }
        return false;
    }

    public String getRelative(String s) {
        if (s.equalsIgnoreCase(bodies[0])) {
            return bodies[2];
        }
        return bodies[0];
    }

    public String toString() {
        return "" + bodies[0] + " " + bodies[1] + " " + bodies[2] + "\n";

    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Fact)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        Fact rhs = (Fact) obj;
        return new EqualsBuilder().
                // if deriving: appendSuper(super.equals(obj)).
                append(bodies, rhs.bodies).
                isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
                // if deriving: appendSuper(super.hashCode()).
                append(bodies).
                toHashCode();
    }

    public Fact matchQuerey(Fact[] pool) { //Changed a little bit from the Wiki, now returns the matched Fact instead of a boolean from a matched fact
        if (this.bodies[2].equals("?")) {
            for (Fact x : pool) {
                System.out.println(x);
                System.out.println(this);
                if (this.types[2]==x.types[2]){
                    System.out.println("Type match.");
                    if(this.bodies[0].equals(x.bodies[0])){
                        System.out.println("Body 1 match.");
                        if(this.bodies[1].equals((x.bodies[1]))){
                            System.out.println("Body 2 match: Full match.");
                            return x;
                        }
                    }
                }
            }
        } else {
            for (Fact x : pool) {
                if (this.bodies[0].equals(x.bodies[0]) && this.bodies[1].equals(x.bodies[1]) && this.bodies[2].equals(x.bodies[2])) {
                    return x;
                }
            }
        }
        System.out.println("Could not find matching fact.");
        return null;
    }
}
