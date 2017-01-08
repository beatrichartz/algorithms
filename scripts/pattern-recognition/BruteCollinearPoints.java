

import java.util.ArrayList;

public class BruteCollinearPoints {
    private final LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new NullPointerException();
        ArrayList<LineSegment> lineSegments = new ArrayList<>();

        for (int i1 = 0; i1 < points.length; i1++) {
            Point point1 = points[i1];
            assertNotNull(point1);

            for (int i2 = i1+1; i2 < points.length; i2++) {
                Point point2 = points[i2];
                assertNotNull(point2);
                assertNotSame(point1, point2);

                for (int i3 = i2+1; i3 < points.length; i3++) {
                    Point point3 = points[i3];
                    assertNotNull(point3);

                    for (int i4 = i3+1; i4 < points.length; i4++) {
                        Point point4 = points[i4];
                        assertNotNull(point4);

                        if (point1.slopeTo(point2) == point1.slopeTo(point3) &&
                                point1.slopeTo(point2) == point1.slopeTo(point4) &&
                                point1.slopeTo(point3) == point1.slopeTo(point4)) {

                            Point y = getMin(point1, point2, point3, point4);
                            Point x = getMax(point1, point2, point3, point4);

                            lineSegments.add(new LineSegment(x, y));
                        }
                    }
                }
            }
        }

        this.lineSegments = lineSegments.toArray(new LineSegment[0]);
    }

    private void assertNotSame(Point point1, Point point2) {
        if (point1.compareTo(point2) == 0) throw new IllegalArgumentException();
    }

    private void assertNotNull(Point point1) {
        if (point1 == null) throw new NullPointerException();
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments.clone();
    }

    private Point getMin(Point... points) {
        Point min = points[0];
        for (int i = 0; i < points.length; i++) {
            if (min.compareTo(points[i]) > 0) {
                min = points[i];
            }
        }

        return min;
    }

    private Point getMax(Point... points) {
        Point max = points[0];
        for (int i = 0; i < points.length; i++) {
            if (max.compareTo(points[i]) < 0) {
                max = points[i];
            }
        }

        return max;
    }
}
