
package main.View;

import java.util.List;
import main.Models.ITestWrapper;

public class DisplayStrings {
    private DisplayStrings() {
    }
    
    public static String startup() {
        var sb = new StringBuilder();
        sb.append("Benchmark GUI\n");
        return sb.toString();
    }
    
    public static String menu() {
        var sb = new StringBuilder();
        sb.append("Enter 'i' for system info\n");
        sb.append("Enter 'l' for test listing and launching\n");
        sb.append("Enter 'q' to quit application\n");
        sb.append("Enter 'r' to list local results\n");
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
        
        for (var wr : list){
            sb.append(index++).append(") ");
            sb.append(wr.getName()).append("\n");
        }
        return sb.toString();
    }
    
    // TODO result listing
    public static String createResultListing(){
        return "";
    }
}
