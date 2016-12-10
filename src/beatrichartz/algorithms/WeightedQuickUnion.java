package beatrichartz.algorithms;

public class WeightedQuickUnion {
    private int[] nodes;
    private int[] treeSizes;

    public WeightedQuickUnion(int numNodes) {
        treeSizes = new int[numNodes];
        nodes = new int[numNodes];

        for (int i = 0; i < numNodes; i++) {
            nodes[i] = i;
        }
    }

    public boolean connected(int node1, int node2) {
        return root(nodes[node1]) == root(nodes[node2]);
    }

    public void union(int node1, int node2) {
        int root1 = root(nodes[node1]);
        int root2 = root(nodes[node2]);

        if (root1 == root2) return;

        if (treeSizes[root1] > treeSizes[root2]) {
            nodes[root1] = root2;
            treeSizes[root1] += treeSizes[root2];
        } else {
            nodes[root2] = root1;
            treeSizes[root2] += treeSizes[root1];
        }
    }

    private int root(int node) {
        while (node != nodes[node]) {
            nodes[node] = nodes[nodes[node]];
            node = nodes[node];
        }

        return node;
    }

}
