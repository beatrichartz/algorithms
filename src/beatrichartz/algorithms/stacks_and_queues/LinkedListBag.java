package beatrichartz.algorithms.stacks_and_queues;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListBag<T> implements Bag<T> {
    protected int size;
    protected Node first;
    protected Node last;

    protected class Node {
        private T element;
        private Node next;

        public Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    protected class BagIterator implements Iterator<T> {
        private Node node;

        public BagIterator(Node node) {
            this.node = node;
        }

        public boolean hasNext () {
            return node != null;
        }

        public T next() {
            if (node == null) throw new NoSuchElementException();

            T element = node.element;
            node = node.next;
            return element;
        }
    }

    public void add(T element) {
        size++;

        if (last == null) {
            first = last = new Node(element, null);
            return;
        }

        last.next = new Node(element, null);
        last = last.next;
    }

    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new BagIterator(first);
    }
}
