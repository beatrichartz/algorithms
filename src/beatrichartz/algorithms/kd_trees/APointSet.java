package beatrichartz.algorithms.kd_trees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public interface APointSet {
    boolean isEmpty();
    int size();
    void insert(Point2D p);
    boolean contains(Point2D p);
    void draw();
    Iterable<Point2D> range(RectHV rect);
    Point2D nearest(Point2D p);
}
