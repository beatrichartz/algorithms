package beatrichartz.algorithms_test.priority_queues.examples.eight_puzzle;

import beatrichartz.algorithms.priority_queues.examples.eight_puzzle.Board;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import static beatrichartz.algorithms_test.priority_queues.examples.eight_puzzle.BoardHelper.createBoard;
import static junit.framework.TestCase.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class BoardTest {
    private Board board3x3;
    private Board board4x4;

    @Before
    public void setUp() throws Exception {
        board3x3 = createBoard("0 1 3|4 2 5|7 8 6");
        board4x4 = createBoard("0 1 3 4|5 2 6 7|9 10 8 11|13 12 14 15");
    }

    @Test
    public void dimensionIsSquareLength() throws Exception {
        assertEquals(3, board3x3.dimension());
        assertEquals(4, board4x4.dimension());
    }

    @Test
    public void hammingDistanceCountsItemsOutOfPlace() throws Exception {
        assertEquals(4, board3x3.hamming());
        assertEquals(9, board4x4.hamming());
    }

    @Test
    public void manhattanDistanceCountsMoveUntilSolved() throws Exception {
        assertEquals(4, board3x3.manhattan());
        assertEquals(12, board4x4.manhattan());
    }

    @Test
    public void goalBoardIsTrueWhenHammingIsZero() throws Exception {
        assertEquals(false, board3x3.isGoal());
        assertEquals(false, board4x4.isGoal());

        assertEquals(true, createBoard("1 2 3|4 5 6|7 8 0").isGoal());
        assertEquals(true, createBoard("1 2 3 4|5 6 7 8|9 10 11 12|13 14 15 0").isGoal());
    }

    @Test
    public void boardIsImmutableThroughInput() throws Exception {
        int[][] input = {{0, 1}, {2, 3}};
        int[][] otherInput = {{0, 1}, {2, 3}};
        Board board = new Board(input);
        Board otherBoard = new Board(otherInput);

        assertEquals(board, otherBoard);
        input[0][0] = 3;
        input[1][1] = 0;

        assertEquals(board, otherBoard);
    }

    @Test
    public void equalsComparesBoards() throws Exception {
        assertEquals(false, board3x3.equals(new Object()));
        assertEquals(false, board3x3.equals(board4x4));
        assertEquals(true, board3x3.equals(createBoard("0 1 3|4 2 5|7 8 6")));
        assertEquals(false, board3x3.equals(createBoard("0 2 3|4 2 5|7 8 6")));
    }

    @Test
    @Parameters
    public void twinReturnsABoardWithTwoArbitraryMoves(Board board) throws Exception {
        Field blocksField = board.getClass().getDeclaredField("blocks");
        blocksField.setAccessible(true);
        int[][] blocks = (int[][]) blocksField.get(board);

        Board twinBoard = board.twin();
        int[][] twinBoardBlocks = (int[][]) blocksField.get(twinBoard);

        assertEquals(twinBoardBlocks.length, blocks.length);

        int differences = 0;
        for (int i = 0; i < twinBoardBlocks.length * twinBoardBlocks.length; i++) {
            int x = i % twinBoardBlocks.length;
            int y = i / twinBoardBlocks.length;

            int twinBlock = twinBoardBlocks[y][x];
            int block = blocks[y][x];

            if (block != 0 && twinBlock != 0 && twinBlock != block) differences++;
        }

        assertEquals(2, differences);
    }
    public Object parametersForTwinReturnsABoardWithTwoArbitraryMoves() {
        return new Object[]{
                new Object[]{
                        createBoard("0 1 3|4 2 5|7 8 6"),
                },
                new Object[]{
                        createBoard("1 2 3|4 6 5|7 8 0"),
                },
                new Object[]{
                        createBoard("1 0|2 3"),
                },
        };
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    @Parameters
    public void neighboursIsReturningAllNeighbours(Board originalBoard, Board[] expectedNeighbours) throws Exception {
        Iterable<Board> neighbours = originalBoard.neighbors();

        int i = 0;
        for (Board neighbour : neighbours) {
            assertEquals(expectedNeighbours[i], neighbour);
            i++;
        }

        assertEquals(i, expectedNeighbours.length);
    }

    public Object parametersForNeighboursIsReturningAllNeighbours() {
        return new Object[]{
                new Object[]{
                        createBoard("0 1 3|4 2 5|7 8 6"),
                        new Board[]{
                                createBoard("4 1 3|0 2 5|7 8 6"),
                                createBoard("1 0 3|4 2 5|7 8 6")
                        }
                },
                new Object[]{
                        createBoard("1 3 0|4 2 5|7 8 6"),
                        new Board[]{
                                createBoard("1 3 5|4 2 0|7 8 6"),
                                createBoard("1 0 3|4 2 5|7 8 6")
                        }
                },
                new Object[]{
                        createBoard("1 2 3|4 6 5|7 8 0"),
                        new Board[]{
                                createBoard("1 2 3|4 6 0|7 8 5"),
                                createBoard("1 2 3|4 6 5|7 0 8")
                        }
                },
                new Object[]{
                        createBoard("1 2 3|4 6 5|0 7 8"),
                        new Board[]{
                                createBoard("1 2 3|0 6 5|4 7 8"),
                                createBoard("1 2 3|4 6 5|7 0 8")
                        }
                },
                new Object[]{
                        createBoard("1 2 3|4 6 0|5 7 8"),
                        new Board[]{
                                createBoard("1 2 0|4 6 3|5 7 8"),
                                createBoard("1 2 3|4 6 8|5 7 0"),
                                createBoard("1 2 3|4 0 6|5 7 8")
                        }
                },
                new Object[]{
                        createBoard("1 2 3|0 4 6|5 7 8"),
                        new Board[]{
                                createBoard("0 2 3|1 4 6|5 7 8"),
                                createBoard("1 2 3|5 4 6|0 7 8"),
                                createBoard("1 2 3|4 0 6|5 7 8")
                        }
                },
                new Object[]{
                        createBoard("1 2 3|5 4 6|7 0 8"),
                        new Board[]{
                                createBoard("1 2 3|5 0 6|7 4 8"),
                                createBoard("1 2 3|5 4 6|0 7 8"),
                                createBoard("1 2 3|5 4 6|7 8 0")
                        }
                },
                new Object[]{
                        createBoard("1 0 3|5 4 2|7 6 8"),
                        new Board[]{
                                createBoard("1 4 3|5 0 2|7 6 8"),
                                createBoard("0 1 3|5 4 2|7 6 8"),
                                createBoard("1 3 0|5 4 2|7 6 8")
                        }
                },
                new Object[]{
                        createBoard("4 1 3|2 0 5|7 8 6"),
                        new Board[]{
                                createBoard("4 0 3|2 1 5|7 8 6"),
                                createBoard("4 1 3|2 8 5|7 0 6"),
                                createBoard("4 1 3|0 2 5|7 8 6"),
                                createBoard("4 1 3|2 5 0|7 8 6")
                        }
                }
        };
    }

    @Test
    public void stringRepresentationIsDimensionFollowedByBoard() throws Exception {
        assertEquals("3\n 0  1  3 \n 4  2  5 \n 7  8  6 \n", board3x3.toString());
        assertEquals("4\n 0  1  3  4 \n 5  2  6  7 \n 9 10  8 11 \n13 12 14 15 \n", board4x4.toString());
    }
}
