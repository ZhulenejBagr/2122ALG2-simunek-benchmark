
package com.tul.simunek.semproj.ui;

import com.tul.simunek.semproj.app.ITestResult;
import java.util.List;
import com.tul.simunek.semproj.app.ITestWrapper;

public class DisplayStrings {
    private DisplayStrings() {
    }
    
    /**
     * Řetězec, který se vypisuje při zapnutí aplikace
     * @return příslušný řetězec
     */
    public static String startup() {
        var sb = new StringBuilder();
        sb.append("--- Benchmark GUI ---\n");
        return sb.toString();
    }
    
    
    /**
     * Řetězec menu
     * @return příslušný řetězec
     */
    public static String menu() {
        var sb = new StringBuilder();
        sb.append("Enter 'S' for system info\n");
        sb.append("Enter 'L' for test listing and launching\n");
        sb.append("Enter 'Q' to quit application\n");
        sb.append("Enter 'R' to list local results\n");
        sb.append("Enter 'H' to display help info\n");
        sb.append("Entry: ");
        return sb.toString();
    }
    
    
    /**
     * Řetězec se systémovými informacemi
     * @return příslušný řetězec
     */
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
    
    
    /**
     * Výpis všech dostupných testů
     * @param list list s testy
     * @return řetězec se zformátovaným výpisem
     */
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
    
    
    /**
     * Dialog pro výběr testu ke spuštění
     * @return příslušný řetězec
     */
    public static String selectTestDialog(){
        var sb = new StringBuilder();
        sb.append("Enter the test launch selector to launch it, or enter anything else to return.\n");
        sb.append("Entry: ");
        return sb.toString();
    }
    
    
    /**
     * Výpis výsledků testů v lokální databázi
     * @param list list výsledků
     * @return zformátovaný řetězec s výpisem výsledků
     */
    public static String createResultListing(List<ITestResult> list){
        var sb = new StringBuilder();
        sb.append(" --- RESULT LISTING ---\n\n");
        sb.append("Listing ").append(list.size()).append(" test results\n\n");
        sb.append("    TESTNAME       SCORE        TIME           DATE   DURATION_ms           SYS_INFO\n\n");
         
        for (var r : list){
            sb.append(String.format("%12s", r.getTestID()));
            sb.append(String.format("%12s", String.format("%.01f", r.getTestScore())));
            var t = r.getStartTime();
            sb.append(String.format("%13s", String.format("%02d:%02d:%02d", t.getHour(), t.getMinute(), t.getSecond())));
            sb.append(String.format("%14s", String.format("%d.%d.%d", t.getDayOfMonth(), t.getMonthValue(), t.getYear())));
            sb.append(String.format("%14s", String.format("%.03f", r.getTestRunTimeMilis() / 1000d))).append("    ");
            
            for (var s : r.getTestSystemInfo()){
                sb.append(s).append(", ");
            }
            
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
    /**
     * Zpráva na výpis při ukončení aplikace
     * @return příslušný řetězec
     */
    public static String exitMessage(){
        return "Exiting...";
    }
    
    /**
     * Výpis pomoci k příkazům
     * @return příslušný řetězec
     */
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
    
    /**
     * Zformátování jednotlivého výsledku po dokončení testu
     * @param result výsledek ke zformátování
     * @return příslušný řetězec
     */
    public static String formatResult(ITestResult result){
        var sb = new StringBuilder();
        sb.append("Test ").append(result.getTestID());
        sb.append(" finished with a score of ").append(result.getTestScore());
        sb.append(".\n").append("returning to main menu");
        return sb.toString();
    }
}
