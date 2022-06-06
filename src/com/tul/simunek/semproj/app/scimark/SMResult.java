
package com.tul.simunek.semproj.app.scimark;

import com.tul.simunek.semproj.app.ITestResult;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


public class SMResult implements ITestResult {
    private final static String TEST_ID = "SciMark";
    private final boolean isValid;
    private final double score;
    
    private final LocalDateTime startTime;
    private final LocalDateTime finishTime;
    
    private final String[] systemInfo;
    
    public SMResult(LocalDateTime startTime, LocalDateTime finishTime, String[] systemInfo, double score){
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.systemInfo = systemInfo;
        this.score = score;
        this.isValid = score > 0;
    }
    
    public SMResult(){
        startTime = LocalDateTime.now();
        finishTime = LocalDateTime.now();
        isValid = false;
        score = -1;
        systemInfo = new String[0];
    }
    
    @Override
    public String getTestID() {
        return TEST_ID;
    }

    @Override
    public double getTestScore() {
        return score;
    }

    @Override
    public String[] getTestSystemInfo() {
        return systemInfo;
    }

    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public LocalDateTime getEndTime() {
        return finishTime;
    }

    @Override
    public long getTestRunTimeMilis() {
        return ChronoUnit.MILLIS.between(startTime, finishTime);
    }
    
    @Override
    public boolean isScoreValid(){
        return isValid;
    }

    @Override
    public int compareTo(ITestResult o) {
        return TEST_ID.compareTo(o.getTestID());
    }
}
