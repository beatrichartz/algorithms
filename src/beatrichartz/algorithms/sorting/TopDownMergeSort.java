package beatrichartz.algorithms.sorting;

import java.util.Arrays;

public class TopDownMergeSort<T extends Comparable<T>> extends Sort<T> {
    public T[] sort(T[] elements) {
        T[] aux = Arrays.copyOf(elements, elements.length);

        sort(aux, elements, 0, elements.length - 1);
        return elements;
    }

    private void sort(T[] elements, T[] aux, int low, int high) {
        if (high <= low) return;

        int mid = low + (high - low) / 2;
        sort(aux, elements, low, mid);
        sort(aux, elements, mid + 1, high);

        merge(elements, aux, low, mid, high);
    }

    private void merge(T[] elements, T[] aux, int low, int mid, int high) {
        int i = low, j = mid + 1;

        for (int k = low; k <= high; k++) {
            if (i > mid)
                aux[k] = elements[j++];
            else if (j > high)
                aux[k] = elements[i++];
            else if (isLessThan(elements, j, i))
                aux[k] = elements[j++];
            else
                aux[k] = elements[i++];
        }
    }

}
