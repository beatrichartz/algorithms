package beatrichartz.algorithms.quick_union.examples.social_network;

import beatrichartz.algorithms.quick_union.WeightedQuickUnion;

/**
 Given a social network containing n members and a log file containing m timestamps at which times pairs of members
 formed friendships, design an algorithm to determine the earliest time at which all members are connected
 i.e., every member is a friend of a friend of a friend ... of a friend). Assume that the log file is sorted by
 timestamp and that friendship is an equivalence relation. The running time of your algorithm should be m log⁡ n
 or better and use extra space proportional to n.
 */
public class SocialNetwork extends WeightedQuickUnion {
    public SocialNetwork(int numNodes) {
        super(numNodes);
    }

    public boolean fullyConnected() {
        return getNodes().length == getTreeSizes()[root(0)];
    }
}
