package game.hero;

public class Walk  implements MovingType{
    private static final int SPEED = 1;

    @Override
    public Position move(Position from, int dx, int dy) {
        return from.shift(dx * SPEED, dy * SPEED);
    }
}
