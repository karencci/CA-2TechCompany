/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package ca_2techcompany;

/**
 *
 * @author Administrator
 */
public enum MenuOption {
    SORT(1),
    SEARCH(2),
    ADD(3),
    GENERATE_RANDOM(4),
    EXIT(5);        
    

    private final int code;
    
    MenuOption(int c) { code = c; }
    public int getCode() { return code; }

    public static MenuOption fromInt(int i) {
        for (MenuOption m : MenuOption.values()) {
            if (m.getCode() == i) {
                return m;
    
            }
        }
        return null; 
}
}
