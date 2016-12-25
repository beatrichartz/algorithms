package beatrichartz.algorithms.stacks_and_queues;

public class ResizingArrayQueue<T> implements Queue<T> {
    private static final int BOTTOM = 0;
    private T[] elements;
    private int firstCursor = BOTTOM;
    private int lastCursor = BOTTOM;

    public ResizingArrayQueue() {
        this(10);
    }

    public ResizingArrayQueue(int initialCapacity) {
        // type unsafe, but elements are only used internally
        this.elements = (T[]) new Object[initialCapacity];
    }

    public void enqueue(T element) {
        if (lastCursor == elements.length) {
            T[] newElements = (T[]) new Object[elements.length * 2];
            System.arraycopy(elements, firstCursor, newElements, 0, lastCursor - firstCursor);
            lastCursor -= firstCursor;
            firstCursor = BOTTOM;
            elements = newElements;
        }

        elements[lastCursor++] = element;
    }

    public T dequeue() {
        T element = elements[firstCursor];
        elements[firstCursor++] = null;

        if (isEmpty()) firstCursor = lastCursor = BOTTOM;
        return element;
    }

    private boolean isEmpty() {
        return elements[firstCursor] == null;
    }
}
