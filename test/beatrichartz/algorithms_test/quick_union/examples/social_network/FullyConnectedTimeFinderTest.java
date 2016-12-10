package beatrichartz.algorithms_test.quick_union.examples.social_network;

import beatrichartz.algorithms.quick_union.examples.social_network.Log;
import beatrichartz.algorithms.quick_union.examples.social_network.LogEntry;
import beatrichartz.algorithms.quick_union.examples.social_network.FullyConnectedTimeFinder;
import junit.framework.TestCase;
import org.joda.time.LocalDateTime;

public class FullyConnectedTimeFinderTest extends TestCase {
    private Log log;
    private LocalDateTime now;

    @Override
    public void setUp() throws Exception {
        now = LocalDateTime.now();
        log = new Log();
    }

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
