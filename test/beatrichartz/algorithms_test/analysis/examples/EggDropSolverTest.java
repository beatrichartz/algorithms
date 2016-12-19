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
        EggDrop eggDrop = new EggDrop(numEggs, numFloors, floor);
        int solvedFloor = EggDropSolver.solveWithUpToFloorTries(eggDrop);

        assertEquals(floor, solvedFloor);
        assertEquals(true, floor + 1 >= eggDrop.getNumTosses());
    }


    @Test
    @Parameters({"3, 19, 3", "3, 19, 14", "4, 54, 34", "8, 2990, 1095"})
    public void canSolveWithLogOfNumFloorEggsAndTries(int numEggs, int numFloors, int floor) throws Exception {
        EggDrop eggDrop = new EggDrop(numEggs, numFloors, floor);
        int solvedFloor = EggDropSolver.solveWithLnToNumFloorsTries(eggDrop);

        assertEquals(floor, solvedFloor);
        int maxTries = (int) Math.ceil(1.5 * Math.log(numFloors));
        assertEquals(true, maxTries >= eggDrop.getNumTosses());
    }

    @Test
    @Parameters({"1, 19, 2", "3, 44, 19", "4, 154, 54", "7, 15876, 1095"})
    public void canSolveWithLogOfFloorEggsAndTwoLogOfFloorTries(int numEggs, int numFloors, int floor) throws Exception {
        EggDrop eggDrop = new EggDrop(numEggs, numFloors, floor);
        int solvedFloor = EggDropSolver.solveWithLnToFloorTries(eggDrop);

        assertEquals(floor, solvedFloor);
        int maxTries = (int) Math.ceil(3 * Math.log(floor));
        assertEquals(true, maxTries >= eggDrop.getNumTosses());
    }

    @Test
    @Parameters({"2, 19, 2", "2, 44, 19", "2, 154, 54", "2, 15876, 554"})
    public void canSolveWithTwoEggsAndTwoSquareRootOfNumFloorsTries(int numEggs, int numFloors, int floor) throws Exception {
        EggDrop eggDrop = new EggDrop(numEggs, numFloors, floor);
        int solvedFloor = EggDropSolver.solveWithTwiceSquareRootToNumFloorsTries(eggDrop);

        assertEquals(floor, solvedFloor);
        int maxTries = (int) Math.ceil(Math.sqrt(2 * numFloors));
        assertEquals(true, maxTries >= eggDrop.getNumTosses());
    }

    @Test
    @Parameters({"2, 19, 2", "2, 44, 19", "2, 154, 54", "2, 15876, 554"})
    public void canSolveWithTwoEggsAndConstantTimesSquareRootOfFloorTries(int numEggs, int numFloors, int floor) throws Exception {
        EggDrop eggDrop = new EggDrop(numEggs, numFloors, floor);
        int solvedFloor = EggDropSolver.solveWithConstantTimesSquareRootToFloorTries(eggDrop);

        assertEquals(floor, solvedFloor);
        int maxTries = (int) Math.ceil((2 * Math.sqrt(2)) * Math.sqrt(floor));
        assertEquals(true, maxTries >= eggDrop.getNumTosses());
    }
}
