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
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    
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
    
    // ==================== ITERABLE IMPLEMENTATION ====================
    
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }
    
    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;
        
        @Override
        public boolean hasNext() {
            return current != null;
        }
        
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }
    
    // ==================== MÉTODOS DE INSERCIÓN ====================
    
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }
    
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
    
    public void add(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else {
            Node<T> newNode = new Node<>(data);
            Node<T> current = head;
            
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }
    
    public boolean insertAfter(T target, T data) {
        Node<T> current = head;
        
        while (current != null) {
            if (current.data.equals(target)) {
                Node<T> newNode = new Node<>(data);
                newNode.next = current.next;
                current.next = newNode;
                size++;
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    public boolean insertBefore(T target, T data) {
        if (head == null) {
            return false;
        }
        
        if (head.data.equals(target)) {
            addFirst(data);
            return true;
        }
        
        Node<T> current = head;
        
        while (current.next != null) {
            if (current.next.data.equals(target)) {
                Node<T> newNode = new Node<>(data);
                newNode.next = current.next;
                current.next = newNode;
                size++;
                return true;
            }
            current = current.next;
        }
        return false;
    }
    
    public void enqueue(T data) {
        addLast(data);
    }
    
    // ==================== MÉTODOS DE ELIMINACIÓN ====================
    
    public T removeFirst() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }
    
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
    
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        if (index == 0) {
            return removeFirst();
        }
        
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        
        T data = current.next.data;
        current.next = current.next.next;
        size--;
        return data;
    }
  
    public T dequeue() {
        return removeFirst();
    }
    
    public boolean removeItem(T item) {
    if (head == null) {
        return false;
    }
    
    // Special case: remove head
    if (head.data.equals(item)) {
        head = head.next;
        size--;
        return true;
    }
    
    // Search for the node to remove
    Node<T> current = head;
    while (current.next != null) {
        if (current.next.data.equals(item)) {
            current.next = current.next.next;
            size--;
            return true;
        }
        current = current.next;
    }
    
    return false;
}
    
    // ==================== MÉTODOS DE CONSULTA ====================
    
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
    
    public T set(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        T oldData = current.data;
        current.data = data;
        return oldData;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public T peekHead() {
    if (head == null) {
        throw new IllegalStateException("Queue is empty");
    }
    return head.data;
}
    
    public T peekLast() {
        if (head == null) {
            throw new IllegalStateException("Queue is empty");
        }

        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current.data;
    }
    // ==================== MÉTODOS DE UTILIDAD ====================
    
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] array = (T[]) new Object[size];
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }
    
    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    
    public void clear() {
        head = null;
        size = 0;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
