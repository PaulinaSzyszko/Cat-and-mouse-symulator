package szyszko.paulina;

import java.util.Random;

public class Cat implements Player {

    private int id;

    public Cat(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cat " + id;
    }

    @Override
    public boolean canMove() {
        return true;
    }

    @Override
    public Direction move() {
        return Direction.values()[new Random().nextInt(5)];
    }
}
