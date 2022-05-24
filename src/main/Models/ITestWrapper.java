package main.Models;

public interface ITestWrapper {
    
    /**
     * Gets te test name
     * separate from getInfo() for display purposes
     * @return the test name
     */
    String getName();
    
    
    /**
     * Get summary about the test
     * Whatever info that is essential
     * @return General info about the test
     */
    String getInfo();
    
    
    /**
     * Get menu selector
     * possible collisions with same tag
     * @return The menu selector
     */
    String menuSelector();
    
    /**
     * Get test version
     * For test result purposes
     * @return The test version in string format
     */
    String getVersion();
    
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
     * Get result of a test
     * @return Instance with test result info
     */
    ITestResult getResult();
}
