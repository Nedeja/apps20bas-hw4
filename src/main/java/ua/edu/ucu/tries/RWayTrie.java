package ua.edu.ucu.tries;

import ua.edu.ucu.iterators.TrieIterator;
import ua.edu.ucu.iterators.TrieWeightIterator;

public class RWayTrie implements Trie {
    public static int AllLetters = 26;
    public final Node head;
    private int size = 0;

    public RWayTrie() {
        head = new Node('H');
    }

    @Override
    public void add(Tuple t) {
        String word = t.term;
        Object weight = t.weight;
        Node iterable = this.head;
        int i;
        for (i = 0; i < word.length(); i++) {
            iterable = iterable.addNextNode(word.charAt(i));
        }
        iterable.end = true;
        iterable.weight = weight;
        size += 1;
    }

    @Override
    public boolean contains(String word) {
        Node node = this.head;
        int i;
        for (i = 0; i < word.length(); i++) {
            if (node.nextNodes.containsKey(word.charAt(i))) {
                node = node.nextNodes.get(word.charAt(i));
            } else {
                return false;
            }
        }
        return node.end;
    }

    @Override
    public boolean delete(String word) {
        if (this.contains(word)){
            Node node = this.head;
            Node doNotDelete = this.head;
            int i;
            int ind = 0;
            for (i = 0; i < word.length(); i++) {
                if (node.nextNodes.containsKey(word.charAt(i))) {
                    node = node.nextNodes.get(word.charAt(i));
                } else {
                    System.out.println("No such word to delete");
                    return false;
                }
                if (node.end && i != word.length()) {
                    doNotDelete = node;
                    ind = i;
                }
            }
            node.end = false;
            node.weight = null;
            size -= 1;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Node node = this.head;
        int i;
        for (i = 0; i < s.length(); i++) {
            if (node.nextNodes.containsKey(s.charAt(i))) {
                node = node.nextNodes.get(s.charAt(i));
            } else {
                return null;
            }
        }
        return TrieIterator.TrieIterable(node, s);
    }

    @Override
    public int size() {
        return size;
    }

}
