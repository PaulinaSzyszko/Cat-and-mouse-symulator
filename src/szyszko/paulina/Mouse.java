package szyszko.paulina;

import java.util.Random;

public class Mouse implements Player {

    int id;

    public Mouse(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Mouse " + id;
    }


    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public Direction move() {
        return Direction.values()[new Random().nextInt(9)];
    }
}
