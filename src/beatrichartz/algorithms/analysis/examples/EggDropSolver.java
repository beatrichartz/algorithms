package beatrichartz.algorithms.analysis.examples;

/*
Egg drop. Suppose that you have an n-story building (with floors 1 through n) and plenty of eggs. An egg breaks if
it is dropped from floor T or higher and does not break otherwise. Your goal is to devise a strategy to determine the
value of T given the following limitations on the number of eggs and tosses:

    Version 0: 1 egg, ≤T tosses.
    Version 1: ∼1lg⁡n eggs and ∼1lg⁡n tosses.
    Version 2: ∼lg⁡T eggs and ∼2lg⁡T tosses.
    Version 3: 2 eggs and ∼2n tosses.
    Version 4: 2 eggs and ≤cT tosses for some fixed constant c
 */
public class EggDropSolver {
    public static int solveWithUpToFloorTries(EggDrop eggDrop) {
        int initialNumEggs = eggDrop.getNumEggs();
        for (int i = 1; i < eggDrop.getNumFloors(); i++) {
            eggDrop.tossFromFloor(i);
            if (initialNumEggs != eggDrop.getNumEggs()) {
               return i - 1;
            }
        }
        return 0;
    }

    public static int solveWithLnToNumFloorsTries(EggDrop eggDrop) {
        return binarySearchEggDrop(eggDrop, 1, eggDrop.getNumFloors());
    }

    public static int solveWithLnToFloorTries(EggDrop eggDrop) {
        int maxFloor = exponentialSearchEggDrop(eggDrop);
        if (eggDrop.checkIsRightFloor(maxFloor)) return maxFloor;

        return binarySearchEggDrop(eggDrop, maxFloor/2, maxFloor);
    }

    public static int solveWithTwiceSquareRootToNumFloorsTries(EggDrop eggDrop) {
        int maxFloor = exponentialSearchEggDrop(eggDrop);
        if (eggDrop.checkIsRightFloor(maxFloor)) return maxFloor;

        for (int i = maxFloor/2; i < maxFloor; i++) {
            int numEggsBeforeToss = eggDrop.getNumEggs();
            eggDrop.tossFromFloor(i);
            int numEggsAfterToss = eggDrop.getNumEggs();
            if (numEggsBeforeToss != numEggsAfterToss) {
               return i - 1;
            }
        }

        return -1;
    }

    private static int exponentialSearchEggDrop(EggDrop eggDrop) {
        int start = 1;
        while (true) {
            int numEggsBeforeToss = eggDrop.getNumEggs();
            eggDrop.tossFromFloor(start);
            int numEggsAfterToss = eggDrop.getNumEggs();

            if (numEggsBeforeToss != numEggsAfterToss) {
                return start;
            } else if (eggDrop.checkIsRightFloor(start)) {
                return start;
            } else {
                start *= 2;
            }
        }
    }

    private static int binarySearchEggDrop(EggDrop eggDrop, int start, int end) {
        while (start <= end) {
            int numEggsBeforeToss = eggDrop.getNumEggs();
            int middle = (start + end) / 2;
            eggDrop.tossFromFloor(middle);
            int numEggsAfterToss = eggDrop.getNumEggs();

            if (numEggsBeforeToss != numEggsAfterToss) {
                end = middle - 1;
            } else if (eggDrop.checkIsRightFloor(middle)) {
                return middle;
            } else {
                start = middle + 1;
            }
        }

        return -1;
    }
}
