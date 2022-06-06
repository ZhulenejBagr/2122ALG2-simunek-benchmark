
package com.tul.simunek.semproj.app;

import com.tul.simunek.semproj.app.scimark.SMWrapper;
import java.util.ArrayList;
import java.util.List;

public class Launcher {
    
    private final List<ITestWrapper> testList;
    
    public Launcher(){
        testList = new ArrayList<>();
        RegisterAvailableTests();
    }
    
    private void RegisterAvailableTests(){
        testList.add(new SMWrapper());
    }
    
    public List<ITestWrapper> getList(){
        return testList;
    }
}
