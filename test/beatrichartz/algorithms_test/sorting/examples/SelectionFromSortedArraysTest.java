package beatrichartz.algorithms_test.sorting.examples;

import beatrichartz.algorithms.sorting.examples.SelectionFromSortedArrays;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

// rank: 4
// [A, C|, D, G, I, K, X]
// [B, E|, F, H, J, L, M, N, O]

public class SelectionFromSortedArraysTest {
    @Test
    public void testSelectFromSorted() throws Exception {
        String[] arrayA = {"A", "C", "D", "G", "I", "K", "X"};
        String[] arrayB = {"B", "E", "F", "H", "J", "L", "M", "N", "O", "P", "Q", "R"};

        SelectionFromSortedArrays<String> selection = new SelectionFromSortedArrays<>();

        assertEquals("A", selection.select(arrayA, arrayB, 1));
        assertEquals("B", selection.select(arrayA, arrayB, 2));
        assertEquals("C", selection.select(arrayA, arrayB, 3));
        assertEquals("D", selection.select(arrayA, arrayB, 4));
        assertEquals("E", selection.select(arrayA, arrayB, 5));
        assertEquals("F", selection.select(arrayA, arrayB, 6));
//        assertEquals("K", selection.select(arrayA, arrayB, 11));
//        assertEquals("O", selection.select(arrayA, arrayB, 15));
    }
}
