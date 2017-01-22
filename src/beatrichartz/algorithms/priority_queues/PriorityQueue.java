package beatrichartz.algorithms.priority_queues;

import java.util.NoSuchElementException;

public class PriorityQueue<T extends Comparable<T>> {
    private int size;
    private T[] elements;
    private boolean empty;

    public PriorityQueue(int initialCapacity) {
        elements = (T[])  new Comparable[initialCapacity];
    }

    public PriorityQueue() {
        this(10);
    }

    public int size() {
        return size;
    }

    public void insert(T element) {
        if (full()) resize(elements.length * 2 + 1);

        elements[++size] = element;
        swim(size);
    }

    private void resize(int newSize) {
        T[] bigger = (T[]) new Comparable[newSize];
        System.arraycopy(elements, 0, bigger, 0, Math.min(newSize, elements.length));
        elements = bigger;
    }

    private boolean full() {
        return elements.length - 1 <= size;
    }

    private void swim(int key) {
        while (key > 1 && less(key/2, key)) {
            swap(key, key/2);
            key /= 2;
        }
    }

    private void swap(int key1, int key2) {
        T element = elements[key1];
        elements[key1] = elements[key2];
        elements[key2] = element;
    }

    private boolean less(int key1, int key2) {
        return elements[key1].compareTo(elements[key2]) < 0;
    }

    private boolean greaterOrEqual(int key1, int key2) {
        return elements[key1].compareTo(elements[key2]) >= 0;
    }

    public T delMax() {
        int key = 1;
        T maxElement = elements[key];
        if (maxElement == null) throw new NoSuchElementException();

        swap(key, size--);
        sink(key);
        elements[size + 1] = null;

        if (halfEmpty()) resize(elements.length / 2 + 1);

        return maxElement;
    }

    private boolean halfEmpty() {
        return size <= elements.length / 2;
    }

    private void sink(int key) {
        while (2 * key <= size) {
            int childKey = 2 * key;
            if (childKey < size && less(childKey, childKey+1)) childKey++;
            if (greaterOrEqual(key, childKey)) break;
            swap(key, childKey);
            key = childKey;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
