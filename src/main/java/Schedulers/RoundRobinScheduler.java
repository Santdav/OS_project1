/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Schedulers;

import dataStructures.ProcessQueue;
import dataStructures.Process;

/**
 *
 * @author santi
 */
public class RoundRobinScheduler extends Scheduler{
    
    private final int quantum;
    private int currentQuantum;
    
    public RoundRobinScheduler(int quantum) {
        this.quantum = quantum;
        this.currentQuantum = 0;
    }
    
    @Override
    public Process selectNextProcess(ProcessQueue readyQueue, Process currentRunning) {
        // Si se agotó el quantum, preemptar
        if (currentQuantum >= quantum && currentRunning != null) {
            currentQuantum = 0;
            // Devolver el proceso actual a la cola
            readyQueue.enqueue(currentRunning);
            // Tomar el siguiente de la cola
            return readyQueue.isEmpty() ? null : readyQueue.dequeue();
        }  
        // Si no hay proceso ejecutándose, tomar uno nuevo
        if (currentRunning == null && !readyQueue.isEmpty()) {
            currentQuantum = 0;
            return readyQueue.dequeue();
        }
        // Continuar con el proceso actual
        return currentRunning;
    }
    
    @Override
    public boolean shouldPreempt(Process currentRunning) {
        return currentQuantum >= quantum;
    }
    
    @Override
    public void onCycleEnd() {
        if (currentQuantum < quantum) {
            currentQuantum++;
        }
    }

    @Override
    public String getName() {
        return "Round Robin";
    }
    
    
}
