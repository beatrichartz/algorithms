package beatrichartz.algorithms.quick_union.examples.social_network;

import java.util.Iterator;

public class SocialNetworkFactory {
    public static SocialNetwork createFromLog(Log log) {
        SocialNetwork socialNetwork = new SocialNetwork(log.maxNode() + 1);
        Iterator<LogEntry> logIterator = log.iterator();

        while (logIterator.hasNext() && !socialNetwork.fullyConnected()) {
            LogEntry logEntry = logIterator.next();
            socialNetwork.connectAtTime(logEntry.getNode1(), logEntry.getNode2(), logEntry.getTimestamp());
        }

        return socialNetwork;
    }
}
