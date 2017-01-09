package beatrichartz.algorithms.sorting.examples.pattern_recognition;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FastCollinearPoints implements CollinearPoints {
    private final LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new NullPointerException();
        List<LineSegment> segmentsList = new ArrayList<>();

        Point[] iterPoints = points.clone();
        Point[] workPoints = points.clone();

        for (int i = 0; i < iterPoints.length; i++) {
            Point point = iterPoints[i];
            assertNotNull(point);
            for (int j = i + 1; j < iterPoints.length; j++) {
                assertNotSame(point, iterPoints[j]);
            }

            sortBySlopesToPoint(workPoints, point);
            extractSegments(workPoints, segmentsList, point);
        }

        this.lineSegments = segmentsList.toArray(new LineSegment[0]);
    }

    private void extractSegments(Point[] points, List<LineSegment> segmentsList, Point point) {
        double slope = point.slopeTo(points[0]);
        int segmentCount = 1;
        int segmentStart = 0;

        for (int j = 1; j < points.length; j++) {
            double currentSlope = point.slopeTo(points[j]);
            boolean sameSegment = Double.compare(currentSlope, slope) == 0;

            if (sameSegment) {
                segmentCount++;
            } else {
                extractSegment(points, segmentsList, point, segmentCount, segmentStart, j);
                segmentCount = 1;
                slope = currentSlope;
                segmentStart = j;
            }
        }

        extractSegment(points, segmentsList, point, segmentCount, segmentStart, points.length);
    }

    private void extractSegment(Point[] points,
                                List<LineSegment> segmentsList,
                                Point point,
                                int segmentCount,
                                int segmentStart,
                                int j) {
        if (segmentCount >= 3 && getMax(point, points, segmentStart, j - 1).compareTo(point) == 0) {
            Point maxPoint = getMax(point, points, segmentStart, j - 1);
            Point minPoint = getMin(point, points, segmentStart, j - 1);
            segmentsList.add(new LineSegment(maxPoint, minPoint));
        }
    }

    private Point getMin(Point point, Point[] points, int low, int high) {
        Point min = point;
        for (int i = low; i <= high; i++) {
            if (min.compareTo(points[i]) > 0) min = points[i];
        }

        return min;
    }

    private Point getMax(Point point, Point[] points, int low, int high) {
        Point max = point;
        for (int i = low; i <= high; i++) {
            if (max.compareTo(points[i]) < 0) max = points[i];
        }

        return max;
    }


    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments.clone();
    }

    private void sortBySlopesToPoint(Point[] points, Point point) {
        StdRandom.shuffle(points);
        sortBySlopes(points, point.slopeOrder(), 0, points.length - 1);
    }

    private void sortBySlopes(Point[] points, Comparator<Point> pointComparator, int low, int high) {
        if (high <= low) return;

        int partition = partition(points, pointComparator, low, high);
        sortBySlopes(points, pointComparator, low, partition - 1);
        sortBySlopes(points, pointComparator, partition + 1, high);
    }

    private int partition(Point[] points, Comparator<Point> pointComparator, int low, int high) {
        int i = low;
        int j = high + 1;

        while (true) {
            while (isLessThan(points, pointComparator, ++i, low))
                if (i == high) break;
            while (isLessThan(points, pointComparator, low, --j))
                if (j == low) break;

            if (i >= j) break;

            swap(points, i, j);
        }

        swap(points, low, j);
        return j;
    }

    private void swap(Point[] points, int i, int j) {
        Point currentPoint = points[i];
        points[i] = points[j];
        points[j] = currentPoint;
    }

    private boolean isLessThan(Point[] points, Comparator<Point> pointComparator, int i, int j) {
        Point point1 = points[i];
        Point point2 = points[j];
        assertNotNull(point1);
        assertNotNull(point2);
        if (i == j) return false;

        return pointComparator.compare(point1, point2) < 0;
    }

    private void assertNotSame(Point point1, Point point2) {
        if (point1.compareTo(point2) == 0) throw new IllegalArgumentException();
    }

    private void assertNotNull(Point point) {
        if (point == null) throw new NullPointerException();
    }
}
