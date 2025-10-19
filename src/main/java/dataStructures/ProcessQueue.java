/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

/**
 *
 * @author santi
 */
public class ProcessQueue extends LinkedList<Process> {
    private StateProcess queueType;

    public ProcessQueue(StateProcess queueType) {
        this.queueType = queueType;
    }
    
}
