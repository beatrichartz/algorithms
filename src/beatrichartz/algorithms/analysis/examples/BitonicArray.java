package beatrichartz.algorithms.analysis.examples;

/*
Search in a bitonic array. An array is bitonic if it is comprised of an increasing sequence of integers followed
immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of n distinct integer
values, determines whether a given integer is in the array.

    Standard version: Use about 3lgn compares in the worst case.
    Signing bonus: Use about 2lgn compares in the worst case (and prove that no algorithm can guarantee to perform fewer
    than about 2lgn compares in the worst case).
 */
public class BitonicArray {
    private final int[] elements;

    public BitonicArray(int... elements) {
        this.elements = elements;
        if (elements.length == 0) return;

        int start = 0;
        int end = elements.length - 1;
        while (true) {
            int startElement = elements[start];
            int endElement = elements[end];

            if (start == elements.length - 1) {
                throw new IllegalArgumentException("Array does not descend after apex");
            } else if (end == 0) {
                throw new IllegalArgumentException("Array does not ascend at start");
            } else if (startElement < elements[start + 1]) {
                start++;
            } else if (endElement < elements[end - 1]) {
                end--;
            } else if (start != end) {
                throw new IllegalArgumentException("Array has more than one apex");
            } else {
                break;
            }
        }
    }

    public boolean contains(int integer) {
        if (elements.length == 0) return false;

        int startAscending = 0;
        int endAscending;
        int max = elements.length / 2;
        int startDescending;
        int endDescending = elements.length - 1;

        while (elements[max] < elements[max + 1] || elements[max] < elements[max - 1]) {
            if (elements[max] < elements[max + 1]) max++;
            if (elements[max] < elements[max - 1]) max--;
        }

        endAscending = startDescending = max;

        while (startAscending <= endAscending) {
            int middle = (startAscending + endAscending) / 2;
            if (elements[middle] == integer) return true;
            if (elements[middle] < integer) startAscending = middle + 1;
            if (elements[middle] > integer) endAscending = middle - 1;
        }

        while (startDescending <= endDescending) {
            int middle = (startDescending + endDescending) / 2;
            if (elements[middle] == integer) return true;
            if (elements[middle] < integer) endDescending = middle - 1;
            if (elements[middle] > integer) startDescending = middle + 1;
        }

        return false;
    }

    public boolean linearContains(int integer) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == integer) return true;
        }

        return false;
    }
}
