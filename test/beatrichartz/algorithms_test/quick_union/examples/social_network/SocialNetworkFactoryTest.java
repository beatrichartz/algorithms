package beatrichartz.algorithms_test.quick_union.examples.social_network;

import beatrichartz.algorithms.quick_union.examples.social_network.Log;
import beatrichartz.algorithms.quick_union.examples.social_network.LogEntry;
import beatrichartz.algorithms.quick_union.examples.social_network.SocialNetwork;
import beatrichartz.algorithms.quick_union.examples.social_network.SocialNetworkFactory;
import junit.framework.TestCase;
import org.joda.time.LocalDateTime;

public class SocialNetworkFactoryTest extends TestCase {
    private Log log;
    private LocalDateTime now;

    @Override
    public void setUp() throws Exception {
        now = LocalDateTime.now();
        log = new Log();
    }

    public void testReCreatesSocialNetworkFromLogs() throws Exception {
        log.add(new LogEntry(now.minusMinutes(10), 1, 2));
        log.add(new LogEntry(now.minusMinutes(5), 3, 4));
        log.add(new LogEntry(now, 3, 2));

        SocialNetwork socialNetwork = SocialNetworkFactory.createFromLog(log);
        assertEquals(now, socialNetwork.getLastConnectionMadeAt());
        assertEquals(true, socialNetwork.connected(1,4));
    }

    public void testReCreatesSocialNetworkFromLogsUntilFullyConnected() throws Exception {
        log.add(new LogEntry(now.minusMinutes(11), 1, 2));
        log.add(new LogEntry(now.minusMinutes(10), 3, 4));
        log.add(new LogEntry(now.minusMinutes(9), 1, 4));
        log.add(new LogEntry(now.minusMinutes(8), 5, 2));
        log.add(new LogEntry(now.minusMinutes(7), 7, 0));
        log.add(new LogEntry(now.minusMinutes(6), 4, 7));
        log.add(new LogEntry(now.minusMinutes(5), 6, 2));

        // superfluous connections
        log.add(new LogEntry(now.minusMinutes(4), 4, 6));
        log.add(new LogEntry(now.minusMinutes(3), 0, 1));
        log.add(new LogEntry(now.minusMinutes(2), 0, 2));
        log.add(new LogEntry(now.minusMinutes(1), 1, 5));

        SocialNetwork socialNetwork = SocialNetworkFactory.createFromLog(log);
        assertEquals(true, socialNetwork.fullyConnected());
        assertEquals(now.minusMinutes(5), socialNetwork.getFullyConnectedAt());
        assertEquals(now.minusMinutes(5), socialNetwork.getLastConnectionMadeAt());
    }
}
