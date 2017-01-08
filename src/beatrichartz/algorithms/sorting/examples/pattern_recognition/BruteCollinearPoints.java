package beatrichartz.algorithms.sorting.examples.pattern_recognition;

public class BruteCollinearPoints implements CollinearPoints {
    private final Point[] points;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new NullPointerException();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new NullPointerException();
            for (int j = i+1; j < points.length; j++) {
                if (points[j] == null) throw new NullPointerException();
                if (points[j].compareTo(points[i]) == 0) throw new IllegalArgumentException();
            }
        }

        this.points = points;
    }

    public int numberOfSegments() {
        int segments = 0;
        for (int i1 = 0; i1 < points.length; i1++) {
            Point point1 = points[i1];
            for (int i2 = i1+1; i2 < points.length; i2++) {
                Point point2 = points[i2];
                for (int i3 = i2+1; i3 < points.length; i3++) {
                    Point point3 = points[i3];
                    for (int i4 = i3+1; i4 < points.length; i4++) {
                        Point point4 = points[i4];
                        if (point1.slopeTo(point2) == point1.slopeTo(point3) &&
                                point1.slopeTo(point2) == point1.slopeTo(point4) &&
                                point1.slopeTo(point3) == point1.slopeTo(point4)) {
                            segments++;
                        }
                    }
                }
            }
        }

        return segments;
    }

    public LineSegment[] segments() {
        int segments = 0;
        LineSegment[] lineSegments = new LineSegment[1000_000];

        for (int i1 = 0; i1 < points.length; i1++) {
            Point point1 = points[i1];
            for (int i2 = i1+1; i2 < points.length; i2++) {
                Point point2 = points[i2];
                for (int i3 = i2+1; i3 < points.length; i3++) {
                    Point point3 = points[i3];
                    for (int i4 = i3+1; i4 < points.length; i4++) {
                        Point point4 = points[i4];
                        if (point1.slopeTo(point2) == point1.slopeTo(point3) &&
                                point1.slopeTo(point2) == point1.slopeTo(point4) &&
                                point1.slopeTo(point3) == point1.slopeTo(point4)) {

                            Point y = getMin(new Point[]{point1, point2, point3, point4});
                            Point x = getMax(new Point[]{point1, point2, point3, point4});

                            lineSegments[segments++] = new LineSegment(x, y);
                        }
                    }
                }
            }
        }

        LineSegment[] returnSegments = new LineSegment[segments];
        System.arraycopy(lineSegments, 0, returnSegments, 0, segments);

        return returnSegments;
    }

    private Point getMin(Point[] points) {
        Point min = points[0];
        for (int i = 0; i < points.length; i++) {
            if (min.compareTo(points[i]) > 0) {
                min = points[i];
            }
        }

        return min;
    }

    private Point getMax(Point[] points) {
        Point max = points[0];
        for (int i = 0; i < points.length; i++) {
            if (max.compareTo(points[i]) < 0) {
                max = points[i];
            }
        }

        return max;
    }
}
