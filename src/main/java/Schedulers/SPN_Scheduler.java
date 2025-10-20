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
public class SPN_Scheduler extends Scheduler{
    
    @Override
    public Process selectNextProcess(ProcessQueue readyQueue, Process currentRunning) {
        if (currentRunning != null) return currentRunning;
        if (readyQueue.isEmpty()) return null;
        
        Process shortest = null;
        int minRemaining = Integer.MAX_VALUE;
        // Iteras DIRECTAMENTE sobre tu ProcessQueue (que es una LinkedList)
        for (Process process : readyQueue) {
            int remaining = process.getTotalInstructions()-process.getInstructionsExecuted();
            if (remaining < minRemaining) {
                minRemaining = remaining;
                shortest = process;
            }
        }
        if (shortest != null) {
            readyQueue.removeItem(shortest); // Usas el remove de LinkedList
        }
        return shortest;
    }

    @Override
    public String getName() {
        return "SPN (Shortest Process Next)";
    }
    
}
