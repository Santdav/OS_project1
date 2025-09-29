/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataStructures;

/**
 *
 * @author santi
 * @param <T>
 */
public class LinkedList<T> {
    
    // Node class representing each element in the list
    private static class Node<T> {
        T data;
        Node<T> next;
        
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node<T> head;
    private int size;
    
    // Constructor
    public LinkedList() {
        head = null;
        size = 0;
    }
    
    // Add element at the beginning
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }
    
    // Add element at the end
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    // Remove first element
    public T removeFirst() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }
    
    // Remove last element
    public T removeLast() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        
        if (head.next == null) {
            return removeFirst();
        }
        
        Node<T> current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        
        T data = current.next.data;
        current.next = null;
        size--;
        return data;
    }
    
    // Check if list contains element
    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    // Get element at specific index
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    // Get size of the list
    public int size() {
        return size;
    }
    
    // Check if list is empty
    public boolean isEmpty() {
        return head == null;
    }
    
    // Print the list
    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    
    // Clear the list
    public void clear() {
        head = null;
        size = 0;
    }
}
