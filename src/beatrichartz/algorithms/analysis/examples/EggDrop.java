package beatrichartz.algorithms.analysis.examples;

public class EggDrop {
    private boolean hasCheckedFloor = false;
    private int numEggs;
    private final int floor;
    private int numFloors;
    private int numTosses;

    public EggDrop(int numEggs, int numFloors, int floor) {
        this.numEggs = numEggs;
        this.numFloors = numFloors;
        this.floor = floor;
    }

    public void tossFromFloor(int floor) {
        if (numEggs <= 0) throw new RuntimeException("You are out of eggs to toss");
        if (floor > this.numFloors) throw new RuntimeException("Can not toss from above the building");

        numTosses++;
        if (floor > this.floor) numEggs--;
    }

    public int getNumEggs() {
        return numEggs;
    }

    public int getNumFloors() {
        return numFloors;
    }

    public boolean checkIsRightFloor(int floor) {
        if (hasCheckedFloor && numEggs == 0) throw new RuntimeException("Game Over");

        hasCheckedFloor = numEggs == 0;
        return this.floor == floor;
    }

    public int getNumTosses() {
        return numTosses;
    }
}
