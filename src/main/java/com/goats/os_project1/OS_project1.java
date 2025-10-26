/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.goats.os_project1;

import GuiElements.MainGui;
import Managers.ProcessesManager;
import Managers.SimulationEngine;
import Schedulers.Scheduler;
import Schedulers.SchedulerFactory;
/**
 *
 * @author santi
 */
public class OS_project1 {
    
    public static void main(String[] args) {
        
        ProcessesManager manager = new ProcessesManager();
        Scheduler scheduler = SchedulerFactory.createScheduler("FCFS");
        SimulationEngine engine = new SimulationEngine(manager, scheduler);
        Thread simulationThread = new Thread(engine);
        simulationThread.start();
        java.awt.EventQueue.invokeLater(() -> {
            MainGui gui = new MainGui(manager, engine);
            engine.setMainGui(gui);
            gui.setVisible(true);
        });
        
    }
}
