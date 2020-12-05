package ua.edu.ucu.iterators;

import ua.edu.ucu.queuePackage.Queue;
import ua.edu.ucu.tries.Node;

public class TrieIterator {
    private Queue queue;
    private String next;



    public TrieIterator(Node node,String str) {
        queue = new Queue();
        queue.enqueue(new Object[]{node, str});
    }
}
