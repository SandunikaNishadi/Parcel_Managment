package Parcel_Model;

import java.util.*;



public class QueueOfCustomers {
    private LinkedList<Customer> queue;

    public QueueOfCustomers() {
        queue = new LinkedList<>();
    }

    public void enqueue(Customer customer) {
        queue.addLast(customer);
    }

    public Customer dequeue() {
        return queue.isEmpty() ? null : queue.removeFirst();
    }

    public List<Customer> getAllCustomers() {
        return queue;
    }

    public int size() {
        return queue.size();
    }
}
