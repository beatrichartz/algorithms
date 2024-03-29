package beatrichartz.algorithms.analysis.examples;

/*
Egg drop. Suppose that you have an n-story building (with floors 1 through n) and plenty of eggs. An egg breaks if
it is dropped from floor T or higher and does not break otherwise. Your goal is to devise a strategy to determine the
value of T given the following limitations on the number of eggs and tosses:

    Version 0: 1 egg, greater than or equal to T tosses.
    Version 1: about 1lgn eggs and about 1lgn tosses.
    Version 2: about lgT eggs and about 2lgT tosses.
    Version 3: 2 eggs and about 2n tosses.
    Version 4: 2 eggs and greater than or equal to cT tosses for some fixed constant c
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
        int start = 1;
        while (true) {
            int numEggsBeforeToss = eggDrop.getNumEggs();
            eggDrop.tossFromFloor(start);
            int numEggsAfterToss = eggDrop.getNumEggs();

            if (numEggsBeforeToss != numEggsAfterToss) {
                break;
            } else if (eggDrop.checkIsRightFloor(start)) {
                return start;
            } else {
                start *= 2;
            }
        }

        return binarySearchEggDrop(eggDrop, start/2, start);
    }

    public static int solveWithTwiceSquareRootToNumFloorsTries(EggDrop eggDrop) {
        int start = 1;
        int increase = (int) Math.floor(Math.sqrt((double) eggDrop.getNumFloors()));
        while (true) {
            int numEggsBeforeToss = eggDrop.getNumEggs();
            eggDrop.tossFromFloor(start);
            int numEggsAfterToss = eggDrop.getNumEggs();

            if (numEggsBeforeToss != numEggsAfterToss) {
                break;
            } else if (eggDrop.checkIsRightFloor(start)) {
                return start;
            } else {
                start += increase;
            }
        }

        if (eggDrop.checkIsRightFloor(start)) return start;

        for (int i = start - increase; i < start; i++) {
            int numEggsBeforeToss = eggDrop.getNumEggs();
            eggDrop.tossFromFloor(i);
            int numEggsAfterToss = eggDrop.getNumEggs();
            if (numEggsBeforeToss != numEggsAfterToss) {
               return i - 1;
            }
        }

        return -1;
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

    public static int solveWithConstantTimesSquareRootToFloorTries(EggDrop eggDrop) {
        int start = 1;
        int increase = 1;
        while (true) {
            int numEggsBeforeToss = eggDrop.getNumEggs();
            eggDrop.tossFromFloor(start);
            int numEggsAfterToss = eggDrop.getNumEggs();

            if (numEggsBeforeToss != numEggsAfterToss) {
                break;
            } else if (eggDrop.checkIsRightFloor(start)) {
                return start;
            } else {
                increase += 1;
                start += increase;
            }
        }

        for (int i = start - increase; i <= start; i++) {
            int numEggsBeforeToss = eggDrop.getNumEggs();
            eggDrop.tossFromFloor(i);
            int numEggsAfterToss = eggDrop.getNumEggs();
            if (numEggsBeforeToss != numEggsAfterToss) {
                return i - 1;
            }
        }

        return -1;
    }
}
