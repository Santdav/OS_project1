/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Schedulers;
import dataStructures.Process;
import dataStructures.ProcessQueue;

/**
 *
 * @author santi
 */
public abstract class Scheduler {

    /**
     * Selecciona el próximo proceso a ejecutar en la ÚNICA CPU
     * @param readyQueue Cola de procesos listos
     * @param currentRunning Proceso actual en ejecución (puede ser null)
     * @return El proceso que debe ejecutarse, o null si la CPU debe estar idle
     */
    public abstract Process selectNextProcess(ProcessQueue readyQueue, Process currentRunning);
    public abstract String getName();
    
    /**
     * Indica si debe haber preemption del proceso actual
     * @param currentRunning Proceso actual en ejecución
     * @return true si debe haber preemption
     */
    public boolean shouldPreempt(Process currentRunning){
        return false;
    }
    /**
     * Método llamado al final de cada ciclo
     */
    public void onCycleEnd(){
        
    }
}
