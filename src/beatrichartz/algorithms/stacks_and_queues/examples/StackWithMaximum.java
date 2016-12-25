package beatrichartz.algorithms.stacks_and_queues.examples;

import beatrichartz.algorithms.stacks_and_queues.LinkedListStack;

public class StackWithMaximum<T extends Comparable<T>> extends LinkedListStack<T> {
    private T max;

    public T pop() {
        T element = super.pop();
        if (isEmpty()) {
            max = null;
        } else if (element.compareTo(max) >= 0) {
            max = getNewMax();
        }

        return element;
    }

    private T getNewMax() {
        Node node = first;
        T maximum = node.getElement();
        while (node != null) {
            if (node.getElement().compareTo(maximum) >= 1) maximum = node.getElement();
            node = node.getNext();
        }

        return maximum;
    }

    public void push(T element) {
        if (max == null || element.compareTo(max) >= 1) max = element;

        super.push(element);
    }

    public T max() {
        return max;
    }
}
