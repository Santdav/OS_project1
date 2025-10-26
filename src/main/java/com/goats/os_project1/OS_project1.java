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
        
        System.out.println("Hello World!");
        ProcessesManager manager = new ProcessesManager();
        Scheduler scheduler = SchedulerFactory.createScheduler("FCFS");
        SimulationEngine engine = new SimulationEngine(manager, scheduler);
        java.awt.EventQueue.invokeLater(() -> {
                        new MainGui(manager, engine).setVisible(true);});
        
    }
}
