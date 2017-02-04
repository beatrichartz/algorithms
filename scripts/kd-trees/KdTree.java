

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class KdTree {
    private class Node {
        private final Point2D point;
        private Node left;
        private Node right;
        private int size;

        public Node(Point2D point, int size) {
            this.point = point;
            this.size = size;
        }
    }

    private Node root;

    private void throwIfNull(Object p) {
        if (p == null) throw new NullPointerException();
    }

    public void insert(Point2D point) {
        throwIfNull(point);
        root = put(root, point, 0);
    }

    private Node put(Node node, Point2D point, int level) {
        if (node == null) {
            return new Node(point, 1);
        }

        double compare1 = point.x();
        double compare2 = node.point.x();
        if (level % 2 != 0) {
            compare1 = point.y();
            compare2 = node.point.y();
        }

        if (compare1 > compare2) {
            node.right = put(node.right, point, level + 1);
        } else {
            node.left = put(node.left, point, level + 1);
        }

        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    private Point2D get(Point2D point) {
        Node node = root;
        return get(node, point, 0);
    }

    private Point2D get(Node node, Point2D point, int level) {
        if (node == null) return null;

        double compare1 = point.x();
        double compare2 = node.point.x();
        if (level % 2 != 0) {
            compare1 = point.y();
            compare2 = node.point.y();
        }

        if (point.equals(node.point)) {
            return node.point;
        } else if (compare1 > compare2) {
            return get(node.right, point, level + 1);
        } else {
            return get(node.left, point, level + 1);
        }
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

    public boolean contains(Point2D point) {
        throwIfNull(point);
        return get(point) != null;
    }

    public void draw() {
        draw(root, new RectHV(0, 0, 1, 1), 0);
    }

    private void draw(Node node, RectHV subTreeRect, int level) {
        if (node == null) return;
        double x = node.point.x();
        double y = node.point.y();

        StdDraw.setPenColor(Color.black);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(x, node.point.y());
        StdDraw.setPenRadius(0.001);

        double xmin = subTreeRect.xmin();
        double ymin = subTreeRect.ymin();
        double ymax = subTreeRect.ymax();
        double xmax = subTreeRect.xmax();

        if (level % 2 == 0) {
            StdDraw.setPenColor(Color.red);
            StdDraw.line(x, ymin, x, ymax);

            draw(node.left, new RectHV(xmin, ymin, x, ymax), level + 1);
            draw(node.right, new RectHV(x, ymin, xmax, ymax), level + 1);
        } else {
            StdDraw.setPenColor(Color.blue);
            StdDraw.line(xmin, y, xmax, y);

            draw(node.left, new RectHV(xmin, ymin, xmax, y), level + 1);
            draw(node.right, new RectHV(xmin, y, xmax, ymax), level + 1);
        }

    }

    private class LinkedListNode {
        Point2D point;
        LinkedListNode next;

        public LinkedListNode(Point2D point) {
            this.point = point;
        }
    }

    private class PointsIterator implements Iterator<Point2D> {
        private LinkedListNode node;

        public PointsIterator(LinkedListNode node) {
            this.node = node;
        }

        public boolean hasNext() {
            return node != null;
        }

        public Point2D next() {
            if (!hasNext()) throw new NoSuchElementException();

            Point2D point = node.point;
            node = node.next;
            return point;
        }
    }
    private class PointsIterable implements Iterable<Point2D> {
        private LinkedListNode node;

        public PointsIterable(LinkedListNode node) {
            this.node = node;
        }

        public Iterator<Point2D> iterator() {
            return new PointsIterator(node);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        LinkedListNode first = range(null, root, rect, new RectHV(0, 0, 1, 1), 0);
        return new PointsIterable(first);
    }

    private LinkedListNode range(LinkedListNode points, Node node, RectHV queryRect, RectHV subTreeRect, int level) {
        if (node == null || !queryRect.intersects(subTreeRect)) return points;

        LinkedListNode listNode = new LinkedListNode(node.point);
        if (queryRect.contains(node.point)) {
            if (points != null) points.next = listNode;
            points = listNode;
        }

        double x = node.point.x();
        double y = node.point.y();

        double xmin = subTreeRect.xmin();
        double ymin = subTreeRect.ymin();
        double ymax = subTreeRect.ymax();
        double xmax = subTreeRect.xmax();

        if (level % 2 == 0) {
            range(points, node.left, queryRect, new RectHV(xmin, ymin, x, ymax), level + 1);
            range(points, node.right, queryRect, new RectHV(x, ymin, xmax, ymax), level + 1);
        } else {
            range(points, node.left, queryRect, new RectHV(xmin, ymin, xmax, y), level + 1);
            range(points, node.right, queryRect, new RectHV(xmin, y, xmax, ymax), level + 1);
        }

        return points;
    }

    public Point2D nearest(Point2D p) {
        throwIfNull(p);
        return nearest(root.point, root, p, new RectHV(0, 0, 1, 1), 0);
    }

    private Point2D nearest(Point2D currentNearest, Node node, Point2D queryPoint, RectHV subTreeRect, int level) {
        double currentNearestDistance = queryPoint.distanceTo(currentNearest);
        if (node == null || subTreeRect.distanceTo(queryPoint) >= currentNearestDistance)
            return currentNearest;
        if (queryPoint.distanceTo(node.point) < currentNearestDistance)
            currentNearest = node.point;

        double x = node.point.x();
        double y = node.point.y();

        double xmin = subTreeRect.xmin();
        double ymin = subTreeRect.ymin();
        double ymax = subTreeRect.ymax();
        double xmax = subTreeRect.xmax();

        if (level % 2 == 0 && queryPoint.x() < x) {
            currentNearest = nearest(currentNearest, node.left, queryPoint, new RectHV(xmin, ymin, x, ymax), level + 1);
            currentNearest = nearest(currentNearest, node.right, queryPoint, new RectHV(x, ymin, xmax, ymax), level + 1);
        } else if (level % 2 == 0 && queryPoint.x() >= x) {
            currentNearest = nearest(currentNearest, node.right, queryPoint, new RectHV(x, ymin, xmax, ymax), level + 1);
            currentNearest = nearest(currentNearest, node.left, queryPoint, new RectHV(xmin, ymin, x, ymax), level + 1);
        } else if (level % 2 > 0 && queryPoint.y() < y) {
            currentNearest = nearest(currentNearest, node.left, queryPoint, new RectHV(xmin, ymin, xmax, y), level + 1);
            currentNearest = nearest(currentNearest, node.right, queryPoint, new RectHV(xmin, y, xmax, ymax), level + 1);
        } else {
            currentNearest = nearest(currentNearest, node.right, queryPoint, new RectHV(xmin, y, xmax, ymax), level + 1);
            currentNearest = nearest(currentNearest, node.left, queryPoint, new RectHV(xmin, ymin, xmax, y), level + 1);
        }

        return currentNearest;
    }
}
