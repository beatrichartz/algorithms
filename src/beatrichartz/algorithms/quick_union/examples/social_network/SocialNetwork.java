package beatrichartz.algorithms.quick_union.examples.social_network;

import beatrichartz.algorithms.quick_union.WeightedQuickUnion;

public class SocialNetwork extends WeightedQuickUnion {
    public SocialNetwork(int numNodes) {
        super(numNodes);
    }

    public boolean fullyConnected() {
        return nodes.length == treeSizes[root(0)];
    }
}
