package beatrichartz.algorithms.stacks_and_queues;

import java.util.EmptyStackException;

public class LinkedListStack<T> implements Stack<T> {
    private class Node {
        private T element;
        private Node next;

        private Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node first;

    public T pop() {
        if (first == null) throw new EmptyStackException();

        T returnElement = first.element;
        this.first = first.next;
        return returnElement;
    }

    public void push(T element) {
        first = new Node(element, first);
    }
}
