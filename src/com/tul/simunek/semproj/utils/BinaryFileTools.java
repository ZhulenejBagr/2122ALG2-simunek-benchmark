
package com.tul.simunek.semproj.utils;

import com.tul.simunek.semproj.app.ITestResult;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryFileTools {
    private BinaryFileTools(){
    }
    
    public static <T> void writeToBinary(List<T> obj, String path) throws FileNotFoundException, IOException {
        var file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))){
            out.writeObject(obj.toArray());
            out.close();
        } 
    }
    
    public static List<ITestResult> parseDatabaseFromBinary(String path) throws IOException, FileNotFoundException, ClassNotFoundException {
        var db_obj = readFromBinary(path);
        var db_arr = (Object[]) db_obj;
        var list = new ArrayList<ITestResult>();
        for (Object db_arr1 : db_arr) {
            list.add((ITestResult) db_arr1);
        }
        return list;
    }
    
    public static <T extends Serializable> Object readFromBinary(String path) throws IOException, FileNotFoundException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            return in.readObject();
        }
    }
}
