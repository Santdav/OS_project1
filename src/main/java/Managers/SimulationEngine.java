/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;

import GuiElements.MainGui;
import Schedulers.Scheduler;
import dataStructures.Process;

/**
 *
 * @author santi
 */
public class SimulationEngine implements Runnable {

    private final ProcessesManager processManager;
    private Scheduler currentScheduler;
    private boolean isRunning;
    private long currentCycle;
    private volatile int cycleDuration; // En milisegundos (ms)
    private MainGui mainGui;

    public SimulationEngine(ProcessesManager processManager, Scheduler currentScheduler) {
        this.processManager = processManager;
        this.currentScheduler = currentScheduler;
        this.currentCycle = 0;
        this.cycleDuration = 1000;
        this.mainGui = null;
    }
    
    public void setMainGui(MainGui gui) {
        this.mainGui = gui;
    }

    public long getCurrentCycle() {
        return currentCycle;
    }

    /**
     * El hilo de ejecucion para el simulador.
     */
    @Override
    public void run() {
        /*
        EJECUCION POR CICLO
        1. incrementar el reloj CHECK    
        2. Actualizar colas              
        3. Consultar Scheduler           
        4. Ejecutar proceso actual 
        5. Preemtion (si aplica)
        6. Nueva asignacion a running
        7. UI
         */
        this.isRunning = true;        
        try {
            while (isRunning) {
                
                currentCycle++; //1.
                System.out.println(getCurrentCycle());
                processManager.admitNewProcess();
                processManager.checkEventCompletion(); //2.
                Process runningProcess = this.processManager.getRunningProcess();

                //Proceso escogido por el chdules es distointo al actual y debe de haber preemtion entonces cambia el proceso
                if (currentScheduler.selectNextProcess(this.processManager.queuesManager.getReadyQueue(), runningProcess) != runningProcess || currentScheduler.shouldPreempt(runningProcess)) {
                    this.processManager.queuesManager.moveToRunning(currentScheduler.selectNextProcess(this.processManager.queuesManager.getReadyQueue(), runningProcess));
                }
                processManager.executeCurrentProcess(); //4. 
                if (this.mainGui != null) {
                    this.mainGui.updateQueueDisplay();
                }
                
                
                Thread.sleep(cycleDuration); // El sleep hace que se cumpla la duracion de ciclo designada por el usuario.
            }       // Se rodea con un try catch por si, por ejemplo, se cierra la aplicacion durante este sleep.

        } catch (InterruptedException ex) {
            System.err.println("The simulation has been interrupted.");
            this.isRunning = false;
        }
        System.out.println(this.getCurrentCycle());
    }

    public void setCycleDuration(int cycleDuration) {
        this.cycleDuration = cycleDuration;
    }

    public ProcessesManager getProcessManager() {
        return processManager;
    }

    public void setCurrentScheduler(Scheduler currentScheduler) {
        this.currentScheduler = currentScheduler;
    }

}
