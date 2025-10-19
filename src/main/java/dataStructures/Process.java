/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import dataStructures.Enums.ProcessType;
import dataStructures.Enums.StateProcess;

   

/**
 *
 * @author santi
 */
public class Process {
    // ===== IDENTIFICACIÓN DEL PROCESO (PCB) =====
    private int id;
    private String name;  
    // ===== INFORMACIÓN DE ESTADO (PCB) =====
    private StateProcess state;
    private int programCounter;
    private int MemoryAdressRegister;
    
    // ===== INFORMACIÓN DE CONTROL (PCB) =====
    private int priority;
    private int ioExceptionCycle;     // Ciclos para generar excepción E/S
    private int ioCompletionCycle;    // Ciclos para completar E/S
    private int startTime;            // Ciclo cuando entró al sistema
    private int completionTime;        // Ciclo cuando terminó
    private int cpuTimeUsed;           // Ciclos de CPU utilizados

    // ===== CARACTERÍSTICAS DEL PROCESO =====
    private ProcessType type;
    private int totalInstructions;
    private int instructionsExecuted;     // Para saber progreso real
    private int arrivalTime;              // Para métricas de tiempo de espera
    private int waitingTime;              // Para calcular eficiencia
    private boolean ioRequestPending;     // Para saber si está en E/S
    private int ioRemainingTime;          // Para contar down de E/S
    private int memoryRequired;  
    
    
    public StateProcess getState() {
        return state;
    }
    
    public void setState(StateProcess state){
        this.state = state;
    }
}
