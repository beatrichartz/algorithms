package beatrichartz.algorithms.sorting.examples;

public class InversionCounter<T extends Comparable<T>> {
    public int count(T[] input) {
        return count(input, 0, input.length - 1);
    }

    private int count(T[] input, int low, int high) {
        if (low == high) return 0;

        int value = 0;
        for (int i = low; i <= high; i++) {
            if (input[low].compareTo(input[i]) > 0) value++;
        }
        return value + count(input, low + 1, high);
    }
}
