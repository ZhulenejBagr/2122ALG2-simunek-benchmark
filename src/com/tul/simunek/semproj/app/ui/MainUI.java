
package com.tul.simunek.semproj.app.ui;

import com.tul.simunek.semproj.app.Launcher;
import java.util.Scanner;


public class MainUI {
    private MenuActions action;
    private final Scanner sc = new Scanner(System.in);
    private Launcher l;
    
    public void launch(){
        while (action != MenuActions.QUIT){
            action = MenuActions.UNRECOGNIZED;
            System.out.println(DisplayStrings.startup());

            System.out.println(DisplayStrings.menu());
            getUserInput();

            switchMenuAction();
        }
    }
    
    public MainUI(){
        action = MenuActions.UNRECOGNIZED;
        l = new Launcher();
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
        // get test listing from launcher
        var tests = l.getList();

        // print available tests
        System.out.println(DisplayStrings.createTestListing(tests));
        
        // take user input on what test to launch or to quit
        System.out.println(DisplayStrings.selectTestDialog());
        var select = sc.next();
        for (var test : tests){
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
