package game.hero;

public interface MovingType {
    Position move(Position from, int dx, int dy);
}
