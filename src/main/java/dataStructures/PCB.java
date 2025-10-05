/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

   

/**
 *
 * @author santi
 */
public class PCB {
    //Process identification
    private int id;
    private String name;    
    //Process State information
    private int PC;
    private int MAR;
        //Status information
    
        
    //Process Control
    private StateProcess state;
    private int priority;
    
    private boolean isCPUBound;
    private int totalInstructions;
    
    private int ioExceptionCycle;     // Ciclos para generar excepción E/S
    private int ioCompletionCycle;    // Ciclos para completar E/S

    private int startTime;                  // Ciclo cuando entró al sistema
    private int completionTime;             // Ciclo cuando terminó
    private int cpuTimeUsed;                // Ciclos de CPU utilizados
    
    
    
}
