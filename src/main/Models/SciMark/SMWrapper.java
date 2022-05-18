
package main.Models.SciMark;

import java.nio.charset.Charset;
import jnt.scimark2.Constants;
import jnt.scimark2.Random;
import jnt.scimark2.commandline;
import jnt.scimark2.kernel;
import main.Models.EnvVars;
import main.Models.ITestResult;
import main.Models.ITestWrapper;
import main.libs.FileStatus;
import main.libs.TextFileTools;


public class SMWrapper implements ITestWrapper {
    public static void main(String[] args) {
        var w = new SMWrapper();
        w.launch();
    }
    
    private static final String SUMMARY = 
            "SciMark 2.0 test, available at https://math.nist.gov/scimark2/";
    
    // default directory of everything test-related
    private static final String DEFAULT_DIRECTORY = EnvVars.testsDirectory + "\\SciMark;";
    private static final String DEFAULT_CONFIG_PATH = DEFAULT_DIRECTORY + "config.txt";
    private static final String DEFAULT_LOGGER_PATH = DEFAULT_DIRECTORY + "log.txt";
    
    // default encoding charset for reading and writing
    private static final Charset ENC = Charset.forName("UTF8");
    
    // contents of default config file
    private static final String[] DEFAULT_CONF_CONTENT = {
        "% config file for SciMark test",
        "TEST_PASSES=" + SMConstants.DEFAULT_TEST_PASSES
    };
    
    // config path
    private String configPath;
    
    // logger path
    private String loggerPath;
    
    // loaded config contents
    private SMConfig conf;
    
    /**
     *  Constructor for the SM test wrapper
     *  
     */
    public SMWrapper(){
        // initialize paths and config to default values
        configPath = DEFAULT_CONFIG_PATH;
        loggerPath = DEFAULT_LOGGER_PATH;
        conf = new SMConfig();      
    }
    
    
    @Override
    public String getInfo() {
        return SUMMARY;
    }

    @Override
    public boolean loadConfigFile() {
        String[] cfcontent = TextFileTools.tryReadFile(configPath, ENC);
        
        var config = new SMConfig(0);
        
        for (String line : cfcontent){
            String[] split = line.split("=");
            var identifier = split[0];      
            
            switch (identifier){
                case "TEST_PASSES" -> {
                    try {
                        conf.setTestPasses(Integer.parseInt(split[1]));
                    }
                    catch (NumberFormatException ex){
                    }
                }
            }
        }
        
        if (config.isValid()){
            conf = config;
            return true;
        }
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
        if (!conf.isValid()){
            System.out.println("No valid config loaded, loading config from " + configPath + " ...");
            if (!loadConfigFile()){
                System.out.println("No available config at " + configPath);
                System.out.println("Please select a valid config path before starting the test");
                return;
            }
        }
        
        Random R = new Random(Constants.RANDOM_SEED);
        
        var FFT_size = Constants.LG_FFT_SIZE;
        var SOR_size =  Constants.LG_SOR_SIZE;
	var Sparse_size_M = Constants.LG_SPARSE_SIZE_M;
	var Sparse_size_nz = Constants.LG_SPARSE_SIZE_nz;
	var LU_size = Constants.LG_LU_SIZE;
        
        var min_time = Constants.RESOLUTION_DEFAULT;
        
        var res = new double[6];
        
        for (int i = 0; i < conf.getTestPasses(); i++){
            System.out.println("Running testpass " + (i + 1));
            res[0] += kernel.measureFFT(FFT_size, min_time, R);
            res[0] /= 2;
            res[1] += kernel.measureSOR(SOR_size, min_time, R);
            res[1] /= 2;
            res[2] += kernel.measureMonteCarlo(min_time, R);
            res[2] /= 2;
            res[3] += kernel.measureSparseMatmult(Sparse_size_M, Sparse_size_nz, min_time, R);
            res[3] /= 2;
            res[4] += kernel.measureLU(LU_size, min_time, R);
            res[4] /= 2;
        }
        
        for (int j = 0; j < 5; j++){
            res[5] += res[j];
        }
        
        
        
        System.out.println("Final score: " + res[5]);
        
    }

    @Override
    public String getSystemInfo() {
        // TODO get info of PC specs and JVM 
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
