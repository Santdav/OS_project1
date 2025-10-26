/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;
import dataStructures.LinkedList;
import dataStructures.Process;
import dataStructures.Enums.StateProcess;
import Utils.IdGenerator;

/**
 *
 * @author santi
 */
public class ProcessesManager {
    private LinkedList<Process> allProcesses;
    public QueuesManager queuesManager;
    //private IdGenerator idGenerator; usar directamente la clase (es estatica)

    public ProcessesManager() {
        this.allProcesses = new LinkedList<Process>();
        this.queuesManager = new QueuesManager();
    }
    
    public synchronized void checkEventCompletion(){
        LinkedList<Process> blockedProcesses = queuesManager.getBlockedQueue();
        LinkedList<Process> finalizedEventProcesses = new 
        LinkedList<>(); // Dos listas por la posibilidad de tener dos 
        // procesos que finalizan su evento en el mismo ciclo.
        for (Process blockedProcess : blockedProcesses){
            blockedProcess.decreaseWaitingTime();
            
            if (blockedProcess.getIoRemainingTime() <= 0){
                finalizedEventProcesses.enqueue(blockedProcess);
            }
        }
        for (Process unblockedProcess : finalizedEventProcesses) {
            unblockedProcess.setState(StateProcess.READY);
            queuesManager.moveToReady(unblockedProcess);
        }      
    }
    
    public synchronized void terminateProcess(Process process){
        process.setState(StateProcess.EXIT);
        queuesManager.moveToExit(process);
        queuesManager.setRunningProcess(null);
    }
    
    public synchronized void manageException(Process process) {
        process.setState(StateProcess.BLOCKED);
        process.setIoRemainingTime(process.getIoCompletionCycle());
        process.setIoRequestPending(true);
        queuesManager.moveToBlocked(process);
        queuesManager.setRunningProcess(null);
    }
        
        
    
    public synchronized void executeCurrentProcess(){
        Process runningProcess = queuesManager.getRunningProcess();
        if (runningProcess == null) return;
        runningProcess.increasePCandMAR();
        
        if (runningProcess.getProgramCounter() >= 
            runningProcess.getTotalInstructions()) {
            terminateProcess(runningProcess);
            return;
            
        }
        if (runningProcess.isIoBound() && (runningProcess.getProgramCounter() % runningProcess.getIoExceptionCycle()) == 0){
            manageException(runningProcess);
        }
    }
    
    public synchronized void addNewProcess(Process process){
        if (process.getState()!=StateProcess.NEW){
            System.out.println("Not new Process cant add here");
            return;
        }
        this.allProcesses.addLast(process);
        this.queuesManager.addToNew(process);
        
    }
    
    public synchronized void admitNewProcess() {

        LinkedList<Process> newQueue = queuesManager.getNewQueue();
        while (!newQueue.isEmpty()) {
            Process processToAdmit = newQueue.dequeue();
            queuesManager.moveToReady(processToAdmit);
        }
    }

    public synchronized Process getRunningProcess() {
        return this.queuesManager.getRunningProcess();
    }

    public synchronized QueuesManager getQueuesManager() {
        return queuesManager;
    }
    
    
    
    
}
