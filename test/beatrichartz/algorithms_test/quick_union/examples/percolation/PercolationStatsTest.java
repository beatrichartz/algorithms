package beatrichartz.algorithms_test.quick_union.examples.percolation;

import beatrichartz.algorithms.quick_union.examples.percolation.Percolation;
import beatrichartz.algorithms.quick_union.examples.percolation.PercolationStats;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;

// Use PowerMock since the course suggests an API without DI
@RunWith(PowerMockRunner.class)
@PrepareForTest(PercolationStats.class)
public class PercolationStatsTest {
    @Mock
    public Percolation mockPercolation;

    @Before
    public void setUp() throws Exception {
        whenNew(Percolation.class).withAnyArguments().thenReturn(mockPercolation);
        when(mockPercolation.percolates()).thenAnswer(new Answer() {
            private int count = 0;
            private boolean cycle = false;

            public Object answer(InvocationOnMock invocation) {
                count += 1;
                if (count == 10) {
                    count = cycle ? 0 : 2;
                    cycle = !cycle;
                    return true;
                }
                return false;
            }
        });

    }

    @Test
    public void testRunsNTrialsAndCalculatesStats() throws Exception {
        PercolationStats stats = new PercolationStats(4, 10);
        verifyNew(Percolation.class, times(10)).withArguments(4);

        assertEquals(0.5625, stats.mean());
        assertEquals(true, 0.065881 - stats.stddev() < 0.000001);
        assertEquals(true, 0.521666 - stats.confidenceLo() < 0.000001);
        assertEquals(true, 0.603333 - stats.confidenceHi() < 0.000001);
    }

    @Test
    public void testMainPrintsResults() throws Exception {
        PrintStream oldSystemOut = System.out;
        PrintStream oldSystemErr = System.err;

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        PercolationStats.main(new String[]{"4", "10"});

        System.setOut(oldSystemOut);
        System.setErr(oldSystemErr);

        assertEquals(true, outContent.toString().contains("mean\t\t\t= 0.5625"));
        assertEquals(true, outContent.toString().contains("stddev\t\t\t= 0.065"));
        assertEquals(true, outContent.toString().contains("95% confidence interval\t= 0.521"));
        assertEquals(true, outContent.toString().contains(", 0.603"));
    }
}
