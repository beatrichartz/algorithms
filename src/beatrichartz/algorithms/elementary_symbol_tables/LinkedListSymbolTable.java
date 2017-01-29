package beatrichartz.algorithms.elementary_symbol_tables;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListSymbolTable<Key, Value> implements SymbolTable<Key, Value> {
    private int size = 0;
    private Node first;

    private class Node {
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public void put(Key key, Value value) {
        Node node = getNode(key);
        if (node != null) {
            node.value = value;
            return;
        }

        first = new Node(key, value, first);
        size++;
    }

    public Value get(Key key) {
        Node node = getNode(key);
        if (node != null) return node.value;

        return null;
    }

    private Node getNode(Key key) {
        Node node = first;
        while (node != null) {
            if (node.key.equals(key)) return node;
            node = node.next;
        }
        return null;
    }

    public void delete(Key key) {
        Node prev = null;
        Node node = first;
        while (node != null) {
            if (node.key.equals(key)) {
                if (prev == null) {
                    first = node.next;
                } else {
                    prev.next = node.next;
                }

                size--;
                return;
            }
            prev = node;
            node = node.next;
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private class KeyIterator implements Iterator<Key> {
        private Node node;

        public KeyIterator(Node first) {
            this.node = first;
        }

        public boolean hasNext() {
            return node != null;
        }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();

            Key key = node.key;
            node = node.next;
            return key;
        }
    }

    private class KeyIterable implements Iterable<Key> {
        private Node first;
        public KeyIterable(Node first) {
            this.first = first;
        }

        public Iterator<Key> iterator() {
            return new KeyIterator(first);
        }
    }

    public Iterable<Key> keys() {
        return new KeyIterable(first);
    }
}
