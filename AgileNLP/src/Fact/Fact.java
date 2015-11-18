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
        if (this.types[2] == TokenType.unknown) {
            for (Fact x : pool) {

                if (this.bodies[0] == x.bodies[0] && this.bodies[1] == (x.bodies[1])) {
                    return x;
                }
            }
        } else {
            for (Fact x : pool) {
                if (this.bodies[0] == x.bodies[0] && this.bodies[1] == x.bodies[1] && this.bodies[2] == x.bodies[2]) {
                    return x;
                }
            }
        }

        return null;
    }

}
