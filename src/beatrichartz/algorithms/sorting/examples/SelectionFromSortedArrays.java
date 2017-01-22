package beatrichartz.algorithms.sorting.examples;

public class SelectionFromSortedArrays<T extends Comparable<T>> {
    public T select(T[] arrayA, T[] arrayB, int rank) {
        int highA = Math.min(rank - 1, arrayA.length - 1);
        int highB = Math.min(rank - 1, arrayB.length - 1);
        return select(arrayA, highA, arrayB, highB, rank - 1);
    }

    public T select(T[] arrayA, int highA, T[] arrayB, int highB, int rank) {
        if (highA + highB == rank) {
            if (isLessThan(arrayA[highA], arrayB[highB])) {
                return arrayA[highA];
            } else {
                return arrayB[highB];
            }
        }

        int midA = highA / 2;
        int midB = highB / 2;

        if (isLessThan(arrayA[midA], arrayB[highB])) {
            return select(arrayA, highA, arrayB, midB, rank);
        } else if (isLessThan(arrayB[midB], arrayA[highA])) {
            return select(arrayA, midA, arrayB, highA, rank);
        } else {
            return select(arrayA, midA, arrayB, midB, rank);
        }
    }


    protected boolean isLessThan(T a, T b) {
        return a.compareTo(b) < 0;
    }
}

