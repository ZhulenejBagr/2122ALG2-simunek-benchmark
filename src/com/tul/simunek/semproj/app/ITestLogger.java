
package com.tul.simunek.semproj.app;


public interface ITestLogger {
    /**
     * Zjištění současně používané cesty logger souboru
     * @return absolutní cesta k logger souboru
     */
    String getfilePath();
    
    /**
     * Nastavení cesty k logger souboru
     * @param path nová cesta
     */
    void setFilePath(String path);
    
    /**
      Zápis textu do logger souboru
     * @param content obsah, který se má zapsat
     * @return jestli byl zápis úspěšný
     */
    boolean WriteToFile(String[] content);
    
    /**
     * Začít logování
     */
    void startLogging();
}
