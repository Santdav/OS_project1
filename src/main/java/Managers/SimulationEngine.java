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

    public long getCurrentCycle() {
        return currentCycle;
    }
    
    /**
     * El hilo de ejecucion para el simulador.
     */
    @Override
    public void run() {
        this.isRunning = true;
        try {
            while (isRunning){
                currentCycle++;
                processManager.checkEventCompletion();
                processManager.executeCurrentProcess();
                Thread.sleep(cycleDuration); // El sleep hace que se cumpla la duracion de ciclo designada por el usuario.
            }       // Se rodea con un try catch por si, por ejemplo, se cierra la aplicacion durante este sleep.
        } catch (InterruptedException ex) {
                System.err.println("The simulation has been interrupted.");
                this.isRunning = false;
        }
            
            
    }
}
