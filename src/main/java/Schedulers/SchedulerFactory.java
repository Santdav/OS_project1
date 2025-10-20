/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Schedulers;

/**
 *
 * @author santi
 */
public class SchedulerFactory {
    
    /**
     * Crea un scheduler basado en el nombre del algoritmo
     * @param algorithmName Nombre del algoritmo (FCFS, RR, SPN, SRT, HRRN, FEEDBACK)
     * @return Instancia del scheduler
     */
    public static Scheduler createScheduler(String algorithmName) {
        return createScheduler(algorithmName, 0); // Default sin parámetros extra
    }
    
    /**
     * Crea un scheduler con parámetro adicional (para Round Robin)
     */
    public static Scheduler createScheduler(String algorithmName, int param) {
        switch (algorithmName.toUpperCase()) {
            case "FCFS":
                return new FCFS_Scheduler();
                
            case "ROUNDROBIN":
            case "RR":
                return new RoundRobinScheduler(param > 0 ? param : 4); // Default quantum = 4
                
            case "SPN":
                return new SPN_Scheduler();
                
            case "SRT":
                return new SRT_Scheduler();
                
            case "HRRN":
                return new HRRN_Scheduler();
                
            case "FEEDBACK":
                // Feedback con valores fijos (3 colas, quantum base 2)
                return new FeedbackScheduler(3, 2);
                
            default:
                // Si no reconoces el algoritmo, usa FCFS por defecto
                System.out.println("Algoritmo desconocido: " + algorithmName + ". Usando FCFS.");
                return new FCFS_Scheduler();
        }
    }
    
    /**
     * Crea un scheduler con dos parámetros (para Feedback)
     */
    public static Scheduler createScheduler(String algorithmName, int param1, int param2) {
        if ("FEEDBACK".equals(algorithmName.toUpperCase())) {
            return new FeedbackScheduler(param1, param2);
        }
        return createScheduler(algorithmName, param1); // Para otros algoritmos, usa un solo parámetro
    }
    
    /**
     * Lista de algoritmos disponibles para la UI - usando array nativo
     */
    public static String[] getAvailableAlgorithms() {
        String[] algorithms = new String[6];
        algorithms[0] = "FCFS";
        algorithms[1] = "RoundRobin"; 
        algorithms[2] = "SPN";
        algorithms[3] = "SRT";
        algorithms[4] = "HRRN";
        algorithms[5] = "Feedback";
        return algorithms;
    }
}
