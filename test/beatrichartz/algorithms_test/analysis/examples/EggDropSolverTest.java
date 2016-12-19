package beatrichartz.algorithms_test.analysis.examples;

import beatrichartz.algorithms.analysis.examples.EggDrop;
import beatrichartz.algorithms.analysis.examples.EggDropSolver;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class EggDropSolverTest {

    @Test
    @Parameters({"1, 19, 3", "1, 19, 14", "1, 54, 34"})
    public void canSolveWith1EggTossingOnEachFloor(int numEggs, int numFloors, int floor) throws Exception {
        int solvedFloor = EggDropSolver.solveWithUpToFloorTries(new EggDrop(numEggs, numFloors, floor));
        assertEquals(floor, solvedFloor);
    }


    @Test
    @Parameters({"3, 19, 3", "3, 19, 14", "4, 54, 34", "8, 2990, 1095"})
    public void canSolveWithLogOfNumFloorEggsAndTries(int numEggs, int numFloors, int floor) throws Exception {
        int solvedFloor = EggDropSolver.solveWithLnToNumFloorsTries(new EggDrop(numEggs, numFloors, floor));
        assertEquals(floor, solvedFloor);
    }

    @Test
    @Parameters({"1, 19, 2", "3, 44, 19", "4, 154, 54", "7, 15876, 1095"})
    public void canSolveWithLogOfFloorEggsAndTwoLogOfFloorTries(int numEggs, int numFloors, int floor) throws Exception {
        int solvedFloor = EggDropSolver.solveWithLnToFloorTries(new EggDrop(numEggs, numFloors, floor));
        assertEquals(floor, solvedFloor);
    }

    @Test
    @Parameters({"2, 19, 2", "2, 44, 19", "2, 154, 54", "2, 15876, 554"})
    public void canSolveWithTwoEggsAndTwoSquareRootOfNumFloorsTries(int numEggs, int numFloors, int floor) throws Exception {
        int solvedFloor = EggDropSolver.solveWithTwiceSquareRootToNumFloorsTries(new EggDrop(numEggs, numFloors, floor));
        assertEquals(floor, solvedFloor);
    }

    @Test
    @Parameters({"2, 19, 2", "2, 44, 19", "2, 154, 54", "2, 15876, 554"})
    public void canSolveWithTwoEggsAndConstantTimesSquareRootOfFloorTries(int numEggs, int numFloors, int floor) throws Exception {
        int solvedFloor = EggDropSolver.solveWithConstantTimesSquareRootToFloorTries(new EggDrop(numEggs, numFloors, floor));
        assertEquals(floor, solvedFloor);
    }
}
