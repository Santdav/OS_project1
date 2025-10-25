/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

import Utils.IdGenerator;
import dataStructures.Enums.ProcessType;
import dataStructures.Enums.StateProcess;

   

/**
 *
 * @author santi
 */
public class Process {
    // ===== IDENTIFICACIÓN DEL PROCESO (PCB) =====
    private long id;
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
    private ProcessType type;           //IO_BOUND o CPU_BOUND
    private int totalInstructions;
    private int instructionsExecuted;     // Para saber progreso real
    private long arrivalTime;              // Para métricas de tiempo de espera
    private int waitingTime;              // Para calcular eficiencia
    private boolean ioRequestPending;     // Para saber si está en E/S
    private int ioRemainingTime;          // Para contar down de E/S
    private int memoryRequired;  
    
    
    public Process(long id, String name, ProcessType type, int totalInstructions,
                  int ioExceptionCycle, int ioCompletionCycle, long arrivalTime, 
                  int priority, int memoryRequired) {
        // ===== IDENTIFICACIÓN =====
        this.id = id;      //ID dada por el IDGenerator
        this.name = name;  //Dado por user input
        
        // ===== CARACTERÍSTICAS DEL PROCESO =====
        this.type = type;
        this.totalInstructions = totalInstructions;
        this.priority = priority;
        this.memoryRequired = memoryRequired;
        
        // ===== CONFIGURACIÓN DE E/S =====
        this.ioExceptionCycle = ioExceptionCycle; //calculado a parte por la cantidad de instrucciones que tarda el IO
        this.ioCompletionCycle = ioCompletionCycle;
        
        // ===== TIEMPOS Y ESTADO INICIAL =====
        this.arrivalTime = arrivalTime;
        this.state = StateProcess.NEW;
        this.programCounter = 0;
        this.MemoryAdressRegister = 0;
        this.instructionsExecuted = 0;
        this.cpuTimeUsed = 0;
        this.waitingTime = 0;
        this.ioRequestPending = false;
        this.ioRemainingTime = 0;
    }
    
    
    // Para procesos CPU-Bound (sin E/S)
    public static Process createCPUProcess(String name, int totalInstructions, 
                                          long arrivalTime) {
        long newId = IdGenerator.nextId();
        return new Process(newId, name, ProcessType.CPU_BOUND, totalInstructions,
                          0, 0, arrivalTime, 0, 1);
    }

    // Para procesos I/O-Bound (con E/S)
    public static Process createIOProcess(String name, int totalInstructions,
                                         int ioExceptionCycle, int ioCompletionCycle,
                                         long arrivalTime) {
        long newId = IdGenerator.nextId();
        return new Process(newId, name, ProcessType.IO_BOUND, totalInstructions,
                          ioExceptionCycle, ioCompletionCycle, arrivalTime, 0, 1);
    }
    
    public boolean isIoBound() {
    return type == ProcessType.IO_BOUND;
    }
    
    public boolean isSuspended() {
    return state == StateProcess.SUSPENDED_READY || 
           state == StateProcess.SUSPENDED_BLOCKED;
    }
    
    public StateProcess getState() {
        return state;
    }

    public int getIoRemainingTime() {
        return ioRemainingTime;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public int getIoExceptionCycle() {
        return ioExceptionCycle;
    }

    public int getIoCompletionCycle() {
        return ioCompletionCycle;
    }
    
    public void setState(StateProcess state){
        this.state = state;
    }

    public void increasePCandMAR(){
        this.programCounter++;
        this.MemoryAdressRegister++;
    }
    
    public void decreaseWaitingTime() {
        this.ioRemainingTime--;
    }

    public void setIoRequestPending(boolean ioRequestPending) {
        this.ioRequestPending = ioRequestPending;
    }

    public void setIoRemainingTime(int ioRemainingTime) {
        this.ioRemainingTime = ioRemainingTime;
    }
    
    
    
    @Override
    public String toString() {
        return "Process{" + "id=" + id + ", name=" + name + ", state=" + state + '}';
    }

    public int getTotalInstructions() {
        return totalInstructions;
    }

    public int getInstructionsExecuted() {
        return instructionsExecuted;
    }

    public long getId() {
        return id;
    }

    public int getWaitingTime() {
        return waitingTime;
    }
    
    
    
}
