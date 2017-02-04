package beatrichartz.algorithms.kd_trees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PointSET implements APointSet {
    private RedBlackBST<Point2D, Boolean> points = new RedBlackBST<>();

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        throwIfNull(p);
        points.put(p, true);
    }

    public boolean contains(Point2D p) {
        throwIfNull(p);
        return points.contains(p);
    }

    private void throwIfNull(Object p) {
        if (p == null) throw new NullPointerException();
    }

    public void draw() {
        for (Point2D point : points.keys()) {
            StdDraw.point(point.x(), point.y());
        }
    }

    private class PointsIterator implements Iterator<Point2D> {
        private final Iterator<Point2D> pointsIterator;

        public PointsIterator(Iterator<Point2D> pointsIterator) {
            this.pointsIterator = pointsIterator;
        }

        public boolean hasNext() {
            return pointsIterator.hasNext();
        }

        public Point2D next() {
            return pointsIterator.next();
        }
    }

    private class PointsIterable implements Iterable<Point2D> {
        private final Iterator<Point2D> pointsIterator;
        public PointsIterable(Iterator<Point2D> pointsIterator) {
            this.pointsIterator = pointsIterator;
        }

        public Iterator<Point2D> iterator() {
            return new PointsIterator(pointsIterator);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        List<Point2D> pointsList = new ArrayList<>();
        for (Point2D point : points.keys()) {
            if (rect.contains(point)) pointsList.add(point);
        }
        return new PointsIterable(pointsList.iterator());
    }

    public Point2D nearest(Point2D p) {
        if (points.isEmpty()) return null;

        Point2D nearest = points.min();
        for (Point2D point : points.keys()) {
            if (p.distanceTo(point) < p.distanceTo(nearest)) nearest = point;
        }

        return nearest;
    }
}
