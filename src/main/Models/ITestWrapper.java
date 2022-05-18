package main.Models;

import java.io.File;


public interface ITestWrapper {
    
    // get info about test 
    String getInfo();
    
    // get config file of test
    boolean loadConfigFile();
    // get directory of config file
    String getConfigDirectory();
    // set config file directory
    boolean setConfigDirectory(String path);
    // create default config
    boolean createDefaultConfigFile();
    // fill config with default values
    boolean fillConfigWithDefault();
    
    // launch test
    void launch();
    
    // generate system info for result
    void getSystemInfo();
    
    // get test result
    ITestResult getResult();
}
