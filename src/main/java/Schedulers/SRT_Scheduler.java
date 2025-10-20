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
public class SRT_Scheduler extends Scheduler{
    
    private Process selectedProcess;
    
    @Override
    public Process selectNextProcess(ProcessQueue readyQueue, Process currentRunning) {
        Process bestCandidate = currentRunning;
        int minRemaining = (currentRunning != null) ? 
            currentRunning.getTotalInstructions()-currentRunning.getInstructionsExecuted() : Integer.MAX_VALUE;
        //  Iteración directa sobre la ProcessQueue
        for (Process process : readyQueue) {
            int remaining = currentRunning.getTotalInstructions()-currentRunning.getInstructionsExecuted();
            if (remaining < minRemaining) {
                minRemaining = remaining;
                bestCandidate = process;
            }
        }
        selectedProcess = bestCandidate;
        return bestCandidate;
    }
    
    @Override
    public boolean shouldPreempt(Process currentRunning) {
        return selectedProcess != null && 
               currentRunning != null && 
               selectedProcess != currentRunning;
    }
    
    @Override
    public String getName() {
        return "SRT (Shortest Remaining Time)";
    }
    
}
