/***************************************************************************
* function(param 1, param 2)
* -----------------------------------
* Description
* What this function does
* Continued
* **************************************************************************
*/  

/***************************************************************************
* CLASS Tokenizer
* -----------------------------------
* Description
* Brakes things into words and pairs them with their type.
* Brakes a String into words and finds their type to create tokens.
* The String is the entire question from the user.
* **************************************************************************
*/  

package Token;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class Tokenizer {
    
    /*
    * 
    *The following functions test whether a word is a certain part of speech, isAdjective does nothing because we cant guess all the adjectives someoneone could use
    */
    public static boolean isProNoun(String s){
        String pronouns = "I you he she we it they us them me her him myself himself themselves mine ours yours who whom";
        String [] words = pronouns.split(" ");
        
        for(int i = 0; i < words.length; i ++){
            if(words[i].equalsIgnoreCase(s)){
                return true;
            }
        }//for
            
        return false;
    }
    public static boolean isProperNoun(String s){
        return Character.isUpperCase(s.charAt(0));
    }
    public static boolean isAdjective(String s){
        //this is clearly not right btw
        String adjective = "his hers what red orange yellow green blue purple";
        String [] words = adjective.split(" ");
        
        for(int i = 0; i < words.length; i ++){
            if(words[i].equalsIgnoreCase(s)){
                return true;
            }
        }//for
            
        return false;
    }
    public static boolean isConjection(String s){
        String conjunction = "and but or neither nor either";
        String [] words = conjunction.split(" ");
        
        for(int i = 0; i < words.length; i ++){
            if(words[i].equalsIgnoreCase(s)){
                return true;
            }
        }//for
            
        return false;
    }
    public static boolean isVerb(String s){
        String verb = "'s was is are has does did have had in";
        String [] words = verb.split(" ");
        
        for(int i = 0; i < words.length; i ++){
            if(words[i].equalsIgnoreCase(s)){
                return true;
            }
        }//for
//        if(s.length()>3 && s.charAt(s.length()-1) == 's'){
//            return true;
//        } 
//        if(s.length()>3 &&s.substring(s.length()-2, s.length()) == "ed"){
//            return true;
//        }
        return false;
    }
    public static boolean isAdverb(String s){
        return(s.length()>4 && s.substring(s.length()-2, s.length()).equals("ly"));
    }
    public static boolean isGerund(String s){
        return(s.length()>4 && s.substring(s.length()-3, s.length()).equals("ing"));
    }
    public static boolean isArticle(String s){
        String articles = "the a an";
        String [] words = articles.split(" ");
        
        for(int i = 0; i < words.length; i ++){
            if(words[i].equalsIgnoreCase(s)){
                return true;
            }
        }//for
            
        return false;
    }
    
    public static boolean isPreposition(String s){
        String preopositions = "above before except by from in near of since for between upon with to at after on";
        String [] words = preopositions.split(" ");
        
        for(int i = 0; i < words.length; i ++){
            if(words[i].equalsIgnoreCase(s)){
                return true;
            }
        }//for
            
        return false;
    }
    public static boolean isWhWord(String s){
        String whWords = "Who What Where When Why How";
        
        String [] words = whWords.split(" ");
        
        for(int i = 0; i < words.length; i ++){
            if(words[i].equalsIgnoreCase(s)){
                return true;
            }
        }//for
            
        return false;
    }
    public static boolean isYesNo(String s){
        String yesNoWords = "Do Was Is Are Would Should Could";
        String [] words = yesNoWords.split(" ");
        
        for(int i = 0; i < words.length; i ++){
            if(words[i].equalsIgnoreCase(s)){
                return true;
            }
        }//for
            
        return false;
    }
    public static boolean isPunctuation(String c){
        String punct = "' (),=+-*/;:.?";
        return punct.contains(c) || c.equals("\"");
    }
/***************************************************************************
* lexer(String question)
* -----------------------------------
* Description
* 
* **************************************************************************
*/  
public static TokenStream lexer(String question){
    question = question.trim();
        TokenStream toks = new TokenStream();
        int errs = 0;
        int wordCount = 0;
        
        boolean isFirstWord = true;
        int ind = 0;
        while (ind < question.length()) {
            char ch = question.charAt(ind);
            if (Character.isWhitespace(ch)) {
                ind++;
                wordCount++;
               
            } else if (Character.isLetter(ch)||ch == '\'') {
                String word = new String();
                word = word + ch;
                ind++;
                char nextLetter;
                while (ind < question.length() &&
                        Character.isLetter(nextLetter = question.charAt(ind))) {
                    word = word + nextLetter;
                    ind++;
                }
                
                if (isFirstWord && isWhWord(word)){ 
                    
                    System.out.print("You said, '" + word + "', so I know you are looking for a ");
                    
                    if("Who".equalsIgnoreCase(word)){
                        System.out.println("person");

                    }
                    
                    if("What".equalsIgnoreCase(word)){
                        System.out.println("thing");

                    }
                    if("Where".equalsIgnoreCase(word)){
                        System.out.println("place");

                    }
                    if("When".equalsIgnoreCase(word)){
                        System.out.println("time");

                    }
                    if("Why".equalsIgnoreCase(word)){
                        System.out.println("cause");

                        
                    }
                    
                }
                else if(isFirstWord && isYesNo(word)){
                    
                    System.out.println("this is a yes or no question");
                
                
                }
                if(isFirstWord){
                    toks.addToken(new Token(TokenType.qword, word));
                }
                if(isProNoun(word)){
                    toks.addToken(new Token(TokenType.pronoun, word));
                }
                else if(!isFirstWord && isProperNoun(word)){
                    toks.addToken(new Token(TokenType.propernoun, word));
                }
                else if(isAdjective(word)){
                    toks.addToken(new Token(TokenType.adjective, word));
                }
                else if(isConjection(word)){
                    toks.addToken(new Token(TokenType.conjunction, word));
                }
                else if(isVerb(word)){
                    toks.addToken(new Token(TokenType.verb, word));
                }
                else if(isAdverb(word)){
                    toks.addToken(new Token(TokenType.adverb, word));
                }
                else if(isGerund(word)){
                    toks.addToken(new Token(TokenType.gerund, word));
                }
                else if(isArticle(word)){
                    toks.addToken(new Token(TokenType.article, word));
                }
                else if(isPreposition(word)){
                    toks.addToken(new Token(TokenType.preposition, word));
                }
                else{
                    toks.addToken(new Token(TokenType.unknown, word));
                }
                isFirstWord = false;
            } 
            else if (Character.isDigit(ch)) {
                String tok = new String();
                tok = tok + ch;
                ind++;
                while (ind < question.length() &&
                        Character.isDigit(question.charAt(ind))) {
                    tok = tok + question.charAt(ind);
                    ind++;
                }
                toks.addToken(new Token(TokenType.number, tok));
                
            } else if (isPunctuation(Character.toString(ch))) {
                toks.addToken(new Token(TokenType.punctuation, Character.toString(ch)));
                ind++;
                
            } 
            else {
                System.out.println("Invalid character " + ch + " ");
                errs++;
                ind++;
            }
        }
       

    return toks;
    }

    public static TokenStream tokenizePlot(String corpus){
        StringReader sr = new StringReader(corpus);
        BufferedReader input = new BufferedReader(sr);
    
        TokenStream toks = new TokenStream();
        int lineNum = 1;
        int errs = 0;
        String line;
        String punct = "(),=+-*/;:.^;";
        boolean isFirstWord = true;
        try {
            while ((line = input.readLine()) != null) {
                
                int col = 0;
                while (col < line.length()) {
                    
                    char ch = line.charAt(col);
                    if (Character.isWhitespace(ch)) {
                        col++;
                    }else if(isPunctuation(String.valueOf(ch))){
                        
                        toks.addToken(new Token(TokenType.punctuation, String.valueOf(ch)));
                        col++;
                        
                    }else if (Character.isLetter(ch)) {
                        String word = new String();
                        word = word + ch;
                        col++;
                        char tmp;
                        while (col < line.length() &&
                               (Character.isLetter(tmp = line.charAt(col))
                                || Character.isDigit(tmp))) {
                                  word = word + tmp;
                                  col++;
                        }
                        
                if(isProNoun(word)){
                    toks.addToken(new Token(TokenType.pronoun, word));
                }
                else if(!isFirstWord && isProperNoun(word)){
                    toks.addToken(new Token(TokenType.propernoun, word));
                }
                else if(isAdjective(word)){
                    toks.addToken(new Token(TokenType.adjective, word));
                }
                else if(isConjection(word)){
                    toks.addToken(new Token(TokenType.conjunction, word));
                }
                else if(isVerb(word)){
                    toks.addToken(new Token(TokenType.verb, word));
                }
                else if(isAdverb(word)){
                    toks.addToken(new Token(TokenType.adverb, word));
                }
                else if(isGerund(word)){
                    toks.addToken(new Token(TokenType.gerund, word));
                }
                else if(isArticle(word)){
                    toks.addToken(new Token(TokenType.article, word));
                }
                else if(isPreposition(word)){
                    toks.addToken(new Token(TokenType.preposition, word));
                }
                
                else{
                    toks.addToken(new Token(TokenType.unknown, word));
                }
                isFirstWord = false;
                    } else if (Character.isDigit(ch)) {
                        String tok = new String();
                        tok = tok + ch;
                        col++;
                        while (col < line.length() &&
                                Character.isDigit(line.charAt(col))) {
                                  tok = tok + line.charAt(col);
                                  col++;
                        }
                        toks.addToken(new Token(TokenType.number, tok));
                        
                    } else if (punct.contains(Character.toString(ch))) {
                        toks.addToken(new Token(TokenType.punctuation, Character.toString(ch)));
                        if(ch=='.'){isFirstWord = true;}
                        col++;
                    } else {
                        System.out.print("Invalid character " + ch + " in " + line);
                        errs++;
                        col++;
                    }
                }
                
                lineNum++;
            }
            return toks;
        } catch (IOException ex) {
            System.out.print("IO Error: " + ex.getMessage());
            return null;
    
        }//catch
    }
    
    public static TokenStream tagger(TokenStream toks){
        if(toks.peek() == Token.EOF){
            toks.here = 0;
            return toks;
        }
        if(toks.peek().type == TokenType.unknown){
            if(toks.away(1).type == TokenType.propernoun){
                toks.next().type = TokenType.verb;
            }
            
            if(toks.away(-1).type == TokenType.article){
                if(toks.away(1).type == TokenType.unknown){
                    toks.next().type = TokenType.adjective;
                    toks.next().type = TokenType.noun;
                }
                else{toks.next().type = TokenType.noun;}
            }
            
            if(toks.away(-1).type == TokenType.adverb){
                toks.next().type = TokenType.verb;
            }
            if(toks.away(1).type == TokenType.preposition){
                toks.next().type = TokenType.verb;
            }
            if(toks.away(-1).type == TokenType.adjective){
                toks.next().type = TokenType.noun;
            }
//            if(toks.away(-1).type == TokenType.noun){
//                toks.next().type = TokenType.verb;
//            }
        }
        if(toks.peek().type == TokenType.conjunction){
            if(toks.away(-1).type == TokenType.verb && toks.away(1).type == TokenType.unknown){
                toks.next();
                toks.peek().type = TokenType.verb;
            }
            if(toks.away(-1).type == TokenType.noun && toks.away(1).type == TokenType.unknown){
                toks.next();
                toks.peek().type = TokenType.noun;
            }
            
         } 
        if(toks.peek().type == TokenType.preposition && (toks.away(1).type == TokenType.unknown || toks.away(2).type == TokenType.unknown )){
                if(toks.away(1).type != TokenType.unknown){
                    toks.away(2).type = TokenType.noun;
                }
        }
        
        toks.next();
        return tagger(toks);
    }
    
    public static TokenStream parse(TokenStream toks){
        if(toks.peek() == Token.EOF){
            toks.here = 0;
            return toks;
        }
        //TokenStream newToks = new TokenStream();
        String nounPhrase = "";
        String verbPhrase = "";
        
        
        if(toks.peek().type == TokenType.article || toks.peek().type == TokenType.propernoun || toks.peek().type == TokenType.noun){
            nounPhrase +=  toks.peek().body;
            
            while(toks.peek().type != TokenType.verb || toks.peek().type != TokenType.unknown  ){
                nounPhrase += " " + toks.peek().body;
                System.out.println(toks.peek().body);
                toks.deleteThis();
            }
            
            toks.peek().body = nounPhrase;
            toks.peek().type = TokenType.nounphrase;
            
            }
            if(toks.peek().type == TokenType.pronoun){
                toks.peek().type = TokenType.nounphrase;
            }
            
            while(toks.peek().type != TokenType.punctuation && !toks.peek().body.equals(".")){
                verbPhrase += " " + toks.peek().body;
                toks.deleteThis();
            }
            toks.addToken(new Token(TokenType.verbphrase, verbPhrase));
            toks.next();
            return parse(toks) ; 
        
        
    
    }
}