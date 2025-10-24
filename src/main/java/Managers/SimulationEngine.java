/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;

import Schedulers.Scheduler;

/**
 *
 * @author santi
 */
public class SimulationEngine implements Runnable{
    private final ProcessesManager processManager;
    private Scheduler currentScheduler;
    private boolean isRunning;
    private long currentCycle;
    private volatile int cycleDuration; // En milisegundos (ms)

    public SimulationEngine(ProcessesManager processManager, Scheduler currentScheduler) {
        this.processManager = processManager;
        this.currentScheduler = currentScheduler;
        this.currentCycle = 0;
        this.cycleDuration = 1000;
    }
    
    @Override
    public void run() {
        while (isRunning){
            currentCycle++;
            
            
        }
    }

}
