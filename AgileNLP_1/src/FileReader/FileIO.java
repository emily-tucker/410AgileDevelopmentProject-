/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileReader;

import Error.HandleError;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author bdoyle
 */
public class FileIO {
    public static BufferedReader GetBufferedReader(Boolean resources, String path_to_file) {
        try {
            InputStream input_stream;
            BufferedReader reader;

            if (resources) {
                input_stream = DataLoader.class.getResourceAsStream(path_to_file);
            } else {
                input_stream = new FileInputStream(path_to_file);
            }

            //load buffered reader
            reader = new BufferedReader(new InputStreamReader(input_stream));

            return reader;
        } catch (Exception ex) {
            HandleError.HandleError(ex);
            return null;
        }
    }    
    public static BufferedWriter GetBufferedWriter(String path_to_file) {
        try {
            FileOutputStream output_stream;
            BufferedWriter writer;

            output_stream = new FileOutputStream(path_to_file);

            //load buffered reader
            writer = new BufferedWriter(new OutputStreamWriter(output_stream));

            return writer;
        } catch (Exception ex) {
            HandleError.HandleError(ex);
            return null;
        }
    }
    
}
