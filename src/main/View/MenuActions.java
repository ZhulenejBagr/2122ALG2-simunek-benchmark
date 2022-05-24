
package main.View;


public enum MenuActions {
    SYS_INFO('i'),
    TEST_LISTING('l'),
    RESULT_LISTING('r'),
    QUIT('q'),
    UNRECOGNIZED(Character.MIN_VALUE);
    
    private final char menuSelector;
    
    public char getSelector(){
        return menuSelector;
    }
    
    MenuActions(char menuSelector){
        this.menuSelector = menuSelector;
    }
}
