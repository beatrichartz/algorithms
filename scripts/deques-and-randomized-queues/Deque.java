

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node first;
    private Node last;

    private class Node {
        private Item element;
        private Node prev;
        private Node next;

        public Node(Item element, Node prev, Node next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private Node node;

        public DequeIterator(Node node) {
            this.node = node;
        }

        public boolean hasNext() {
            return node != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Item element = node.element;
            node = node.next;
            return element;
        }
    }

    public Iterator<Item> iterator() {
        return new DequeIterator(first);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(Item element) {
        if (element == null) throw new NullPointerException();

        Node pastFirst = first;
        this.first = new Node(element, null, first);
        if (pastFirst != null) pastFirst.prev = first;
        if (this.last == null) last = first;

        size++;
    }

    public void addLast(Item element) {
        if (element == null) throw new NullPointerException();

        Node pastLast = last;
        this.last = new Node(element, last, null);
        if (pastLast != null) pastLast.next = last;
        if (this.first == null) first = last;

        size++;
    }

    public int size() {
        return size;
    }

    public Item removeFirst() {
        if (first == null) throw new UnsupportedOperationException();

        Item element = first.element;
        first = first.next;
        if (first != null) first.prev = null;

        return element;
    }

    public Item removeLast() {
        if (last == null) throw new UnsupportedOperationException();

        Item element = last.element;
        last = last.prev;
        if (last != null) last.next = null;

        return element;
    }
}
