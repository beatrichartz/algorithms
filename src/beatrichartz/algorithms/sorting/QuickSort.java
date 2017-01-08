package beatrichartz.algorithms.sorting;

public class QuickSort<T extends Comparable<T>> extends Sort<T> {
    public T[] sort(T[] elements) {
        sort(elements, 0, elements.length - 1);
        return elements;
    }

    private void sort(T[] elements, int low, int high) {
        if (high <= low) return;

        int partition = partition(elements, low, high);
        sort(elements, low, partition - 1);
        sort(elements, partition + 1, high);
    }

    private int partition(T[] elements, int low, int high) {
        int i = low;
        int j = high + 1;

        while (true) {
            while (isLessThan(elements, ++i, low))
                if (i == high) break;
            while (isLessThan(elements, low, --j))
                if (j == low) break;

            if (i >= j) break;

            swap(elements, i, j);
        }

        swap(elements, low, j);
        return j;
    }
}
