package beatrichartz.algorithms.stacks_and_queues;

import java.util.EmptyStackException;

public class ResizingArrayStack<T> implements Stack<T> {
    private static final int BOTTOM = -1;
    private T[] elements;
    private int cursor = BOTTOM;

    public ResizingArrayStack() {
        this(10);
    }

    public ResizingArrayStack(int initialCapacity) {
        // type unsafe, but elements are only used internally
        this.elements = (T[]) new Object[initialCapacity];
    }

    public boolean isEmpty() {
        return cursor == BOTTOM;
    }

    public T pop() {
        if (isEmpty()) throw new EmptyStackException();

        T element = elements[cursor];
        elements[cursor] = null;
        cursor--;

        if (cursor <= elements.length / 2) {
            resizeElements(elements.length / 2 + 1);
        }

        return element;
    }

    public void push(T elem) {
        if (elements.length == cursor + 1) {
            resizeElements(elements.length * 2);
        }

        elements[++cursor] = elem;
    }

    private void resizeElements(int newCapacity) {
        T[] newElements = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, Math.min(elements.length, newElements.length));
        this.elements = newElements;
    }
}
