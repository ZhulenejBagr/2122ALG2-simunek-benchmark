
package main.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import main.Models.ITestWrapper;
import main.Models.SciMark.SMWrapper;


public class Launcher {
    private final Scanner sc = new Scanner(System.in);
    
    
    private final List<ITestWrapper> testList;
    private MenuActions action;
    
    public void launch(){
        // TODO while loop until quit is selected
        System.out.println(DisplayStrings.startup());
        
        System.out.println(DisplayStrings.menu());
        getUserInput();
        
        switchMenuAction();
        
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
            // TODO test listing should also allow launching
            case TEST_LISTING -> printTestListing();
            case RESULT_LISTING -> printResultListing();
            case QUIT -> quit();
            default -> {}
        }
    }
    
    
    private void printSysInfo(){
        System.out.println(DisplayStrings.systemInfo());
    }
    
    private void printTestListing(){
        System.out.println(DisplayStrings.createTestListing(testList));
    }
    
    private void printResultListing(){
        System.out.println(DisplayStrings.createResultListing());
    }
    
    private void quit(){
        
    }
}
