package beatrichartz.algorithms_test.sorting.examples;

import beatrichartz.algorithms.sorting.examples.InversionCounter;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class InversionCounterTest {
    private InversionCounter<Integer> counter;

    @Before
    public void setUp() throws Exception {
        counter = new InversionCounter<>();
    }

    @Test
    public void testCountsZeroInversionForSortedArray() throws Exception {
        Integer[] input = new Integer[10];
        for (int i = 0; i < input.length; i++) {
            input[i] = i;
        }

        assertEquals(0, counter.count(input));
    }

    @Test
    public void testCountsTriangularLengthMinusOneForReversedArray() throws Exception {
        Integer[] input = new Integer[10];
        for (int i = 0; i < input.length; i++) {
            input[i] = 10 - i;
        }

        assertEquals(triangular(9), counter.count(input));
    }

    private int triangular(int i) {
        int triangular = 0;
        for (int j = 0; j <= i; j++) {
           triangular += j;
        }

        return triangular;
    }

    @Test
    public void testCountsInversionsForRandomArray() throws Exception {
        Integer[] input = {10,9,8,5,-1,11,10,0,4,7};

        assertEquals(28, counter.count(input));
    }
}
