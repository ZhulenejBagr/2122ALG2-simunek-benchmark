
package com.tul.simunek.semproj.app;


public interface ITestLogger {
    String getfilePath();
    void setFilePath(String path);
    boolean WriteToFile(String[] content);
    void startLogging();
}
