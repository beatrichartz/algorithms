package beatrichartz.algorithms.quick_union.examples.social_network;

import org.joda.time.LocalDateTime;

import java.util.Objects;

public class LogEntry {
    private LocalDateTime timestamp;
    private int node1;
    private int node2;

    public LogEntry(LocalDateTime timestamp, int node1, int node2) {
        this.timestamp = timestamp;
        this.node1 = node1;
        this.node2 = node2;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getMaxNode() { return node1 > node2 ? node1 : node2; }

    public int getNode1() {
        return node1;
    }

    public int getNode2() {
        return node2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogEntry logEntry = (LogEntry) o;
        return node1 == logEntry.node1 &&
                node2 == logEntry.node2 &&
                Objects.equals(timestamp, logEntry.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, node1, node2);
    }
}
