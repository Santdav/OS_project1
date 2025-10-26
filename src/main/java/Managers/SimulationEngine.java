/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;

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
        /*
        EJECUCION POR CICLO
        1. incrementar el reloj CHECK    YES
        2.Actualizar colas               YES
        3. Ejecutar proceso actual       YES
        4. Consultar Scheduler
        5. Preemtion (si aplica)
        6. Nueva asignacion a running
        7. UI
         */
        this.isRunning = true;
        Process runningProcess = this.processManager.getRunningProcess();
        
        try {
            while (isRunning) {
                currentCycle++; //1.
                processManager.checkEventCompletion(); //2.
                processManager.executeCurrentProcess(); //3. 

                Thread.sleep(cycleDuration); // El sleep hace que se cumpla la duracion de ciclo designada por el usuario.
                
                //Proceso escogido por el chdules es distointo al actual y debe de haber preemtion entonces cambia el proceso
                if (currentScheduler.selectNextProcess(this.processManager.queuesManager.getReadyQueue(), runningProcess) != runningProcess || currentScheduler.shouldPreempt(runningProcess)) {
                    this.processManager.queuesManager.moveToRunning(currentScheduler.selectNextProcess(this.processManager.queuesManager.getReadyQueue(), runningProcess));
                }
            }       // Se rodea con un try catch por si, por ejemplo, se cierra la aplicacion durante este sleep.

        } catch (InterruptedException ex) {
            System.err.println("The simulation has been interrupted.");
            this.isRunning = false;
        }
    }

    public void setCycleDuration(int cycleDuration) {
        this.cycleDuration = cycleDuration;
    }

}
