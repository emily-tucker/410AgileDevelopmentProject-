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
        String word = "";
        ArrayList<String> words = new ArrayList<>();
        for(int i = 0; i < pronouns.length(); i++ ){
            if(Character.isWhitespace(pronouns.charAt(i))){
                words.add(word);
                word = "";
            }
            else{word += pronouns.charAt(i);}
        
        }
        for(String w: words){
            if(w.equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }
    public static boolean isProperNoun(String s){
        return Character.isUpperCase(s.charAt(0));
    }
    public static boolean isAdjective(String s){
        //this is clearly not right btw
        String adjective = "what red orange yellow green blue purple";
        String word = "";
        ArrayList<String> words = new ArrayList<>();
        for(int i = 0; i < adjective.length(); i++ ){
            if(Character.isWhitespace(adjective.charAt(i))){
                words.add(word);
                word = "";
            }
            else{word += adjective.charAt(i);}
        
        }
        for(String w: words){
            if(w.equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }
    public static boolean isConjection(String s){
        String conjunction = "and but or neither nor either";
        String word = "";
        ArrayList<String> words = new ArrayList<>();
        for(int i = 0; i < conjunction.length(); i++ ){
            if(Character.isWhitespace(conjunction.charAt(i))){
                words.add(word);
                word = "";
            }
            else{word += conjunction.charAt(i);}
        
        }
        for(String w: words){
            if(w.equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }
    public static boolean isVerb(String s){
        String verb = "'s was is has does did have had";
        String word = "";
        ArrayList<String> words = new ArrayList<>();
        for(int i = 0; i < verb.length(); i++ ){
            if(Character.isWhitespace(verb.charAt(i))){
                words.add(word);
                word = "";
            }
            else{word += verb.charAt(i);}
        
        }
        for(String w: words){
            if(w.equalsIgnoreCase(s)){
                return true;
            }
        }
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
        String word = "";
        ArrayList<String> words = new ArrayList<>();
        for(int i = 0; i < articles.length(); i++ ){
            if(Character.isWhitespace(articles.charAt(i))){
                words.add(word);
                word = "";
            }
            else{word += articles.charAt(i);}
        
        }
        for(String w: words){
            if(w.equalsIgnoreCase(s)){
                return true;
            }
        }
        return false;
    }
    
    public static boolean isPreposition(String s){
        String preopositions = "above before except from in near of since for between upon with to at after on";
        String word = "";
        ArrayList<String> words = new ArrayList<>();
        for(int i = 0; i < preopositions.length(); i++ ){
            if(Character.isWhitespace(preopositions.charAt(i))){
                words.add(word);
                word = "";
            }
            else{word += preopositions.charAt(i);}
        
        }
        for(String w: words){
            if(w.equalsIgnoreCase(s)){
                return true;
            }
        }
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
        return punct.contains(c);
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
                    } else if (Character.isLetter(ch) || ch == '\'') {
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
}
