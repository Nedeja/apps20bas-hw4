package ua.edu.ucu.queuePackage;

public class Queue {
    private ImmutableLinkedList lst;

    public Queue() {
        lst = new ImmutableLinkedList();
    }

    public boolean empty() {
        return lst.size() == 0;
    }

    public Object peek() {
        return this.lst.getFirst();
    }

    public Object dequeue() {
        Object element = this.lst.getFirst();
        this.lst = this.lst.remove(0);
        return element;
    }

    public void enqueue(Object element) {
        lst = lst.add(element);
    }

    public String toString() {
        return this.lst.toString();
    }
}
