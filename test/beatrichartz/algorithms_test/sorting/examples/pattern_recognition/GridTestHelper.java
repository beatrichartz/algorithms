package beatrichartz.algorithms_test.sorting.examples.pattern_recognition;

import beatrichartz.algorithms.sorting.examples.pattern_recognition.Point;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GridTestHelper {
    Point[] getPointsForGrid(String gridName) {
        List<String> grid = new ArrayList<String>();
        try {
            grid = Files.readAllLines(Paths.get("test/resources/fixtures/pattern_recognition", gridName + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Integer capacity = grid.stream().map(line -> countChar(line, "x")).reduce(0, (x, y) -> x + y);
        Point[] points = new Point[capacity];
        int index = 0;
        for (int y = grid.size() - 1; y >= 0; y--) {
            String line = grid.get(grid.size() - y - 1);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == 'x') {
                    points[index++] = new Point(x, y);
                }
            }
        }
        return points;
    }

    private int countChar(String pointsString, String character) {
        return pointsString.length() - pointsString.replace(character, "").length();
    }
}