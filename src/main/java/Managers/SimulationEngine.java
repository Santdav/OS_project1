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
public class SimulationEngine {
    private final ProcessesManager processManager;
    private Scheduler currentScheduler;
    private boolean isRunning = false;

    public SimulationEngine(ProcessesManager processManager, Scheduler currentScheduler) {
        this.processManager = processManager;
        this.currentScheduler = currentScheduler;
    }

}
