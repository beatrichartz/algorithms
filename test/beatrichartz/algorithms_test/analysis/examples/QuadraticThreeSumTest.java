package beatrichartz.algorithms_test.analysis.examples;

import beatrichartz.algorithms.analysis.examples.QuadraticThreeSum;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

public class QuadraticThreeSumTest {
    @Test
    public void setNotContainingElementsSummingToZeroReturnEmptyResult() throws Exception {
        Set<Set<Integer>> result = QuadraticThreeSum.solve(setOf());
        assertEquals(0, result.size());
    }

    @Test
    public void setNotContainingAtLeastThreeElementsReturnsEmptyResult() throws Exception {
        Set<Set<Integer>> result = QuadraticThreeSum.solve(setOf(-1,1));
        assertEquals(0, result.size());
    }

    @Test
    public void setContainingNoElementsSummingToZeroReturnEmptyResult() throws Exception {
        Set<Set<Integer>> result = QuadraticThreeSum.solve(setOf(1, 2, 3));
        assertEquals(0, result.size());
    }

    @Test
    public void setContainingOneCombinationSummingToZeroYieldThatCombination() throws Exception {
        Set<Set<Integer>> result = QuadraticThreeSum.solve(setOf(1, 2, -3));

        assertEquals(1, result.size());
        assertEquals(true, result.contains(setOf(1, 2, -3)));
    }

    @Test
    public void setContainingMultipleCombinationsSummingToZeroYieldAllCombinations() throws Exception {
        Set<Set<Integer>> result = QuadraticThreeSum.solve(setOf(0, 1, 2, -3, 3, -5, 7, 8));

        assertEquals(4, result.size());

        assertEquals(true, result.contains(setOf(1, 2, -3)));
        assertEquals(true, result.contains(setOf(0, 3, -3)));
        assertEquals(true, result.contains(setOf(2, 3, -5)));
        assertEquals(true, result.contains(setOf(8, -3, -5)));
    }

    private Set<Integer> setOf(int... integers) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < integers.length; i++) {
            set.add(integers[i]);
        }

        return set;
    }
}
