package szyszko.paulina;

public enum Direction {

    LEFT(0, -1), TOP(-1, 0), RIGHT(0, 1), BOTTON(1, 0), TOP_LEFT(-1, -1), TOP_RIGHT(-1, 1), BOTTOM_LEFT(1, -1), BOTTOM_RIGHT(1, 1), STAY(0,0);

    int firstDirection;
    int secondDirection;


    Direction(int firstDirection, int secondDirection) {
        this.firstDirection = firstDirection;
        this.secondDirection = secondDirection;
    }

    public int getFirstDirection() {
        return firstDirection;
    }

    public int getSecondDirection() {
        return secondDirection;
    }

}
