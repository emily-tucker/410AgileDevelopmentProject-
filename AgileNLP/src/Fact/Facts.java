/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fact;

import java.util.ArrayList;

/**
 *
 * @author Chris
 */
public class Facts {
    
    ArrayList<Fact> facts;
    
    public Facts(){
        facts = new ArrayList<>();
    }
    
    public void addFact(Fact f){
        facts.add(f);
    }
    public String toString(){
        String out = "";
        for(Fact f: facts){
            out += f.toString();
        } 
        return out;
    }
}
