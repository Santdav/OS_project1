/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.goats.os_project1;
import GuiElements.MainGui;
import dataStructures.*;
/**
 *
 * @author santi
 */
public class OS_project1 {
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        MainGui mainGui = new MainGui();
        mainGui.setVisible(true);
        
        ProcessQueue pq = new ProcessQueue(StateProcess.READY);
        System.out.println(pq.size());
        
    }
}
