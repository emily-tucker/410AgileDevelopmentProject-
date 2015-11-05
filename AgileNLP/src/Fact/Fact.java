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
public class Fact {
    String [] factoid = new String [3];
    
    public Fact(String n1, String v, String n2){
        factoid[0] = n1;
        factoid[1] = v;
        factoid[2] = n2;
    
    }
    public boolean isRelevant(String s){
        for(int i = 0; i < factoid.length; i++){
            if (factoid[i].equalsIgnoreCase(s))
                return true;
        
        }
        return false;
    }
    public String getRelative(String s){
        if(s.equalsIgnoreCase(factoid[0]))
            return factoid[2];
        return factoid[0];
    }
    public String toString(){
        return "" +factoid[0]+ " " + factoid[2] + " " + factoid[3] + "\n";
    
    }
}
