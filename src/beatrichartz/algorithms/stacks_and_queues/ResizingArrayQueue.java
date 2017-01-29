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
            resizeElements(elements.length * 2);
        }

        elements[lastCursor++] = element;
    }

    public T dequeue() {
        T element = elements[firstCursor];
        elements[firstCursor] = null;
        if (lastCursor - firstCursor <= elements.length / 2) {
            resizeElements(elements.length / 2);
        }

        firstCursor++;
        if (isEmpty()) firstCursor = lastCursor = BOTTOM;

        return element;
    }

    private void resizeElements(int newCapacity) {
        T[] newElements = (T[]) new Object[newCapacity];
        System.arraycopy(elements, firstCursor, newElements, 0, lastCursor - firstCursor);
        lastCursor -= firstCursor;
        firstCursor = BOTTOM;
        elements = newElements;
    }

    public boolean isEmpty() {
        return firstCursor >= lastCursor;
    }
}
