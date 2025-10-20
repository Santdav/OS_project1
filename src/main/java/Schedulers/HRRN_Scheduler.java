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
public class HRRN_Scheduler extends Scheduler {
    
    @Override
    public Process selectNextProcess(ProcessQueue readyQueue, Process currentRunning) {
        if (currentRunning != null) return currentRunning;
        if (readyQueue.isEmpty()) return null;
        
        Process bestProcess = null;
        double highestRatio = -1;
        // Iteración directa y limpia
        for (Process process : readyQueue) {
            double ratio = calculateResponseRatio(process);
            if (ratio > highestRatio) {
                highestRatio = ratio;
                bestProcess = process;
            }
        }
        if (bestProcess != null) {
            readyQueue.removeItem(bestProcess);
        }
        return bestProcess;
    }
    
    private double calculateResponseRatio(Process process) {
        int waitingTime = process.getWaitingTime();
        int serviceTime = process.getTotalInstructions()-process.getInstructionsExecuted();
        return serviceTime == 0 ? Double.MAX_VALUE : (waitingTime + serviceTime) / (double) serviceTime;
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
