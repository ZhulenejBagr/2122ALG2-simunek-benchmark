
package com.tul.simunek.semproj.app.scimark;

import com.tul.simunek.semproj.app.ITestLogger;
import com.tul.simunek.semproj.app.utils.Stopwatch;
import com.tul.simunek.semproj.app.utils.TextFileTools;
import com.tul.simunek.semproj.app.utils.TimeTools;


public class SMLogger implements ITestLogger {
    private String path;
    private final Stopwatch sw;
    
    public SMLogger() {
        sw = new Stopwatch();
    }
    
    @Override
    public String getfilePath() {
        return path;
    }

    @Override
    public void setFilePath(String path) {
        this.path = path; 
    }

    @Override
    public boolean WriteToFile(String[] content) {
        for (int i = 0; i < content.length; i++){
            content[i] = addTimeToString(content[i]);
        }
        return TextFileTools.tryWriteToFile(this.path, content, SMWrapper.getEncoding());
    }
    
    public boolean WriteToFile(String content) {
        return WriteToFile(new String[] {content});
    }
    
    private String addTimeToString(String line){
        var sb = new StringBuilder();
        var time = sw.getMilisFromStart();
        sb.append("[").append(TimeTools.formatMilis(time)).append("] ");
        sb.append(line);
        return sb.toString();
    }

    @Override
    public void startLogging() {
        TextFileTools.tryEmptyFile(path);
    }
    
}
