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
        String pronouns = "I you he she we it they us them me her him myself himself themselves mine ours yours who whom that";
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
        String adjective = "one two three four five any his hers what red orange yellow green blue purple good new first last long great little own other old right big high different small large next early young important few public bad" ;
        String [] words = adjective.split(" ");
        
        for(int i = 0; i < words.length; i ++){
            if(words[i].equalsIgnoreCase(s)){
                return true;
            }
        }//for
            
        return false;
    }
    public static boolean isConjection(String s){
        String conjunction = "and but or neither nor either & as if than because while after so though since until whether before although nor like once unless now except";
        String [] words = conjunction.split(" ");
        
        for(int i = 0; i < words.length; i ++){
            if(words[i].equalsIgnoreCase(s)){
                return true;
            }
        }//for
            
        return false;
    }
    public static boolean isVerb(String s){
        String verb = "'s was is are has does did have had be do say get make go know ake see come think look want give use find tell ask work seem feel try leave call";
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
        String articles = "can not up so out just now how then more also here well only very even back there down still as never when";
        String [] words = articles.split(" ");
        
        for(int i = 0; i < words.length; i ++){
            if(words[i].equalsIgnoreCase(s)){
                return true;
            }
        }//for
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
        String preopositions = "above before except by from in near of since for between upon across with to at after on for on at from by about as ino like through after over between out against during without before under around among";
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
                    }else if (ch == '&') {
                        toks.addToken(new Token(TokenType.conjunction, String.valueOf(ch)));
                        col++;
                    }else if (Character.isDigit(ch)) {
                        String tok = new String();
                        tok = tok + ch;
                        col++;
                        while (col < line.length() &&
                                Character.isDigit(line.charAt(col)) || line.charAt(col) == '.' ) {
                                  tok = tok + line.charAt(col);
                                  col++;
                        }
                        toks.addToken(new Token(TokenType.number, tok));
                        
                    } else if(isPunctuation(String.valueOf(ch))){
                        
                        toks.addToken(new Token(TokenType.punctuation, String.valueOf(ch)));
                        col++;
                        
                    }
                    else if (Character.isLetter(ch) ) {
                        String word = new String();
                        word = word + ch;
                        col++;
                        char tmp;
                        while (col < line.length() &&
                               (Character.isLetter(tmp = line.charAt(col)) 
                                || Character.isDigit(tmp) || tmp == '-' || tmp == '\''  ||((Character.isUpperCase(line.charAt(col-1)) && tmp == '.') )) ) {
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
                else if(isPunctuation(String.valueOf(ch))&& ch == '.'){
                        
                        toks.addToken(new Token(TokenType.EOS, String.valueOf(ch)));
                        isFirstWord = true;
                        col++;
                        
                    }else if (punct.contains(Character.toString(ch))) {
                        toks.addToken(new Token(TokenType.punctuation, Character.toString(ch)));
                        col++;
                    }
                
                else{
                    toks.addToken(new Token(TokenType.unknown, word));
                }
                isFirstWord = false;
                    }  else {
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
        //mark end of sentence
        if(toks.peek().type == TokenType.punctuation && toks.peek().body.equals(".")){
            toks.peek().type = TokenType.EOS;
        }
        //two consecutive propernouns makes one propernoun
        if(toks.peek().type == TokenType.propernoun && toks.away(1).type == TokenType.propernoun){
            toks.peek().body +=  " " + toks.away(1).body;
            toks.next();
            toks.deleteThis();
        }
        //if the word has a hyphen, it is an adjective
        if(toks.peek().body.contains("-")){
            toks.peek().type = TokenType.adjective;
        
        }
        
        //if this is the first word
        if(toks.away(-1).type == TokenType.EOS){
            
        
        }
        if(toks.peek().type == TokenType.gerund){
            if(toks.away(-1).type == TokenType.pronoun){
                toks.peek().type = TokenType.adjective;
            }
        
        }
        //find a token of an unknow type
        if (toks.peek().type == TokenType.unknown) {

            //if the token after the unknown is a propernoun, it is likely the unknown is a verb
            //this rule does not make a lot of sense
            if ((toks.away(1).type == TokenType.noun || toks.away(1).type == TokenType.propernoun) && (toks.away(-1).type == TokenType.propernoun || toks.away(-1).type == TokenType.noun)) {
                toks.peek().type = TokenType.verb;
            }
        }
        if (toks.peek().type == TokenType.unknown) {
            //if the token before 
            if (toks.away(-1).type == TokenType.article) {
                if (toks.away(1).type == TokenType.unknown && toks.away(2).type == TokenType.unknown) {
                    toks.next().type = TokenType.adjective;
                    toks.next().type = TokenType.adjective;
                    toks.peek().type = TokenType.noun;
                }
                else if (toks.away(1).type == TokenType.unknown) {
                    toks.next().type = TokenType.adjective;
                    toks.peek().type = TokenType.noun;
                } else {
                    toks.peek().type = TokenType.noun;
                }
            }
        }
        if (toks.peek().type == TokenType.unknown) {
            if (toks.away(-1).type == TokenType.adverb) {
                toks.peek().type = TokenType.verb;
            }
        }
        if (toks.peek().type == TokenType.unknown) {
            if (toks.away(1).type == TokenType.preposition) {
                toks.peek().type = TokenType.verb;
            }
            
        }
        if (toks.peek().type == TokenType.unknown) {
            if (toks.away(-1).type == TokenType.preposition) {
                if (toks.away(1).type == TokenType.pronoun) {
                    toks.peek().type = TokenType.verb;
                }
                if(toks.away(1).type == TokenType.punctuation || toks.away(1).type == TokenType.EOS || toks.away(1).type == TokenType.conjunction){
                    toks.peek().type = TokenType.noun;
                }
                if(toks.away(1).type == TokenType.unknown){
                    toks.peek().type = TokenType.adjective;
                    toks.away(1).type = TokenType.noun;
                }
            }
        }
        if (toks.peek().type == TokenType.unknown) {
            if (toks.away(-1).type == TokenType.pronoun) {
                if (toks.away(1).type == TokenType.pronoun) {
                    toks.peek().type = TokenType.verb;
                }
            }
        }
        if (toks.peek().type == TokenType.unknown) {
            if (toks.away(-1).type == TokenType.adjective) {
                toks.peek().type = TokenType.noun;
            }
        }
        if (toks.peek().type == TokenType.unknown) {
            if (toks.away(-1).type == TokenType.propernoun) {
                toks.peek().type = TokenType.noun;
            }
        }
        if (toks.peek().type == TokenType.unknown) {
            if (toks.away(-1).type == TokenType.conjunction) {
                if (toks.away(-2).type == TokenType.verb) {
                    toks.peek().type = TokenType.verb;
                }
                if (toks.away(-2).type == TokenType.noun && toks.away(1).type != TokenType.preposition) {

                    toks.peek().type = TokenType.noun;
                }
                if (toks.away(-2).type == TokenType.noun && (toks.away(1).type == TokenType.preposition || toks.away(1).type == TokenType.article)) {
                    toks.peek().type = TokenType.verb;
                }

            }

        }
        if (toks.peek().type == TokenType.unknown) {
            if(toks.peek().type == TokenType.preposition && (toks.away(1).type == TokenType.unknown || toks.away(2).type == TokenType.unknown )){
                    if(toks.away(1).type != TokenType.unknown){
                        toks.away(2).type = TokenType.noun;
                    }
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
        String adjectivePhrase = "";
        
        if(toks.peek().type != TokenType.verb ){
            nounPhrase +=  toks.peek().body;
            toks.deleteThis();
            
            while((toks.away(1).type != TokenType.verb && toks.away(1).type != TokenType.adverb )&& toks.peek() != Token.EOF && toks.peek().type != TokenType.preposition){
                nounPhrase += " " + toks.peek().body;
                
                toks.deleteThis();
                
            }
            
            toks.peek().body = nounPhrase + " " +toks.peek().body;
            toks.peek().type = TokenType.nounphrase;
             System.out.println(toks.peek() + "   (" + toks.peek().type + ")");
            toks.next();
            }
        
        if(toks.peek().type == TokenType.adverb ||toks.peek().type == TokenType.verb || toks.peek().type == TokenType.preposition){
            verbPhrase +=  toks.peek().body;
            toks.deleteThis();
            
            while(toks.away(1) != Token.EOF  && toks.away(1).type == TokenType.verb ){
                verbPhrase += " " + toks.peek().body;
                //System.out.println(verbPhrase);
                toks.deleteThis();
                
            }
            toks.peek().body = verbPhrase + " " +toks.peek().body;
            toks.peek().type = TokenType.verbphrase;
            System.out.println(toks.peek() + "   (" + toks.peek().type + ")");
        }
//        
            toks.next();
            return parse(toks) ; 
        
        
    
    }
}