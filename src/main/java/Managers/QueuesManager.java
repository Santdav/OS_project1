/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;
import dataStructures.ProcessQueue;
import dataStructures.Enums.StateProcess;
import dataStructures.Process;
/**
 *
 * @author santi
 * Manejador de colas, su trabajo principal es hacer de interface entre la logica
 * de pasar un proceso de una cola a otra y el bajo nivel de trabajar con colas
 */
public class QueuesManager {
    /*
    JDOC
    */
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
    
    public void moveProcess(Process process, StateProcess from, StateProcess to){
        //TODO
    }

    public ProcessQueue getNewQueue() {
        return newQueue;
    }

    public ProcessQueue getReadyQueue() {
        return readyQueue;
    }

    public ProcessQueue getBlockedQueue() {
        return blockedQueue;
    }

    public ProcessQueue getSuspendedReadyQueue() {
        return suspendedReadyQueue;
    }

    public ProcessQueue getSuspendedBlockedQueue() {
        return suspendedBlockedQueue;
    }

    public ProcessQueue getExitQueue() {
        return exitQueue;
    }
    
    
}
