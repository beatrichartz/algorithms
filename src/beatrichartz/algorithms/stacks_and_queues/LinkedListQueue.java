package beatrichartz.algorithms.stacks_and_queues;

public class LinkedListQueue<T> implements Queue<T> {
    protected class Node {
        protected T element;
        protected Node next;

        public T getElement() {
            return element;
        }

        public Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    protected Node first;
    protected Node last;

    public void enqueue(T element) {
        Node newNode = new Node(element, null);
        if (isEmpty()) {
            last = first = newNode;
            return;
        }

        last.next = newNode;
        last = last.next;
    }

    public T dequeue() {
        if (isEmpty()) return null;

        T element = first.element;
        first = first.next;

        if (isEmpty()) last = null;

        return element;
    }

    public boolean isEmpty() {
        return first == null;
    }

}
