/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GuiElements;

import javax.swing.table.AbstractTableModel;
import dataStructures.ProcessQueue;
import dataStructures.Process;

public class ProcessTableFormat extends AbstractTableModel {
    
    private ProcessQueue queue; 
    private final String[] columnNames = {"ID", "Nombre"};
    
    public ProcessTableFormat(ProcessQueue queue) {
        this.queue = queue;
    }
    
    public void setQueue(ProcessQueue queue) {
        this.queue = queue;
        fireTableDataChanged(); 
    }

    @Override
    public int getRowCount() {
        return queue.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Process process = queue.get(rowIndex);
        
        switch (columnIndex) {
            case 0: return process.getId();
            case 1: return process.getName();
            default: return null;
        }
    }
}