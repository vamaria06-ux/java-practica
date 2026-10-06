package game.hero;

public class Split implements MovingType {
    private static final int SPEED = 5;

    @Override
    public Position move(Position from, int dx, int dy) {
        return from.shift(dx * SPEED, dy * SPEED);
    }
}
