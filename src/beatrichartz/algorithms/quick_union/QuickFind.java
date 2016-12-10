package beatrichartz.algorithms.quick_union;

public class QuickFind {
    private int[] nodes;
    public QuickFind(int numNodes) {
        nodes = new int[numNodes];
        for (int i = 0; i < numNodes; i++) {
            nodes[i] = i;
        }
    }

    public boolean connected(int node1, int node2) {
        return nodes[node1] == nodes[node2];
    }

    public void union(int node1, int node2) {
        int node1Value = nodes[node1];
        int node2Value = nodes[node2];

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == node2Value) {
                nodes[i] = node1Value;
            }
        }
    }
}
