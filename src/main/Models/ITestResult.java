package main.Models;


public interface ITestResult {
    double getScore();
    
    int getExitCode();
    Exception getException();
}
