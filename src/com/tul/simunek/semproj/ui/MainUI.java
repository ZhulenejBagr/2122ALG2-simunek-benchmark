
package com.tul.simunek.semproj.ui;

import com.tul.simunek.semproj.app.ITestResult;
import com.tul.simunek.semproj.app.Launcher;
import com.tul.simunek.semproj.app.ResultDatabase;
import java.util.Scanner;


public class MainUI {
    private MenuActions action;
    private final Scanner sc = new Scanner(System.in);
    private final Launcher l;
    private final ResultDatabase database;
    
    /**
     * Hlavní metoda uživatelského rozhraní
     */
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
        database = new ResultDatabase();
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
                System.out.println("--- LAUNCHING TEST --- \n\n");
                test.launch();
                var result = test.getResult();
                if (result.isScoreValid()){
                    database.addResult(test.getResult());
                }
                printTestFinished(result);
                return;
            }
        } 
    }
    
    private void printHelp(){
        System.out.println(DisplayStrings.helpMenu());
        sc.next();
    }
    
    private void printResultListing(){
        System.out.println(DisplayStrings.createResultListing(database.getResults()));
        System.out.println("\nEnter 'S' to sort the results, else enter anything else to return to the main menu");
        var i = getFirstChar();
        if (i == 'S') {
            database.sortByTestThenByScore();
            System.out.println(DisplayStrings.createResultListing(database.getResults()));
        }
    }
    
    private void quit(){
        System.out.println(DisplayStrings.exitMessage());
    }
    
    private void printTestFinished(ITestResult result){
        System.out.println(DisplayStrings.formatResult(result));
    }
}
