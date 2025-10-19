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
public abstract class Scheduler {

    /**
     *
     * @param readyQueue
     * @return
     */
    public abstract Process selectNextProcess(ProcessQueue readyQueue);
}
