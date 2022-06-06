
package com.tul.simunek.semproj.app.utils;

public class TimeTools {
    private TimeTools(){
        
    }
    
    public static String formatMilis(long milis) {
        var seconds = milis / 1000;
        milis %= 1000;
        var hours = seconds / 3600;
        seconds %= 3600;
        var minutes = seconds / 60;
        seconds %= 60;
        
        return String.format("%02d %02d %02d.%03d", hours, minutes, seconds, milis);
    }
    
    public static void main(String[] args) {
        System.out.println(formatMilis(3725003));
    }
}
