/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Unsorted;

/**
 *
 * @author stu616647
 */
public class QueryBuilder {
    public FactFactory ff;
    public TokensToQuestion ttq;
    public Fact f;
    public MovieSearcher m;
    
    public QueryBuilder(){
        //insert defaults here
    }
    
    public String getAnswer(){
        return null;//use the fact and the MovieSearcher to get an answer
    }
    
    public void update(TokenStream ts){
        //use the current token stream from the input to give us a fact from our factory
    }
}
