package ua.edu.ucu.iterators;

import ua.edu.ucu.tries.Node;
import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Trie;

import java.util.Iterator;
import java.util.Queue;

public class TrieWeightIterator implements Iterator<String> {

    private Iterator<String> iterator;
    private int k;
    private String next;

    public TrieWeightIterator(Trie tree,String pref, int key) {
        iterator = tree.wordsWithPrefix(pref).iterator();
        if (iterator.hasNext()) {
            next = iterator.next();
        }
        k = key - 1;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    public String next() {
        String returnable = next;
        if (iterator.hasNext() && k < 0) {
            String newNext = iterator.next();
            if (newNext.length() != next.length()) {
                k -= 1;
            }
            if (k >= 0) {
                next = newNext;
            } else {
                next = null;
            }
        } else {
            next = null;
        }
        return returnable;
    }

    public static Iterable<String> TrieWeightIterable(Trie tree, String pref, int key) {
        return () -> new TrieWeightIterator(tree, pref, key);
    }
}
