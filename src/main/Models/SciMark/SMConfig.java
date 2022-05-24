package main.Models.SciMark;


public class SMConfig {
    private int testPasses;
    
    
    public int getTestPasses(){
        return testPasses;
    }
    
    public void setTestPasses(int tp){
        testPasses = tp;
    }
    
    public SMConfig(int tp){
        testPasses = tp;
    }
    
    public SMConfig(){
        testPasses = SMConstants.DEFAULT_TEST_PASSES;
    }
    
    public boolean isValid(){
        if (testPasses < SMConstants.MIN_TEST_PASSES || testPasses > SMConstants.MAX_TEST_PASSES){
            return false;
        }
        // additional conditions when others config etries are added
        
        return true;
    }
}
