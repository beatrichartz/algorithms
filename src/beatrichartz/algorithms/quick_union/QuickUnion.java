package beatrichartz.algorithms.quick_union;

public class QuickUnion {
    private int[] nodes;
    public QuickUnion(int numNodes) {
        nodes = new int[numNodes];
        for (int i = 0; i < numNodes; i++) {
            nodes[i] = i;
        }
    }

    public boolean connected(int node1, int node2) {
        return root(nodes[node1]) == root(nodes[node2]);
    }

    public void union(int node1, int node2) {
        nodes[root(nodes[node1])] = root(nodes[node2]);
    }

    private int root(int node) {
        while (node != nodes[node]) {
            node = nodes[node];
        }

        return node;
    }
}
