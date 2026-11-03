package game.hero;

public class Fly implements MovingType {
    private static final  int SPEED = 3;

    @Override
    public Position move(Position from, int dx, int dy) {
        return from.shift(dx * SPEED, dy * SPEED);
    }
}
