package beatrichartz.algorithms.quick_union.examples.largest_node;

import beatrichartz.algorithms.quick_union.WeightedQuickUnion;

/*
 Add a method find() to the union-find data type so that find(i) returns the largest element in the connected component
 containing i. The operations, union(), connected(), and find() should all take logarithmic time or better.
 */
public class ExtendedQuickUnion extends WeightedQuickUnion {
    private int[] maxNode;
    public ExtendedQuickUnion(int numNodes) {
        super(numNodes);
        maxNode = new int[numNodes];

        for (int i = 0; i < numNodes; i++) {
            maxNode[i] = i;
        }
    }

    @Override
    public void union(int node1, int node2) {
        int root1 = root(nodes[node1]);
        int root2 = root(nodes[node2]);

        if (root1 == root2) return;

        if (treeSizes[root1] > treeSizes[root2]) {
            nodes[root1] = root2;
            treeSizes[root2] += treeSizes[root1];
        } else {
            nodes[root2] = root1;
            treeSizes[root1] += treeSizes[root2];
        }

        if (maxNode[root1] > maxNode[root2]) {
            maxNode[root2] = maxNode[root1];
        } else {
            maxNode[root1] = maxNode[root2];
        }
    }

    public int findLargestNodeInComponent(int node) {
        return maxNode[root(node)];
    }
}
