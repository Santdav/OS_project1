/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Schedulers;

import dataStructures.Process;
import dataStructures.ProcessQueue;

/**
 *
 * @author santi
 */
public class FCFS_Scheduler extends Scheduler{

    @Override
    public Process selectNextProcess(ProcessQueue readyQueue) {
        if (readyQueue.isEmpty()){
            return null;
        }
        return readyQueue.dequeue();
    }
    
}
