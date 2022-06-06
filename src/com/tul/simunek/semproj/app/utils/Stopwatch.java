
package com.tul.simunek.semproj.app.utils;


public class Stopwatch {
    private final long startMilis;
    private long lastCheckpoint;
    
    public Stopwatch(){
        startMilis = currentTime();
    }
    
    public void setLastCheckpoint(){
        lastCheckpoint = currentTime();
    }
    
    public long getMilisFromStart(){
        return currentTime() - startMilis;
    }
    
    public long getMilisFromCheckpoint(){
        return currentTime() - lastCheckpoint
;    }
    
    private long currentTime(){
        return System.currentTimeMillis();
    }
}
