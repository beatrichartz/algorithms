package beatrichartz.algorithms.sorting;

public class HeapSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    public T[] sort(T[] elements) {
        int size = elements.length;
        for (int i = size / 2; i >= 0; i--) {
            sink(elements, i, size - 1);
        }

        int heapSize = size - 1;
        while (heapSize > 0) {
            swap(elements, 0, heapSize);
            sink(elements, 0, --heapSize);
        }

        return elements;
    }

    private void sink(T[] elements, int key, int heapSize) {
        while (2 * key <= heapSize) {
            int childKey = 2 * key;
            if (childKey < heapSize && isLessThan(elements, childKey, childKey+1)) childKey++;
            if (!isLessThan(elements, key, childKey)) break;
            swap(elements, key, childKey);
            key = childKey;
        }
    }
}
