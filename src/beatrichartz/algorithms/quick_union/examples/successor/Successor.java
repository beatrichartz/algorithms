package beatrichartz.algorithms.quick_union.examples.successor;

/*
Successor with delete. Given a set of N integers S={0,1,...,N−1} and a sequence of requests of the following form:

    Remove x from S
    Find the successor of x: the smallest y in S such that y≥x.

design a data type so that all operations (except construction) should take logarithmic time or better.
 */
public class Successor {
    private int[] nodes;

    public Successor(int numNodes) {
        nodes = new int[numNodes];
        for (int i = 0; i < numNodes; i++) {
            nodes[i] = i;
        }
    }

    public void delete(int node) {
        int oldRoot = find(nodes[node]);
        int newRoot = find(nodes[node + 1]);

        nodes[oldRoot] = newRoot;
    }

    public int find(int node) {
        while (node != nodes[node]) {
            nodes[node] = nodes[nodes[node]];
            node = nodes[node];
        }

        return node;
    }
}
