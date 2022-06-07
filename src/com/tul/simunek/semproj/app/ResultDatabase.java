
package com.tul.simunek.semproj.app;

import com.tul.simunek.semproj.app.scimark.SMResult;
import com.tul.simunek.semproj.utils.BinaryFileTools;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ResultDatabase {
    private final static ITestResult[] SAMPLE_DATA = new ITestResult[] {
        new SMResult(LocalDateTime.of(2022, Month.of(6), 6, 7, 30, 20, 1242145), LocalDateTime.of(2022, Month.of(6), 6, 8, 10, 56, 0), new String[] {"generic x86 CPU"}, 2302),
        new SMResult(LocalDateTime.of(2022, Month.of(6), 6, 9, 0, 0, 0), LocalDateTime.of(2022, Month.of(6), 6, 9, 1, 0, 0), new String[] {"generic x86 CPU"}, 3400)
    };
    
    private final static String DB_PATH = EnvVars.testsDirectory + "\\db";
    
    public static void main(String[] args) {
        var r = new ResultDatabase();
        //r.fillWithSampleData();
        //System.out.println(r.serialize());
        System.out.println(r.deserialize());
        System.out.println("");
    }
    
    private List<ITestResult> results;
    
    public ResultDatabase(){
        if (doesDBFileExist()){
            deserialize();
        }
        else {
            results = new ArrayList<>();
            serialize();
        }
    }
    
    /**
     * Uložení výsledku do databáze
     * @param res výsledek k uložení
     * @return jestli bylo uložení úspěšné
     */
    public boolean addResult(ITestResult res){
        var status = results.add(res);
        serialize();
        return status;
    }
    
    
    /**
     * Naplnění databáze vzorkovými daty
     */
    public void fillWithSampleData() {
        results.addAll(Arrays.asList(SAMPLE_DATA));
    }
    
    /**
     Získání všech výsledků z databáze
     * @return list výsledků v databázi
     */
    public List<ITestResult> getResults(){
        return results;
    }
    
    /**
     * Seřadit databázové záznamy podle jména a skóre
     * Pokud mají stejné jméno testu, tak ten s vyšším skóre bude výš
     */
    public void sortByTestThenByScore(){
        Collections.sort(results, 
                (x, y) -> y.compareTo(x) == 0 ? Double.compare(y.getTestScore(), x.getTestScore()) : y.compareTo(x));
    }
    
    private boolean doesDBFileExist(){
        return new File(DB_PATH).exists();
    }
    
    
    /**
     * Serialize DB data and save to file
     * @return result of serialization
     */
    private boolean serialize() {
        try {
            BinaryFileTools.<ITestResult>writeToBinary(results, DB_PATH);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
    
    /**
     * Deserialize data from the DB file
     * @return result of deserialization
     */
    private boolean deserialize() {
        try {
            results = BinaryFileTools.parseDatabaseFromBinary(DB_PATH);
            return true;
        } catch (IOException | ClassNotFoundException ex) {
            return false;
        }
    }
}
