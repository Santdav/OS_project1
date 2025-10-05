/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;
import dataStructures.ProcessQueue;
import dataStructures.StateProcess;
/**
 *
 * @author santi
 */
public class QueuesManager {
    private final ProcessQueue newQueue;
    private final ProcessQueue readyQueue;
    private final ProcessQueue blockedQueue;
    private final ProcessQueue suspendedReadyQueue;
    private final ProcessQueue suspendedBlockedQueue;
    private final ProcessQueue exitQueue;
    
    public QueuesManager() {
        this.newQueue = new ProcessQueue(StateProcess.NEW);
        this.readyQueue = new ProcessQueue(StateProcess.READY);
        this.blockedQueue = new ProcessQueue(StateProcess.BLOCKED);
        this.suspendedReadyQueue = new ProcessQueue(StateProcess.SUSPENDED_READY);
        this.suspendedBlockedQueue = new ProcessQueue(StateProcess.SUSPENDED_BLOCKED);
        this.exitQueue = new ProcessQueue(StateProcess.EXIT);
    }
}
