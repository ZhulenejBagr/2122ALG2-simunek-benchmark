package com.tul.simunek.semproj.app.scimark;


public class SMConfig {
    private int testPasses;
    
    /**
     * Zjištění množství opakování testu v configu
     * @return počet testů
     */
    public int getTestPasses(){
        return testPasses;
    }
    
    /**
     * Nastavení množství opakování v configu
     * @param tp nové množství opakování
     */
    public void setTestPasses(int tp){
        testPasses = tp;
    }
    
    public SMConfig(int tp){
        testPasses = tp;
    }
    
    // default config
    public SMConfig(){
        testPasses = SMConstants.DEFAULT_TEST_PASSES;
    }
    
    /**
     * Validita configu
     * obsahuje kontrolu všech parametrů testu
     * @return hodnota validity
     */ 
    public boolean isValid(){
        if (testPasses < SMConstants.MIN_TEST_PASSES || testPasses > SMConstants.MAX_TEST_PASSES){
            return false;
        }
        // additional conditions when others config etries are added
        
        return true;
    }
}
