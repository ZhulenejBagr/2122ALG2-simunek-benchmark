
package main.Models.SciMark;

import java.nio.charset.Charset;
import jnt.scimark2.commandline;
import main.Models.EnvVars;
import main.Models.ITestResult;
import main.Models.ITestWrapper;
import main.libs.FileStatus;
import main.libs.TextFileTools;


public class SciMarkWrapper implements ITestWrapper {
    public static void main(String[] args) {
        var w = new SciMarkWrapper();
        System.out.println(w.createDefaultConfigFile());
        System.out.println(w.fillConfigWithDefault());
    }
    
    private static final String SUMMARY = 
            "SciMark 2.0 test, available at https://math.nist.gov/scimark2/";
    
    private static final String DEFAULT_DIRECTORY = EnvVars.testsDirectory + "\\SciMark;";
    private static final String DEFAULT_CONFIG_PATH = DEFAULT_DIRECTORY + "config.txt";
    private static final String DEFAULT_LOGGER_PATH = DEFAULT_DIRECTORY + "log.txt";
    
    private static final Charset ENC = Charset.forName("UTF8");
    
    private static final String[] DEFAULT_CONF_CONTENT = {
        "% config file for SciMark test",
        "TEST_PASSES=" + SciMarkConstants.DEFAULT_TEST_PASSES
    };
    
    // config path
    private String configPath;
    
    // logger path
    private String loggerPath;
    
    // loaded config contents
    private SciMarkConfig conf;
    
    
    public SciMarkWrapper(){
        configPath = DEFAULT_CONFIG_PATH;
        loggerPath = DEFAULT_LOGGER_PATH;
        conf = new SciMarkConfig(SciMarkConstants.DEFAULT_TEST_PASSES);
        
    }
      
    @Override
    public String getInfo() {
        return SUMMARY;
    }

    @Override
    public boolean loadConfigFile() {
        String[] cfcontent = TextFileTools.tryReadFile(configPath, ENC);
        
        
        
       return false; 
    }
    
    

    @Override
    public String getConfigDirectory() {
        return configPath;
    }

    @Override
    public boolean setConfigDirectory(String path) {
        configPath = path;
        
        return true;
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
    public boolean createDefaultConfigFile() {
        var status = TextFileTools.tryCreateFileAtPath(configPath);
        return status != FileStatus.ERROR;
    }

    @Override
    public boolean fillConfigWithDefault() {
        var isEmpty = TextFileTools.tryEmptyFile(configPath);
         
        if (isEmpty) {
            if (TextFileTools.tryWriteToFile(configPath, DEFAULT_CONF_CONTENT, ENC)){
                return true;
            }
        }
        
        return false;
    }
    
}       
