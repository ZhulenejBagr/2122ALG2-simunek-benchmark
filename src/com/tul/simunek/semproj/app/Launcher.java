
package com.tul.simunek.semproj.app;

import com.tul.simunek.semproj.app.scimark.SMWrapper;
import java.util.ArrayList;
import java.util.List;

public class Launcher {
    
    private final List<ITestWrapper> testList;
    
    public Launcher(){
        testList = new ArrayList<>();
        registerAvailableTests();
    }
    
    private void registerAvailableTests(){
        testList.add(new SMWrapper());
    }
    
    /**
     * Získat list registrovaných testů v launcheru
     * @return list registrovaných testů
     */
    public List<ITestWrapper> getList(){
        return testList;
    }
}
