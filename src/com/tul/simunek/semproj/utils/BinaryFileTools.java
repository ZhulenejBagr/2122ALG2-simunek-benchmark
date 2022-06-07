
package com.tul.simunek.semproj.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class BinaryFileTools {
    private BinaryFileTools(){
    }
    
    public static boolean writeToBinary(Serializable obj, String path) {
        var file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                return false;
            }
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(obj);
            out.close();
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        } 
    }
}
