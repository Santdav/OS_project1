/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures.Enums;
import dataStructures.Process;
import dataStructures.ProcessQueue;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Squidward
 */
public class ProcessThread implements Runnable{

    public Process PCB; // PCB asociado a un "hilo" de ejecucion.
    public int cycleDuration; // Duracion de ciclo
    private Semaphore usingCpu;
    private Semaphore readyQueueLock;
    private Semaphore blockedQueueLock;
    private ProcessQueue readyQueue;
    private ProcessQueue blockedQueue;
    private int cyclesSinceLastIO;

    public ProcessThread(Process PCB, int cycleDuration, Semaphore usingCpu, Semaphore readyQueueLock, Semaphore blockedQueueLock, ProcessQueue readyQueue, ProcessQueue blockedQueue, int cyclesSinceLastIO) {
        this.PCB = PCB;
        this.cycleDuration = cycleDuration;
        this.usingCpu = usingCpu;
        this.readyQueueLock = readyQueueLock;
        this.blockedQueueLock = blockedQueueLock;
        this.readyQueue = readyQueue;
        this.blockedQueue = blockedQueue;
        this.cyclesSinceLastIO = cyclesSinceLastIO;
    }
    @Override
    public void run() {
        try {
            readyQueueLock.acquire();
        } catch (InterruptedException ex) {
            System.getLogger(ProcessThread.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
       readyQueue.enqueue(this.PCB);
       readyQueueLock.release();
    }
    
}
