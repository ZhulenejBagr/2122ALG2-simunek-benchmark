package com.tul.simunek.semproj.app.utils.libs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class TextFileTools {
    private TextFileTools(){
    }
    
    public static FileStatus tryCreateFileAtPath(String path){
         try {
            File conf = new File(path);
            if (conf.createNewFile()) {
                return FileStatus.CREATED;
            }
            else {
                return FileStatus.EXISTS;
            }
        }
        catch (IOException ex) {
            return FileStatus.ERROR;
        }
    }
    
    public static boolean tryEmptyFile(String path){
        try {
            PrintWriter writer = new PrintWriter(path);
            writer.close();
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("File not found at " + path);
            return false;
        }    
    }
    
    public static boolean tryWriteToFile(String path, String[] content, Charset ch){
        try (BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), ch))) {
            
            for (String s : content) {
                w.write(s);
                w.newLine();
                w.flush();
            }
            return true;
        }
        catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
    }
    
    public static String[] tryReadFile(String path, Charset ch){
        try (BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(path), ch))){
            List<String> content = new ArrayList<>();
            String text;
            while ((text = r.readLine()) != null) {
                content.add(text);
            }
            return content.toArray(String[]::new);
        }
        catch (FileNotFoundException ex) {
            return new String[0];
        } catch (IOException ex) {
            return new String[0];
        }
    }
}
