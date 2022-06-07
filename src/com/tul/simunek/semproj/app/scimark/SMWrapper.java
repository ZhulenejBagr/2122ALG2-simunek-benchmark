
package com.tul.simunek.semproj.app.scimark;

import java.nio.charset.Charset;
import jnt.scimark2.Constants;
import jnt.scimark2.Random;
import jnt.scimark2.kernel;
import com.tul.simunek.semproj.app.EnvVars;
import com.tul.simunek.semproj.app.ITestResult;
import com.tul.simunek.semproj.app.ITestWrapper;
import com.tul.simunek.semproj.utils.FileStatus;
import com.tul.simunek.semproj.utils.TextFileTools;
import java.time.LocalDateTime;


public class SMWrapper implements ITestWrapper {
    public static void main(String[] args) {
        var w = new SMWrapper();
        w.launch();
    }
    private static final String MS = "scimark";
    
    private static final String NAME = "SciMark 2.0";
    
    private static final String SUMMARY = 
            "SciMark 2.0 test, available at https://math.nist.gov/scimark2/";
    
    // default directory of everything test-related
    private static final String DEFAULT_DIRECTORY = EnvVars.testsDirectory + "\\SciMark";
    private static final String DEFAULT_CONFIG_PATH = DEFAULT_DIRECTORY + "\\config.txt";
    private static final String DEFAULT_LOGGER_PATH = DEFAULT_DIRECTORY + "\\log.txt";
    
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
    
    // logger instance
    private SMLogger logger;
    
    // result instance
    private SMResult result;
    
    /**
     *  Constructor for the SM test wrapper
     *  
     */
    public SMWrapper(){
        // initialize paths and config to default values
        configPath = DEFAULT_CONFIG_PATH;
        loggerPath = DEFAULT_LOGGER_PATH;
        logger = new SMLogger();
        logger.setFilePath(loggerPath);
        conf = new SMConfig(0);      
    }
    
    @Override
    public String getName(){
        return NAME;
    }
    
    
    @Override
    public String getInfo() {
        return SUMMARY;
    }
    
    public static Charset getEncoding() {
        return ENC;
    }

    @Override
    public boolean loadConfigFile() {
        logger.WriteToFile("Loading config from " + configPath + " ...");
        String[] cfcontent = TextFileTools.tryReadFile(configPath, ENC);
        if (cfcontent == new String[0]) {
            logger.WriteToFile("Error loading config at " + configPath + " !" );
        }
        var config = new SMConfig(0);
        
        for (String line : cfcontent){
            String[] split = line.split("=");
            var identifier = split[0];      
            
            switch (identifier){
                case "TEST_PASSES" -> {
                    try {
                        config.setTestPasses(Integer.parseInt(split[1]));
                    }
                    catch (NumberFormatException ex){
                    }
                }
            }
        }
        
        if (config.isValid()){
            conf = config;
            logger.WriteToFile("Loaded config is valid");
            return true;
        }
        logger.WriteToFile("Loaded config is invalid");
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
        var startTime = LocalDateTime.now();
        
        logger.startLogging();
        
        if (!loadConfigFile()){
            logger.WriteToFile("No valid config available, creating default config from at default path ...");
            setConfigPathToDefault();
            if (createDefaultConfigFile()) {
                if (fillConfigWithDefault()) {
                    loadConfigFile();
                    logger.WriteToFile("Default config succesfully created at " + configPath);
                }
                else {
                    logger.WriteToFile("Unable to access config file, exiting ...");
                    return;
                }
            }
            else{
                logger.WriteToFile("Unable to access config file, exiting ...");
                return;
            }
        }
        else {
            logger.WriteToFile("Valid config loaded from " + configPath + ", commencing test launch.");
        }
        
        logger.WriteToFile("Launching test");
        
        Random R = new Random(Constants.RANDOM_SEED);
        
        var FFT_size = Constants.LG_FFT_SIZE;
        var SOR_size =  Constants.LG_SOR_SIZE;
	var Sparse_size_M = Constants.LG_SPARSE_SIZE_M;
	var Sparse_size_nz = Constants.LG_SPARSE_SIZE_nz;
	var LU_size = Constants.LG_LU_SIZE;
        
        var min_time = Constants.RESOLUTION_DEFAULT;
        
        var res = new double[6];
        
        for (int i = 0; i < conf.getTestPasses(); i++){
            logger.WriteToFile("Running testpass " + (i + 1));
            res[0] += kernel.measureFFT(FFT_size, min_time, R);
            logger.WriteToFile("FFT score " + res[0]);
            res[0] /= 2;
            res[1] += kernel.measureSOR(SOR_size, min_time, R);
            logger.WriteToFile("SOR score " + res[1]);
            res[1] /= 2;
            res[2] += kernel.measureMonteCarlo(min_time, R);
            logger.WriteToFile("MonteCarlo score " + res[2]);
            res[2] /= 2;
            res[3] += kernel.measureSparseMatmult(Sparse_size_M, Sparse_size_nz, min_time, R);
            logger.WriteToFile("Sparse score " + res[3]);
            res[3] /= 2;
            res[4] += kernel.measureLU(LU_size, min_time, R);
            logger.WriteToFile("LU score " + res[4]);
            res[4] /= 2;
        }
        
        for (int j = 0; j < 5; j++){
            res[5] += res[j];
        }
        
        logger.WriteToFile("Final score " + res[5]);

        var systemInfo = new String[] {"x86 CPU"};
        var finishTime = LocalDateTime.now();
        result = new SMResult(startTime, finishTime, systemInfo, res[5]);
    }

    @Override
    public ITestResult getResult() {
        return result;
    }

    public void setConfigPathToDefault(){
        configPath = DEFAULT_CONFIG_PATH;
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

    @Override
    public String getVersion() {
        return SMConstants.VERSION;
    }

    @Override
    public String getMenuSelector() {
        return MS;
    }
    
}       
