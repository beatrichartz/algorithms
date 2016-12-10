package beatrichartz.algorithms.quick_union.examples.social_network;

import org.joda.time.LocalDateTime;

/**
 Given a social network containing n members and a log file containing m timestamps at which times pairs of members
 formed friendships, design an algorithm to determine the earliest time at which all members are connected
 i.e., every member is a friend of a friend of a friend ... of a friend). Assume that the log file is sorted by
 timestamp and that friendship is an equivalence relation. The running time of your algorithm should be m log⁡ n
 or better and use extra space proportional to n.
 */
public class SocialNetwork {
    private int[] networkSizes;
    private int[] networks;
    private LocalDateTime lastConnectionMadeAt;
    private LocalDateTime fullyConnectedAt;

    public LocalDateTime getLastConnectionMadeAt() {
        return lastConnectionMadeAt;
    }

    public SocialNetwork(int numPeople) {
        networks = new int[numPeople];
        networkSizes = new int[numPeople];
        for (int i = 0; i < numPeople; i++) {
            networkSizes[i] = 1;
            networks[i] = i;
        }
    }

    public void connectAtTime(int person1, int person2, LocalDateTime time) {
        lastConnectionMadeAt = time;
        if (connected(person1, person2)) return;

        int root1 = root(person1);
        int root2 = root(person2);

        if (networkSizes[root1] > networkSizes[root2]) {
            networks[root1] = root2;
            networkSizes[root2] += networkSizes[root1];
        } else {
            networks[root2] = root1;
            networkSizes[root1] += networkSizes[root2];
        }

        if (fullyConnected() && fullyConnectedAt == null) {
            fullyConnectedAt = time;
        }
    }

    public boolean connected(int person1, int person2) {
        return root(person1) == root(person2);
    }

    private int root(int person) {
        while (networks[person] != person) {
            networks[person] = networks[networks[person]];
            person = networks[person];
        }

        return person;
    }

    public boolean fullyConnected() {
        return networks.length == networkSizes[root(0)];
    }

    public LocalDateTime getFullyConnectedAt() {
        return fullyConnectedAt;
    }
}
