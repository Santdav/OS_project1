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
    private Process runningProcess;
    
    public QueuesManager() {
        this.newQueue = new ProcessQueue(StateProcess.NEW);
        this.readyQueue = new ProcessQueue(StateProcess.READY);
        this.blockedQueue = new ProcessQueue(StateProcess.BLOCKED);
        this.suspendedReadyQueue = new ProcessQueue(StateProcess.SUSPENDED_READY);
        this.suspendedBlockedQueue = new ProcessQueue(StateProcess.SUSPENDED_BLOCKED);
        this.exitQueue = new ProcessQueue(StateProcess.EXIT);
        this.runningProcess = null;
    }
    
    public ProcessQueue getQueueForState(StateProcess state){
        switch(state){
            case NEW:
                return this.newQueue;
            case READY:
                return this.readyQueue;
            case BLOCKED:
                return this.blockedQueue;
            case SUSPENDED_READY:
                return this.suspendedReadyQueue;
            case SUSPENDED_BLOCKED:
                return this.suspendedBlockedQueue;
            case EXIT: 
                return this.exitQueue;
            default:
                return this.newQueue;
        }
        
    }
    
    //======= Move Methods ===========
    
    public void addToNew(Process process){
        this.newQueue.enqueue(process);
    }
    
    public void moveToReady(Process process){
        StateProcess currentState = process.getState();
        
        switch (currentState){
            case NEW, RUNNING, BLOCKED, SUSPENDED_READY:
                getQueueForState(currentState).removeItem(process);
                process.setState(StateProcess.READY);
                this.readyQueue.enqueue(process);
                                return;
            case READY:
                return;
            case EXIT,SUSPENDED_BLOCKED:
                System.out.println(process.getState() + " cant move to READY");
                System.out.println(process.toString());
                return;
        }
    }
    
    public void moveToBlocked(Process process){
        StateProcess currentState = process.getState();
        
        switch(currentState){
            case BLOCKED:
                return;
            case RUNNING,SUSPENDED_BLOCKED:
                getQueueForState(currentState).removeItem(process);
                process.setState(StateProcess.BLOCKED);
                this.blockedQueue.enqueue(process);
                return;
            case READY,NEW,EXIT,SUSPENDED_READY:
                System.out.println(process.getState() + " cant move to BLOCKED");
                System.out.println(process.toString());
                return;
        }
    }
    
    public void moveToSuspendedReady(Process process){
        StateProcess currentState = process.getState();
        
        switch (currentState) {
            case SUSPENDED_READY:
                return;
            case SUSPENDED_BLOCKED,READY,RUNNING,NEW:
                getQueueForState(currentState).removeItem(process);
                process.setState(StateProcess.SUSPENDED_READY);
                this.suspendedReadyQueue.enqueue(process);
                return;
            case BLOCKED,EXIT:
                System.out.println(process.getState() + " cant move to SUSPENDED READY");
                System.out.println(process.toString());
                return;
        }
    }
    
    public void moveToSuspendedBlocked(Process process){
        StateProcess currentState = process.getState();
        
        switch (currentState){
            case SUSPENDED_BLOCKED:
                return;
            case BLOCKED:
                getQueueForState(currentState).removeItem(process);
                process.setState(StateProcess.SUSPENDED_BLOCKED);
                this.suspendedBlockedQueue.enqueue(process);
                return;
            case NEW,READY,RUNNING,SUSPENDED_READY,EXIT:
                System.out.println(process.getState() + " cant move to SUSPENDED BLOCKED");
                System.out.println(process.toString());
                return;
        }
    }
    
    public void moveToExit(Process process){
        StateProcess currProcess = process.getState();
        
        switch (currProcess){
            case EXIT:
                return;
            case NEW,BLOCKED,READY,SUSPENDED_BLOCKED,SUSPENDED_READY:
                System.out.println(process.getState() + " cant move to EXIT");
                System.out.println(process.toString());
                return;
            case RUNNING:
                getQueueForState(currProcess).removeItem(process);
                process.setState(StateProcess.EXIT);
                this.exitQueue.enqueue(process);
                return;
        }
    }
    
    public void moveToRunning(Process process){
        StateProcess currentState = process.getState();
        
        switch (currentState){
            case RUNNING:
                return;
            case READY:
                getQueueForState(currentState).removeItem(process);
                Process oldRunning = this.runningProcess;
                process.setState(StateProcess.RUNNING);
                this.runningProcess = process;
                if (oldRunning != null) {
                    oldRunning.setState(StateProcess.READY);
                    this.readyQueue.enqueue(oldRunning);
                }
                return;
            default:
                System.out.println(process.getState() + " cant move to RUNNING");
                System.out.println(process.toString());
                return;
        }
    }
    
    //========== Getters y Setters =========
    
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

    public Process getRunningProcess() {
        return runningProcess;
    }

    public void setRunningProcess(Process runningProcess) {
        this.runningProcess = runningProcess;
    }
    
}
