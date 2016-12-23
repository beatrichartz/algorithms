package beatrichartz.algorithms.stacks_and_queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayBag<T> implements Bag<T> {
    private static final int BOTTOM = 0;
    private int size = BOTTOM;

    private T[] elements;

    private class BagIterator implements Iterator<T> {
        private final T[] elements;
        private int cursor = BOTTOM;

        public BagIterator(T[] elements) {
            this.elements = elements;
        }

        public boolean hasNext() {
            return !cursorIsAtEnd();
        }

        public T next() {
            if (cursorIsAtEnd()) throw new NoSuchElementException();

            return elements[cursor++];
        }

        private boolean cursorIsAtEnd() {
            return elements.length == cursor || elements[cursor] == null;
        }
    }

    public ResizingArrayBag() {
        this(10);
    }

    public ResizingArrayBag(int initialCapacity) {
        // type unsafe, but elements are only used internally
        this.elements = (T[]) new Object[initialCapacity];
    }

    public void add(T element) {
        if (size == elements.length) {
            T[] newElements = (T[]) new Object[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
        elements[size++] = element;
    }

    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new BagIterator(elements);
    }
}
