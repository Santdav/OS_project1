/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

/**
 *
 * @author santi
 */
public class IdGenerator {
    private static long currentId = 0;
    
    /**
     * Generates a new unique ID
     * @return a new unique long value
     */
    public static long nextId() {
        return ++currentId;
    }
    
    /**
     * Gets the current ID without incrementing
     * @return the current ID value
     */
    public static long getCurrentId() {
        return currentId;
    }

}
