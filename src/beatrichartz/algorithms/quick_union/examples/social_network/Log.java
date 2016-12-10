package beatrichartz.algorithms.quick_union.examples.social_network;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Log {
    private List<LogEntry> entries;

    public Log() {
        this.entries = new ArrayList<>();
    }

    public void add(LogEntry logEntry) {
        entries.add(logEntry);
    }

    public int maxNode() {
        Optional<LogEntry> maxLogEntry = entries.stream().max((logEntryA, logEntryB) ->
            Integer.compare(logEntryA.getMaxNode(), logEntryB.getMaxNode()));

        return maxLogEntry.get().getMaxNode();
    }

    public Iterator<LogEntry> iterator() {
        return entries.iterator();
    }
}
