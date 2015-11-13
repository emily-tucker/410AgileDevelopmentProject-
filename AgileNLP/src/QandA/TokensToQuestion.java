/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QandA;
import java.util.ArrayList;
import Token.*;

/**
 *
 * @author stu852511
 * SO WHAT WE DONE HERE.
 * Is we made a class that doesn't have a constructor, that is private, and a main method. A bit like Tokenizer.
 * It takes a stream of tokens (a sentence), and then tries to process what kind of question is being asked.
 * It returns a string at the moment, because that's what the user is going to expect. Imagine this plugged right into the UI.
 * That will probably change, though, because I have no clue how the architecture of this project is going to turn out.
 * Anyway. This looks at the structure of a question.
 * Like, if it's a who, what, where, when question.
 * Obviously we can't answer every question- WHY questions are right out.
 * As are oddly-worded ones.
 * In the future, we'll be able to recognize questions that start with verbs.
 * Specifically, IS-verbs. Is, are, was, etc.
 * We don't do that yet.
 */
public class TokensToQuestion {
    private TokensToQuestion(){}
    public String MakeQuestion(TokenStream stream){
        String currentToken = stream.peek().body;
        String nextToken = stream.following().body;
        if(Tokenizer.isWhWord(currentToken)){
            if(currentToken.equals("who")){
                if(Tokenizer.isVerb(nextToken)){
                    ArrayList<String> nouns = new ArrayList();
                    while(stream.next() != Token.EOF){
                        currentToken = stream.peek().body;
                        if(Tokenizer.isProperNoun(currentToken)){
                            nouns.add(currentToken);
                        }
                    }
                    if(nouns.size() <= 0){
                        return "Well, that was most of a question, but you didn't finish it with any nouns.";
                    }
                    String dump = " "; //FOR DUMPING ALL THE NOUNS INTO ONE PLACE
                    dump = dump + nouns.get(0);
                    for(int i = 1; i < nouns.size(); i++){
                        dump = dump + ", " + nouns.get(i); 
                    }
                    return "This is a question about WHO " + nextToken + dump;
                }
            }else if(currentToken.equals("what")){
                if(Tokenizer.isProperNoun(nextToken)){
                    ArrayList<String> nouns = new ArrayList();
                    while(stream.next() != Token.EOF){
                        currentToken = stream.peek().body;
                        if(Tokenizer.isProperNoun(currentToken)){
                            nouns.add(currentToken);
                        }
                    }
                    if(nouns.size() <= 0){
                        return "Well, that was most of a question, but you didn't finish it with any nouns.";
                    }
                    String dump = " "; //FOR DUMPING ALL THE NOUNS INTO ONE PLACE
                    dump = dump + nouns.get(0);
                    for(int i = 1; i < nouns.size(); i++){
                        dump = dump + ", " + nouns.get(i); 
                    }
                    return "This is a question about WHAT " + nextToken + dump;
                }
            }else if(currentToken.equals("where")){
                if(Tokenizer.isVerb(nextToken)){
                    ArrayList<String> nouns = new ArrayList();
                    while(stream.next() != Token.EOF){
                        currentToken = stream.peek().body;
                        if(Tokenizer.isProperNoun(currentToken)){
                            nouns.add(currentToken);
                        }
                    }
                    if(nouns.size() <= 0){
                        return "Well, that was most of a question, but you didn't finish it with any nouns.";
                    }
                    String dump = " "; //FOR DUMPING ALL THE NOUNS INTO ONE PLACE
                    dump = dump + nouns.get(0);
                    for(int i = 1; i < nouns.size(); i++){
                        dump = dump + ", " + nouns.get(i); 
                    }
                    return "This is a question about WHERE " + nextToken + dump;
                }
            }else if(currentToken.equals("when")){
                if(Tokenizer.isVerb(nextToken)){
                    ArrayList<String> nouns = new ArrayList();
                    while(stream.next() != Token.EOF){
                        currentToken = stream.peek().body;
                        if(Tokenizer.isProperNoun(currentToken)){
                            nouns.add(currentToken);
                        }
                    }
                    if(nouns.size() <= 0){
                        return "Well, that was most of a question, but you didn't finish it with any nouns.";
                    }
                    String dump = " "; //FOR DUMPING ALL THE NOUNS INTO ONE PLACE
                    dump = dump + nouns.get(0);
                    for(int i = 1; i < nouns.size(); i++){
                        dump = dump + ", " + nouns.get(i); 
                    }
                    return "This is a question about WHAT " + nextToken + dump;
                }
            }else if(currentToken.equals("why")){
                return "I can't answer 'Why' questions.";
            }
        }else if(Tokenizer.isVerb(currentToken)){
            return "SOMETHING ABOUT VERB QUESTIONS. Check for multiple nouns- MULTIPLE.";
        }else{
            return "I don't understand the question. Please start it with a WH-word.";
        }
        
        
        
        return "SOMETHING WENT BAD";
    }
    
}