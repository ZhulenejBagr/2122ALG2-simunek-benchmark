package com.tul.simunek.semproj.app;


public interface ITestResult {
    double getScore();
    
    int getExitCode();
    Exception getException();
}
