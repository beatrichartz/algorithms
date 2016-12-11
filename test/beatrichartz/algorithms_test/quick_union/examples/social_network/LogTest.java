package beatrichartz.algorithms_test.quick_union.examples.social_network;

import beatrichartz.algorithms.quick_union.examples.social_network.Log;
import beatrichartz.algorithms.quick_union.examples.social_network.LogEntry;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class LogTest {
    private LocalDateTime now;
    private Log log;

    @Before
    public void setUp() throws Exception {
        now = LocalDateTime.now();
        log = new Log();
    }

    @Test
    public void canGetTheMaxNode() throws Exception {
        log.add(new LogEntry(now.minusMinutes(10), 1, 2));
        log.add(new LogEntry(now.minusMinutes(5), 3, 4));
        log.add(new LogEntry(now, 3, 2));

        assertEquals(4, log.maxNode());
    }

    @Test
    public void canGetTheMaxNodeWhenConnectionIsNotIncremental() throws Exception {
        log.add(new LogEntry(now.minusMinutes(10), 1, 2));
        log.add(new LogEntry(now.minusMinutes(5), 3, 1));
        log.add(new LogEntry(now, 2, 4));

        assertEquals(4, log.maxNode());
    }

    @Test
    public void returnsIteratorForEntries() throws Exception {
        log.add(new LogEntry(now.minusMinutes(5), 3, 1));
        log.add(new LogEntry(now, 2, 4));

        Iterator<LogEntry> logIterator = log.iterator();

        assertEquals(true, logIterator.hasNext());
        LogEntry first = logIterator.next();
        assertFalse(first == null);
        assertEquals(3, first.getNode1());
        assertEquals(1, first.getNode2());

        assertEquals(true, logIterator.hasNext());
        LogEntry second = logIterator.next();
        assertFalse(second == null);
        assertEquals(2, second.getNode1());
        assertEquals(4, second.getNode2());

        assertEquals(false, logIterator.hasNext());
    }
}
