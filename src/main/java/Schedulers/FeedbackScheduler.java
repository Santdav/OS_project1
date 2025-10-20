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
class FeedbackScheduler extends Scheduler {
    private final ProcessQueue[] priorityQueues;  // Array de colas de prioridad
    private final int[] quantums;                 // Array de quantums por nivel
    private int currentQuantum;
    private Process currentProcess;
    private int currentQueueIndex;
    
    public FeedbackScheduler(int numQueues, int baseQuantum) {
        // Usamos arrays nativos en lugar de ArrayList
        this.priorityQueues = new ProcessQueue[numQueues];
        this.quantums = new int[numQueues];
        
        // Inicializar colas de prioridad
        for (int i = 0; i < numQueues; i++) {
            priorityQueues[i] = new ProcessQueue();
            quantums[i] = baseQuantum * (i + 1); // Quantum aumenta en niveles inferiores
        }
        this.currentQuantum = 0;
        this.currentProcess = null;
        this.currentQueueIndex = 0;
    }
    
    @Override
    public Process selectNextProcess(ProcessQueue readyQueue, Process currentRunning) {
        // 1. Mover procesos nuevos de readyQueue a la cola de mayor prioridad
        moveNewProcessesToFeedback(readyQueue);
        
        // 2. Buscar en todas las colas por orden de prioridad (0 = mayor prioridad)
        for (int i = 0; i < priorityQueues.length; i++) {
            if (!priorityQueues[i].isEmpty()) {
                Process nextProcess = priorityQueues[i].dequeue();
                currentProcess = nextProcess;
                currentQueueIndex = i; // Recordar de qué cola vino
                currentQuantum = 0;    // Resetear contador de quantum
                return nextProcess;
            }
        }
        
        // 3. No hay procesos en ninguna cola
        currentProcess = null;
        return null;
    }
    
    @Override
    public boolean shouldPreempt(Process currentRunning) {
        // Preemption cuando se agota el quantum del nivel actual
        return currentQuantum >= getCurrentQuantum();
    }
    
    @Override
    public void onCycleEnd() {
        // Incrementar contador de quantum después de cada ciclo
        currentQuantum++;
    }
    
    /**
     * Método para que el SimulationEngine notifique cuando un proceso es preemptado
     */
    public void onProcessPreempted(Process process) {
        if (process != null) {
            // Degradar el proceso a una cola de menor prioridad
            demoteProcess(process);
        }
    }
    
    /**
     * Mueve procesos nuevos de la readyQueue general a la cola de mayor prioridad
     */
    private void moveNewProcessesToFeedback(ProcessQueue readyQueue) {
        // Mover todos los procesos de la cola general a la cola de mayor prioridad
        while (!readyQueue.isEmpty()) {
            Process newProcess = readyQueue.dequeue();
            priorityQueues[0].enqueue(newProcess); // Nivel 0 = mayor prioridad
        }
    }
    
    /**
     * Degrada un proceso a una cola de menor prioridad
     */
    private void demoteProcess(Process process) {
        int nextQueueIndex = currentQueueIndex + 1;
        
        // Si hay siguiente nivel, mover allí; si no, mantener en el último nivel
        if (nextQueueIndex < priorityQueues.length) {
            priorityQueues[nextQueueIndex].enqueue(process);
        } else {
            // Ya está en el nivel más bajo, volver a ponerlo allí
            priorityQueues[priorityQueues.length - 1].enqueue(process);
        }
    }
    
    /**
     * Obtiene el quantum actual basado en la cola de donde vino el proceso
     */
    private int getCurrentQuantum() {
        return quantums[currentQueueIndex];
    }
    
    @Override
    public String getName() {
        return "Feedback (Niveles: " + priorityQueues.length + ", Quantum Base: " + quantums[0] + ")";
    }
    
    /**
     * Método para debugging - muestra el estado de todas las colas
     */
    public void printQueueStatus() {
        System.out.println("=== Estado Colas Feedback ===");
        for (int i = 0; i < priorityQueues.length; i++) {
            System.out.println("Nivel " + i + " (Quantum: " + quantums[i] + "): " + 
                             priorityQueues[i].size() + " procesos");
        }
    }
}
