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
public class FeedbackScheduler extends Scheduler{
    @Override
    public Process selectNextProcess(ProcessQueue readyQueue, Process currentRunning) {
        if (currentRunning != null) return currentRunning;
        return readyQueue.isEmpty() ? null : readyQueue.dequeue();
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
