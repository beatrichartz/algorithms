package beatrichartz.algorithms.stacks_and_queues;

public class LinkedListQueue<T> implements Queue<T> {
    private class Node {
        private T element;
        private Node next;

        public Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node first;
    private Node last;

    public void enqueue(T element) {
        Node newNode = new Node(element, null);
        if (isEmpty()) {
            this.last = this.first = newNode;
            return;
        }

        this.last.next = newNode;
        this.last = this.last.next;
    }

    public T dequeue() {
        if (isEmpty()) return null;

        T element = first.element;
        this.first = first.next;

        if (isEmpty()) last = null;

        return element;
    }

    private boolean isEmpty() {
        return this.first == null;
    }

}
