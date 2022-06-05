
package com.tul.simunek.semproj.app.ui;


public enum MenuActions {
    SYS_INFO('S'),
    TEST_LISTING('L'),
    RESULT_LISTING('R'),
    QUIT('Q'),
    HELP('H'),
    UNRECOGNIZED(Character.MIN_VALUE);
    
    private final char menuSelector;
    
    public char getSelector(){
        return menuSelector;
    }
    
    MenuActions(char menuSelector){
        this.menuSelector = menuSelector;
    }
}
