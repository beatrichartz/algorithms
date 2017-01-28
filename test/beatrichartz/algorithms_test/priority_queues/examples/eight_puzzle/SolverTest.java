package beatrichartz.algorithms_test.priority_queues.examples.eight_puzzle;

import beatrichartz.algorithms.priority_queues.examples.eight_puzzle.Board;
import beatrichartz.algorithms.priority_queues.examples.eight_puzzle.Solver;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static beatrichartz.algorithms_test.priority_queues.examples.eight_puzzle.BoardHelper.createBoard;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;
import static junit.framework.TestCase.assertNull;

@RunWith(JUnitParamsRunner.class)
public class SolverTest {
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Test
    public void throwsNullPointerWhenGivenNull() throws Exception {
        expectedException.expect(NullPointerException.class);

        new Solver(null);
    }

    @Test
    public void isSolvableForBoard() throws Exception {
        Solver unsolvableSolver = new Solver(createBoard("1 2 3|4 5 6|8 7 0"));
        assertEquals(false, unsolvableSolver.isSolvable());
        assertEquals(-1, unsolvableSolver.moves());
        assertNull(unsolvableSolver.solution());

        Solver solvableSolver = new Solver(createBoard("0 1 3|4 2 5|7 8 6"));
        assertEquals(true, solvableSolver.isSolvable());
        assertNotSame(-1, solvableSolver.moves());
    }


    @Test
    @Parameters
    public void solutionReturnsSolutionPath(Board initialBoard, Board[] expectedSolutionPath) throws Exception {
        int i = 0;
        Iterable<Board> solutionPath = new Solver(initialBoard).solution();
        for (Board solutionBoard : solutionPath) {
            assertEquals(solutionBoard, expectedSolutionPath[i]);
            i++;
        }

        assertEquals(i, expectedSolutionPath.length);
    }
    public Object parametersForSolutionReturnsSolutionPath() {
        return new Object[]{
                new Object[]{
                        createBoard("1 2 3|4 5 6|7 8 0"),
                        new Board[]{
                                createBoard("1 2 3|4 5 6|7 8 0")
                        }
                },
                new Object[]{
                        createBoard("0 1 3|4 2 5|7 8 6"),
                        new Board[]{
                                createBoard("0 1 3|4 2 5|7 8 6"),
                                createBoard("1 0 3|4 2 5|7 8 6"),
                                createBoard("1 2 3|4 0 5|7 8 6"),
                                createBoard("1 2 3|4 5 0|7 8 6"),
                                createBoard("1 2 3|4 5 6|7 8 0")
                        }
                },
                new Object[]{
                        createBoard("1 3 6|5 2 8|4 0 7"),
                        new Board[]{
                                createBoard("1 3 6|5 2 8|4 0 7"),
                                createBoard("1 3 6|5 2 8|4 7 0"),
                                createBoard("1 3 6|5 2 0|4 7 8"),
                                createBoard("1 3 0|5 2 6|4 7 8"),
                                createBoard("1 0 3|5 2 6|4 7 8"),
                                createBoard("1 2 3|5 0 6|4 7 8"),
                                createBoard("1 2 3|0 5 6|4 7 8"),
                                createBoard("1 2 3|4 5 6|0 7 8"),
                                createBoard("1 2 3|4 5 6|7 0 8"),
                                createBoard("1 2 3|4 5 6|7 8 0")
                        }
                },
        };
    }

    @Test
    @Parameters
    public void movesForSolvableBoards(Board board, int expectedMoves) throws Exception {
        Solver solver = new Solver(board);
        assertEquals(expectedMoves, solver.moves());
    }
    public Object parametersForMovesForSolvableBoards() {
        return new Object[]{
                new Object[]{
                        createBoard("1 2 3|4 5 6|7 8 0"), 0
                },
                new Object[]{
                        createBoard("0 1 3|4 2 5|7 8 6"), 4
                },
                new Object[]{
                        createBoard("1 2 3|0 7 6|5 4 8"), 7
                },
                new Object[]{
                        createBoard("1 3 6|5 2 8|4 0 7"), 9
                },
                new Object[]{
                        createBoard("4 1 2|3 0 6|5 7 8"), 12
                },
                new Object[]{
                        createBoard("2 0 8|1 3 5|4 6 7"), 15
                },
                new Object[]{
                        createBoard("7 4 3|2 8 6|0 5 1"), 20
                },
                new Object[]{
                        createBoard("6 5 3|4 1 7|0 2 8"), 24
                },
                new Object[]{
                        createBoard("6 3 8|5 4 1|7 2 0"), 28
                },
                new Object[]{
                        createBoard("1 2 4 12|5 6 3 0|9 10 8 7|13 14 11 15"), 10
                },
                new Object[]{
                        createBoard("6 3 7 4|2 9 10 8|1 5 12 15|13 0 14 11"), 20
                },
                new Object[]{
                        createBoard("1 2 3 0|5 12 7 4|13 6 14 9|10 8 11 15"), 27
                },
                new Object[]{
                        createBoard("6 7 4 11|3 2 8 12|1 5 13 15|0 9 10 14"), 33
                }
        };
    }
}
