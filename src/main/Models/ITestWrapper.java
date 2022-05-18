package main.Models;

public interface ITestWrapper {
    
    /**
     * Get summary about the test
     * Whatever info that is essential
     * @return General info about the test
     */
    String getInfo();
    
    /**
     * Method to load the config from the config file
     * The logic should cover all possible exceptions
     * @return If the config was loaded successfully
     */
    boolean loadConfigFile();
    
    /**
     * Method to get the config file path
     * Should contain a default and cover exceptions
     * @return Config file path
     */
    String getConfigDirectory();
    
    /**
     * Set the config file path
     * Can contain a check for the validity of the path
     * @param path New path
     * @return Whether the setting was successful
     */
    boolean setConfigDirectory(String path);
    
    /**
     * Create a file with a known, tested config
     * Together with createDefaultConfigFile() 
     * serves as a first time init or as a fail-safe
     * @return If file exists or was sucessfully created
     */
    boolean createDefaultConfigFile();
    
    /**
     * Overwrite current config file with default known good values
     * @return If overwrite to default was successful
     */
    boolean fillConfigWithDefault();
    
    /**
     * Launch test with currently loaded configuration
     */
    void launch();
    
    /**
     * Generate system info 
     * @return System info summary
     */ 
    String getSystemInfo();
    
    /**
     * Get result of a test
     * @return Instance with test result info
     */
    ITestResult getResult();
}
