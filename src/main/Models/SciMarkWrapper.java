
package main.Models;

import java.io.File;
import jnt.scimark2.commandline;


public class SciMarkWrapper implements ITestWrapper {
    public static void main(String[] args) {
        var w = new SciMarkWrapper();
        w.launch();
    }
    private static final String SUMMARY = 
            "SciMark 2.0 test, available at https://math.nist.gov/scimark2/";
    
    private static final String DEFAULT_DIRECTORY = EnvVars.testsDirectory + "/SciMark/config.txt";
    
    // config directory
    private String configDirectory;
    // config file
    private File config;
    
    public SciMarkWrapper(){
        configDirectory = DEFAULT_DIRECTORY;
    }
      
    @Override
    public String getInfo() {
        return SUMMARY;
    }

    @Override
    public File loadConfigFile() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    

    @Override
    public String getConfigDirectory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean setConfigDirectory() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void launch() {
        commandline.main(new String[0]);
    }

    @Override
    public void getSystemInfo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ITestResult getResult() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public File createDefaultConfigFile() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
