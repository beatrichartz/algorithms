package beatrichartz.algorithms.elementary_symbol_tables;

import beatrichartz.algorithms.stacks_and_queues.Queue;
import beatrichartz.algorithms.stacks_and_queues.ResizingArrayQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTreeSymbolTable<Key extends Comparable<Key>, Value> implements OrderedSymbolTable<Key, Value> {
    private class Node {
        private final Key key;
        private Value value;
        private Node left;
        private Node right;
        private int size;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    private Node root;

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
        }
        int comparison = key.compareTo(node.key);

        if (comparison > 0) {
            node.right = put(node.right, key, value);
        } else if (comparison < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.value = value;
        }

        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }


    public Value get(Key key) {
        Node node = root;
        while (node != null) {
            int comparison = node.key.compareTo(key);
            if (comparison < 0) {
                node = node.right;
            } else if (comparison > 0) {
                node = node.left;
            } else {
                return node.value;
            }
        }

        return null;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int comparison = key.compareTo(node.key);

        if (comparison < 0) node.left = delete(node.left, key);
        else if (comparison > 0) node.right = delete(node.right, key);
        else if (node.right == null)  return node.left;
        else if (node.left == null)  return node.right;
        else {
            Node successor = node;
            node = min(successor.right);
            node.right = deleteMin(successor.right);
            node.left = successor.left;
        }

        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public Key min() {
        if (isEmpty()) return null;
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    public Key max() {
        if (isEmpty()) return null;
        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null) return node;
        return min(node.right);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Key floor(Key key) {
        Node node = floor(root, key);
        if (node == null) return null;

        return node.key;
    }

    private Node floor(Node node, Key key) {
        if (node == null) return null;

        int comparison = key.compareTo(node.key);
        if (comparison == 0) return node;
        if (comparison < 0) return floor(node.left, key);

        Node rightFloor = floor(node.right, key);
        if (rightFloor == null) return node;
        return rightFloor;
    }

    public Key ceiling(Key key) {
        Node node = ceiling(root, key);
        if (node == null) return null;

        return node.key;
    }

    private Node ceiling(Node node, Key key) {
        if (node == null) return null;

        int comparison = key.compareTo(node.key);
        if (comparison == 0) return node;
        if (comparison > 0) return ceiling(node.right, key);

        Node rightCeiling = ceiling(node.right, key);
        if (rightCeiling == null) return node;
        return rightCeiling;
    }

    public boolean isEmpty() {
        return size(root) == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.size;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null) return 0;
        int comparison = key.compareTo(node.key);
        if (comparison < 0) return rank(node.left, key);
        else if (comparison > 0) return 1 + size(node.left) + rank(node.right, key);
        else return size(node.left);
    }

    public Key select(int rank) {
        return select(root, rank);
    }

    private Key select(Node node, int rank) {
        if (node == null) return null;
        if (rank < node.size - 1) {
            return select(node.left, rank);
        } else if (rank > node.size - 1) {
            return null;
        }

        Key rightKey = select(node.right, rank - size(node.left) - 1);
        if (rightKey == null) return node.key;
        return rightKey;
    }

    private class KeyIterator implements Iterator<Key> {
        private Queue<Key> queue;

        public KeyIterator(Queue<Key> queue) {
            this.queue = queue;
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return queue.dequeue();
        }
    }

    private class KeyIterable implements Iterable<Key> {
        private Node node;
        private Key low;
        private Key high;

        public KeyIterable(Node node, Key low, Key high) {
            this.node = node;
            this.low = low;
            this.high = high;
        }

        public Iterator<Key> iterator() {
            Queue<Key> queue = new ResizingArrayQueue<>();
            fill(queue, node, low, high);
            return new KeyIterator(queue);
        }

        private void fill(Queue<Key> queue, Node node, Key low, Key high) {
            if (node == null ||
                    (low != null && node.key.compareTo(low) < 0) ||
                    (high != null && node.key.compareTo(high) > 0)) return;

            fill(queue, node.left, low, high);
            queue.enqueue(node.key);
            fill(queue, node.right, low, high);
        }
    }

    public Iterable<Key> keys() {
        return new KeyIterable(root, null, null);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node == null) return null;
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node node) {
        if (node == null) return null;
        if (node.right == null) return node.left;
        node.right = deleteMax(node.left);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public int size(Key low, Key high) {
        return size(root, low, high);
    }

    private int size(Node node, Key low, Key high) {
        if (node == null ||
                (low != null && node.key.compareTo(low) < 0) ||
                (high != null && node.key.compareTo(high) > 0)) return 0;

        return 1 + size(node.left, low, high) + size(node.right, low, high);
    }

    public Iterable<Key> keys(Key low, Key high) {
        return new KeyIterable(root, low, high);
    }
}
