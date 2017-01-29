package beatrichartz.algorithms.elementary_symbol_tables;

import beatrichartz.algorithms.stacks_and_queues.Queue;
import beatrichartz.algorithms.stacks_and_queues.ResizingArrayQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RedBlackBinarySearchTreeSymbolTable<Key extends Comparable<Key>, Value> implements OrderedSymbolTable<Key, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int size;
        private boolean color;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.color = RED;
        }
    }

    private Node root;

    private Node rotateLeft(Node node) {
        Node rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        rightNode.color = node.color;
        rightNode.size = node.size;
        node.color = RED;
        resize(node);

        return rightNode;
    }

    private Node rotateRight(Node node) {
        Node leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        leftNode.color = node.color;
        leftNode.size = node.size;
        node.color = RED;
        resize(node);

        return leftNode;
    }

    private Node moveRedLeft(Node node) {
        colorFlip(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            colorFlip(node);
        }

        return node;
    }

    private Node moveRedRight(Node node) {
        colorFlip(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            colorFlip(node);
        }

        return node;
    }

    private void colorFlip(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private boolean isRed(Node node) {
        if (node == null) return false;
        return node.color == RED;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
        colorRootBlack();
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

        return balance(node);
    }

    private Node balance(Node node) {
        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) colorFlip(node);

        resize(node);
        return node;
    }

    private void resize(Node node) {
        node.size = 1 + size(node.left) + size(node.right);
    }


    public Value get(Key key) {
        Node node = root;
        return get(node, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) return null;

        int comparison = node.key.compareTo(key);
        if (comparison < 0) {
            return get(node.right, key);
        } else if (comparison > 0) {
            return get(node.left, key);
        } else {
            return node.value;
        }
    }


    public void delete(Key key) {
        if (isBlack(root.left) && isBlack(root.right))
            root.color = RED;

        root = delete(root, key);
        colorRootBlack();
    }

    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int comparison = key.compareTo(node.key);

        if (comparison < 0 && !isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
            node.left = delete(node.left, key);
        } else if (comparison < 0) {
            node.left = delete(node.left, key);
        } else {
            if (isRed(node.left)) node = rotateRight(node);
            if (comparison == 0 && node.right == null) return null;
            if (isBlack(node.right) && isBlack(node.right.left)) node = moveRedRight(node);
            if (comparison == 0) {
                Key newKey = min(node.right).key;
                node.value = get(node.right, newKey);
                node.key = newKey;
                node.right = deleteMin(node.right);
            } else {
                node.right = delete(node.right, key);
            }
        }

        return balance(node);
    }

    private boolean isBlack(Node node) {
        if (node == null) return false;
        return node.color == BLACK;
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
        return size() == 0;
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

        int leftSize = size(node.left);
        if (leftSize > rank) return select(node.left, rank);
        else if (leftSize < rank) return select(node.right, rank - leftSize - 1);
        else return node.key;
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
        colorRootBlack();
    }

    private void colorRootBlack() {
        if (root != null) root.color = BLACK;
    }

    private Node deleteMin(Node node) {
        if (node == null) return null;
        if (node.left == null) return node.right;

        if (isBlack(node.left) && isBlack(node.left.left))
            node = moveRedLeft(node);

        node.left = deleteMin(node.left);
        return balance(node);
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node node) {
        if (node == null) return null;
        if (isRed(node.left)) node = rotateRight(node);
        if (node.right == null) return node.left;

        if (isBlack(node.right) && isBlack(node.right.left))
            node = moveRedLeft(node);

        node.right = deleteMax(node.left);
        return balance(node);
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
