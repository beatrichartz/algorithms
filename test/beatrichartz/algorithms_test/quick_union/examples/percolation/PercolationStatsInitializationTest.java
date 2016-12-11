package beatrichartz.algorithms_test.quick_union.examples.percolation;

import beatrichartz.algorithms.quick_union.examples.percolation.PercolationStats;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class PercolationStatsInitializationTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    @Parameters({"0, 1", "1, 0", "-1, 1", "1, -1"})
    public void testDoesNotAcceptNegativeNumbersOrZeroForConstructorArguments(int sideLength, int numTrials) {
        exception.expect(IllegalArgumentException.class);
        new PercolationStats(sideLength, numTrials);
    }
}
