package beatrichartz.algorithms.quick_union.examples.social_network;

import org.joda.time.LocalDateTime;

import java.util.Iterator;

public class FullyConnectedTimeFinder {
    public static LocalDateTime findFromLog(Log log) {
        SocialNetwork socialNetwork = new SocialNetwork(log.maxNode() + 1);
        Iterator<LogEntry> logIterator = log.iterator();

        LogEntry logEntry = null;
        while (logIterator.hasNext() && !socialNetwork.fullyConnected()) {
            logEntry = logIterator.next();
            socialNetwork.union(logEntry.getNode1(), logEntry.getNode2());
        }

        return logEntry.getTimestamp();
    }
}
