
package main.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import main.Models.ITestWrapper;
import main.Models.SciMark.SMWrapper;


public class Launcher {
    // TODO separate GUI from inner launcher logic
    private final Scanner sc = new Scanner(System.in);
    
    private final List<ITestWrapper> testList;
    private MenuActions action;
    
    public void launch(){
        while (action != MenuActions.QUIT){
            action = MenuActions.UNRECOGNIZED;
            System.out.println(DisplayStrings.startup());

            System.out.println(DisplayStrings.menu());
            getUserInput();

            switchMenuAction();
        }
    }
    
    public Launcher(){
        action = MenuActions.UNRECOGNIZED;
        testList = new ArrayList<>();
        testList.add(new SMWrapper());
    }
    
    public static void main(String[] args) {
        Launcher l = new Launcher();
        l.launch();
    }
    
    private void getUserInput(){
        char c = getFirstChar();
        for (var ma : MenuActions.values()){
            if (c == ma.getSelector()){
                action = ma;
                return;
            }
        }
        action = MenuActions.UNRECOGNIZED;
    }
    
    private char getFirstChar(){
        return sc.next().charAt(0);
    }
    
    private void switchMenuAction(){
        switch (action) {
            case SYS_INFO -> printSysInfo();
            case TEST_LISTING -> launchableTestListing();
            case RESULT_LISTING -> printResultListing();
            case QUIT -> quit();
            case HELP -> printHelp();
            default -> {}
        }
    }
    
    
    private void printSysInfo(){
        System.out.println(DisplayStrings.systemInfo());
    }
    
    private void launchableTestListing(){
        // print available tests
        System.out.println(DisplayStrings.createTestListing(testList));
        
        // take user input on what test to launch
        System.out.println(DisplayStrings.selectTestDialog());
        var select = sc.next();
        for (var test : testList){
            if (select == null ? test.getMenuSelector() == null : select.equals(test.getMenuSelector())){
                test.launch();
                return;
            }
        } 
    }
    
    private void printHelp(){
        System.out.println(DisplayStrings.helpMenu());
        sc.next();
    }
    
    private void printResultListing(){
        System.out.println(DisplayStrings.createResultListing());
    }
    
    private void quit(){
        System.out.println(DisplayStrings.exitMessage());
    }
}
