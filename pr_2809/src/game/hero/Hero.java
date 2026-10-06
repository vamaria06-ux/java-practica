package game.hero;

public class Hero {
    private Position position;
    private MovingType moving;

    public Hero(Position start, MovingType moving) {
        this.position = start;
        this.moving = moving;
    }
    public void setMoving(MovingType moving) {
        this.moving = moving;
    }
    public void move(int dx, int dy) {
        position = moving.move(position, dx, dy);
    }
    public Position getPosition() {
        return position;
    }
}
