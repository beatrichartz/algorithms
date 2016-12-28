

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] elements;

    public RandomizedQueue() {
        this(10);
    }

    private RandomizedQueue(int initialCapacity) {
        elements = (Item[]) new Object[initialCapacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(Item element) {
        if (element == null) throw new NullPointerException();
        if (elements.length == size) {
            resizeElements(elements.length * 2);
        }

        elements[size++] = element;
    }

    public int size() {
        return size;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int randomIndex = StdRandom.uniform(size);
        Item element = elements[randomIndex];
        elements[randomIndex] = elements[size - 1];
        if (elements.length / 2 == size) {
            resizeElements(elements.length / 2);
        }
        size--;

        return element;
    }

    private void resizeElements(int newCapacity) {
        Item[] newElements = (Item[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        return elements[StdRandom.uniform(size)];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator(elements, size);
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] elements;
        private int size;

        public RandomizedQueueIterator(Item[] elements, int size) {
            this.elements = (Item[]) new Object[size];
            System.arraycopy(elements, 0, this.elements, 0, size);

            this.size = size;
        }

        public boolean hasNext() {
            return size > 0;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            int randomIndex = StdRandom.uniform(size);
            Item element = elements[randomIndex];
            elements[randomIndex] = elements[size - 1];
            size--;

            return element;
        }
    }
}
