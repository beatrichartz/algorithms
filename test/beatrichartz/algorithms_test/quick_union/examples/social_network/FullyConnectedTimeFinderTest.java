package beatrichartz.algorithms_test.quick_union.examples.social_network;

import beatrichartz.algorithms.quick_union.examples.social_network.FullyConnectedTimeFinder;
import beatrichartz.algorithms.quick_union.examples.social_network.Log;
import beatrichartz.algorithms.quick_union.examples.social_network.LogEntry;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class FullyConnectedTimeFinderTest {
    private Log log;
    private LocalDateTime now;

    @Before
    public void setUp() throws Exception {
        now = LocalDateTime.now();
        log = new Log();
    }

    @Test
    public void testFindsTimeWhenSocialNetworkFullyConnected() throws Exception {
        log.add(new LogEntry(now.minusMinutes(8), 1, 2));
        log.add(new LogEntry(now.minusMinutes(7), 3, 4));
        log.add(new LogEntry(now.minusMinutes(6), 1, 4));
        log.add(new LogEntry(now.minusMinutes(5), 5, 2));
        log.add(new LogEntry(now.minusMinutes(4), 7, 0));
        log.add(new LogEntry(now.minusMinutes(3), 4, 7));
        log.add(new LogEntry(now.minusMinutes(2), 2, 6));

        // superfluous connection
        log.add(new LogEntry(now.minusMinutes(1), 4, 6));

        LocalDateTime time = FullyConnectedTimeFinder.findFromLog(log);
        assertEquals(now.minusMinutes(2), time);
    }
}
