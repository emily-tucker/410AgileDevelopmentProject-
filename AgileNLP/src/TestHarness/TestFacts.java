/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestHarness;

/**
 *
 * @author bdoyle
 */
import Fact.*;
import Token.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestFacts {
    public static void main(String[] args) {
        test1();
    }    
    
    public static void test1()
    {
        String path = "c:\\test\\test_data.txt";
        String file_contents = getContentsofFile(path);
        TokenStream ts1 = Tokenizer.lexer("What movie is Yoda in?");
        TokenStream ts2 = Tokenizer.tokenizePlot(file_contents);
        Facts fs1 = FactBuilder.glean(ts1);
        Facts fs2 = FactBuilder.glean(ts2);
        System.out.println("done");
    }
    

    public static String getContentsofFile(String path)
    {
        
        try
        {
            Charset encoding = Charset.defaultCharset();
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, encoding);        
        }
        catch (Exception ex)
        {
            return "";
        }
    }
}
