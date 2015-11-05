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
    public ArrayList<Token> tokens;
    public int here;
    public int size;
    public TokenStream() {
        tokens = new ArrayList<Token>();
        here = 0;
        size = 0;
    }
    public void addToken(Token t) {
        tokens.add(size, t);
        size++;
    }
    public Token next() {
        if (here < size) {
            Token res = tokens.get(here);
            here++;
            return res;
        }
        return Token.EOF;
    }
        public Token peek() {
        if (here < size) {
            return tokens.get(here);}
        else return Token.EOF;
    }
    
}
