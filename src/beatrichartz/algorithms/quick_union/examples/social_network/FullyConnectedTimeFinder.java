package beatrichartz.algorithms.quick_union.examples.social_network;

import org.joda.time.LocalDateTime;

import java.util.Iterator;

/**
 Given a social network containing n members and a log file containing m timestamps at which times pairs of members
 formed friendships, design an algorithm to determine the earliest time at which all members are connected
 i.e., every member is a friend of a friend of a friend ... of a friend). Assume that the log file is sorted by
 timestamp and that friendship is an equivalence relation. The running time of your algorithm should be m log⁡ n
 or better and use extra space proportional to n.
 */
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
