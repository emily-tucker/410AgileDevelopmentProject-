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
<<<<<<< HEAD
public class Fact {
    Token [] factoid = new Token [3];
    
    public Fact(Token n1, Token v, Token n2){
        factoid[0] = n1;
        factoid[1] = v;
        factoid[2] = n2;
    
    }
    public boolean isRelevant(Fact s){
        for(int i = 0; i < factoid.length; i++){
            if (factoid[i].body.equalsIgnoreCase(s.factoid[i].body))
=======

public class Fact {
    String [] bodies = new String [3];
    TokenType [] types = new TokenType[3];
    public Fact(Token n1, Token v, Token n2){
        //Bodies array is given the body (string)
        bodies[0] = n1.body;
        bodies[1] = v.body;
        bodies[2] = n2.body;
        //Type array is the relevant types (part of speech) 
        types[0] = n1.type;
        types[1] = v.type;
        types[2] = n2.type;
    
    }
    public boolean isRelevant(String s){
        for(int i = 0; i < bodies.length; i++){
            if (bodies[i].equalsIgnoreCase(s))
>>>>>>> refs/remotes/emily-tucker/master
                return true;
        
        }
        return false;
    }
<<<<<<< HEAD
    public Token getRelative(Fact s){
        if(factoid[0].body.equalsIgnoreCase(s.factoid[0].body))
            return factoid[2];
        return factoid[0];
=======
    public String getRelative(String s){
        if(s.equalsIgnoreCase(bodies[0]))
            return bodies[2];
        return bodies[0];
>>>>>>> refs/remotes/emily-tucker/master
    }
    public String toString(){
        return "" +bodies[0]+ " " + bodies[2] + " " + bodies[3] + "\n";
    
    }

    @Override
    public boolean equals(Object obj) {
       if (!(obj instanceof Fact))
            return false;
        if (obj == this)
            return true;

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
}
