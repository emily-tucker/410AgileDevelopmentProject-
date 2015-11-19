package Token;
import java.util.ArrayList;

/***************************************************************************
* function(param 1, param 2)
* -----------------------------------
* Description
* What this function does
* Continued
* **************************************************************************
*/  

/***************************************************************************
* TokenStream
* -----------------------------------
* Description
* This class holds a bunch of tokens in an array list and provides some additional functionality 
* including peek(), and next() which it does by keeping track of what token is currently being acted on
* This is the same tokenStream we used in 280 
* **************************************************************************
*/  

public class TokenStream {
    public ArrayList<Token> tokens; // The ArrayList of Tokens
    public int here;    // The index in the ArrayList
    public int size;    // Size of the Arraylist
    
    public TokenStream() {
        tokens = new ArrayList<Token>();
        here = 0;
        size = 0;
    }
    public void addToken(Token t) {
        tokens.add(size, t);
        size++;
    }
    
    /***************************************************************************
    * next()
    * -----------------------------------
    * Description
    * This will MOVE to the next token in the arraylist
    * This is destructive so do NOT call it until you want to move to the next token
    * **************************************************************************
    */  
    public Token next() {
        if (here < size) {
            Token res = tokens.get(here);
            here++;
            return res;
        }
        return Token.EOF;
    }
    
    /***************************************************************************
    * peek()
    * -----------------------------------
    * Description
    * This will peek at the next Token in the arraylist
    * This is NON-Destructive
    * **************************************************************************
    */  
    public Token peek() {
        if (here < size) {
            return tokens.get(here);}
        else return Token.EOF;
    }
     
    public Token away(int i) {
        if (here + i < size && here + i > 0) {
            return tokens.get(here + i);}
        else return Token.EOF;
    }//Testing git stuff
    
    public void deleteThis(){
        if (here < size) {
            tokens.remove(here);
            size--;
        }
        else System.out.println("here is greater than size");
    }
    
}
