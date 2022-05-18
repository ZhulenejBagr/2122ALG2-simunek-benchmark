package main.Models.SciMark;


public class SciMarkConfig {
    private int testPasses;
    
    public int getTestPasses(){
        return testPasses;
    }
    
    public void setTestPasses(int tp){
        testPasses = tp;
    }
    
    public SciMarkConfig(int tp){
        testPasses = tp;
    }
}
