
package com.tul.simunek.semproj.app.ui;

import java.util.List;
import com.tul.simunek.semproj.app.ITestWrapper;

public class DisplayStrings {
    private DisplayStrings() {
    }
    
    public static String startup() {
        var sb = new StringBuilder();
        sb.append("--- Benchmark GUI ---\n");
        return sb.toString();
    }
    
    public static String menu() {
        var sb = new StringBuilder();
        sb.append("Enter 'S' for system info\n");
        sb.append("Enter 'L' for test listing and launching\n");
        sb.append("Enter 'Q' to quit application\n");
        sb.append("Enter 'R' to list local results\n");
        sb.append("Enter 'H' to display help info");
        sb.append("Entry: ");
        return sb.toString();
    }
    
    public static String systemInfo(){
        // only simple info that is available to the JVM
        // to get more/other info, external library or OS level 
        // commands would have to be used
        
        var sb = new StringBuilder();
        var rt = Runtime.getRuntime();
        sb.append("--- JVM System info ---\n\n");
        sb.append("Available logical CPU cores: ").append(rt.availableProcessors()).append("\n");
        sb.append("Unallocated RAM (bytes): ").append(rt.freeMemory()).append("\n");
        sb.append("Maximum memory (bytes): ").append(rt.maxMemory() == Long.MAX_VALUE ? "no limit" : rt.maxMemory()).append("\n");
        sb.append("Total available RAM to JVM (bytes): ").append(rt.totalMemory());
        return sb.toString();
    }
    
    public static String createTestListing(List<ITestWrapper> list){
        var sb = new StringBuilder();
        var index = 1;
        sb.append("--- Available tests ---\n");
        sb.append("index) name (launch selector)\n");
        
        for (var wr : list){
            sb.append(index++).append(") ");
            sb.append(wr.getName()).append(" ");
            sb.append("(").append(wr.getMenuSelector()).append(")\n");
        }
        return sb.toString();
    }
    
    public static String selectTestDialog(){
        var sb = new StringBuilder();
        sb.append("Enter the test launch selector to launch it, or enter anything else to return.\n");
        sb.append("Entry: ");
        return sb.toString();
    }
    
    // TODO result listing
    public static String createResultListing(){
        return "";
    }
    
    public static String exitMessage(){
        return "Exiting...";
    }
    
    public static String helpMenu(){
        var sb = new StringBuilder();
        
        sb.append("--- HELP MENU ---\n");
        sb.append("GUI wrapper app for benchmarking hardware using multiple registered tests\n");
        sb.append("Description of individual selectors in menu: \n");
        sb.append("S - Displays system information that is available to the JVM\n");
        sb.append("L - Lists available tests and gives the ability to launch them by entering their selector name\n");
        sb.append("R - Not yet implemented\n");
        sb.append("Q - Quits the application\n");
        sb.append("Press ENTER to return to the main menu...\n");
        
        return sb.toString();
    }
}
