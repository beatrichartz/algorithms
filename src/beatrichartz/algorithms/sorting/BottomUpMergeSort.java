package beatrichartz.algorithms.sorting;

import java.util.Arrays;

public class BottomUpMergeSort<T extends Comparable<T>> extends Sort<T> {
    public T[] sort(T[] elements) {
        T[] aux = Arrays.copyOf(elements, elements.length);

        for (int size = 1; size < elements.length; size *= 2) {
            for (int low = 0; low < elements.length - size; low += size * 2) {
                merge(elements, aux,
                        low, low + size - 1,
                        Math.min(low + size * 2 - 1, elements.length - 1));
            }
        }

        return elements;
    }

    private void merge(T[] elements, T[] aux, int low, int mid, int high) {
        int i = low, j = mid + 1;

        for (int k = 0; k <= high; k++) {
            aux[k] = elements[k];
        }

        for (int k = low; k <= high; k++) {
            if (i > mid)
                elements[k] = aux[j++];
            else if (j > high)
                elements[k] = aux[i++];
            else if (isLessThan(aux, j, i))
                elements[k] = aux[j++];
            else
                elements[k] = aux[i++];
        }
    }
}
