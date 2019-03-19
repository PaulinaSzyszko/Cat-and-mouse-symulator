package szyszko.paulina;

public class Cheese implements Player {

    @Override
    public String toString() {
        return "Cheese";
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public Direction move() {
        return null;
    }
}
