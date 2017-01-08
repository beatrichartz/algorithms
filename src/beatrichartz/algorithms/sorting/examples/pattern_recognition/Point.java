package beatrichartz.algorithms.sorting.examples.pattern_recognition;

/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(x, y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if (compareTo(that) == 0) return Double.NEGATIVE_INFINITY;
        else if (x == that.x) return Double.POSITIVE_INFINITY;
        else if (y == that.y) return 0;

        return (double) (that.y - y) / (double) (that.x - x);
    }

    public int compareTo(Point that) {
        if (y == that.y) return compareCoordinates(x, that.x);
        return compareCoordinates(y, that.y);
    }

    private int compareCoordinates(int a, int b) {
        if (a > b) return 1;
        else if (a == b) return 0;
        else return -1;
    }

    private class PointComparator implements Comparator<Point> {
        private Point point;
        private PointComparator(Point point) {
            this.point = point;
        }

        public int compare(Point point1, Point point2) {
            double slopeToPoint1 = point.slopeTo(point1);
            double slopeToPoint2 = point.slopeTo(point2);

            if (slopeToPoint1 > slopeToPoint2) return 1;
            else if (slopeToPoint1 == slopeToPoint2) return 0;
            else return -1;
        }
    }

    public Comparator<Point> slopeOrder() {
        return new PointComparator(this);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

