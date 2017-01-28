package beatrichartz.algorithms_test.priority_queues.examples.eight_puzzle;

import beatrichartz.algorithms.priority_queues.examples.eight_puzzle.Board;

public class BoardHelper {
    public static Board createBoard(String board) {
        String[] horizontal = board.split("\\|");
        int[][] input = new int[horizontal.length][horizontal.length];
        for (int i = 0; i < horizontal.length; i++) {
            String[] numbers = horizontal[i].split(" ");
            for (int j = 0; j < numbers.length; j++) {
                input[i][j] = Integer.parseInt(numbers[j]);
            }
        }

        return new Board(input);
    }
}
