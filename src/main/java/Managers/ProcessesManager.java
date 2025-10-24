/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;
import dataStructures.LinkedList;
import dataStructures.Process;
import dataStructures.Enums.StateProcess;
import Utils.IdGenerator;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author santi
 */
public class ProcessesManager {
    private LinkedList<Process> allProcesses;
    private QueuesManager queuesManager;
    //private IdGenerator idGenerator; usar directamente la clase (es estatica)

    public ProcessesManager() {
        this.allProcesses = new LinkedList<Process>();
        this.queuesManager = new QueuesManager();
    }
    
    public void checkEventCompletion(){
        LinkedList<Process> blockedProcesses = queuesManager.getBlockedQueue();
        LinkedList<Process> finalizedEventProcesses = new 
        LinkedList<Process>(); // Dos listas por la posibilidad de tener dos 
        // procesos que finalizan su evento en el mismo ciclo.
        for (Process blockedProcess : blockedProcesses){
            blockedProcess.decreaseWaitingTime();
            
            if (blockedProcess.getIoRemainingTime() <= 0){
                finalizedEventProcesses.enqueue(blockedProcess);
            }
        }
        for (Process unblockedProcess : finalizedEventProcesses) {
            unblockedProcess.setState(StateProcess.READY);
            queuesManager.moveToBlocked(unblockedProcess);
        }      
    }
    
    public void terminateProcess(Process process){
        process.setState(StateProcess.EXIT);
        queuesManager.moveToExit(process);
        queuesManager.setRunningProcess(null);
    }
    
    public void manageException(Process process) {
        process.setState(StateProcess.BLOCKED);
        process.setIoRemainingTime(process.getIoCompletionCycle());
        process.setIoRequestPending(true);
        queuesManager.moveToBlocked(process);
        queuesManager.setRunningProcess(null);
    }
        
        
    
    public void executeCurrentProcess(){
        Process runningProcess = queuesManager.getRunningProcess();
        if (runningProcess == null) return;
        runningProcess.increasePc();
        
        if (runningProcess.getProgramCounter() >= 
            runningProcess.getTotalInstructions()) {
            terminateProcess(runningProcess);
            return;
            
        }
        if (runningProcess.isIoBound() && (runningProcess.getProgramCounter() % runningProcess.getIoExceptionCycle()) == 0){
            manageException(runningProcess);
        }
    }
    
    public void addNewProcess(Process process){
        if (process.getState()!=StateProcess.NEW){
            System.out.println("Not new Process cant add here");
            return;
        }
    
        
        this.allProcesses.addLast(process);
        //this.queuesManager.addToNewQueue();
    }
}
