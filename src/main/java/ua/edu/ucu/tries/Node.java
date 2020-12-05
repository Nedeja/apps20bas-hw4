package ua.edu.ucu.tries;

import java.util.HashMap;

public class Node {
    public final char letter;
    public HashMap<Character, Node> nextNodes;
    public Object weight;
    public boolean end;

    public Node(char letter) {
        this.letter = letter;
        this.nextNodes = new HashMap<Character, Node>();
        this.weight = null;
        this.end = false;
    }

    public Node addNextNode(char letter) {
        if (!this.nextNodes.containsKey(letter)) {
            this.nextNodes.put(letter, new Node(letter));
        }
        return this.nextNodes.get(letter);
    }
}
