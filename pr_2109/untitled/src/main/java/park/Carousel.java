package park;

public class Carousel extends Attraction {
    public Carousel() {
        super("Карусель", 100, 10, 1, 3, 0, 0);
    }

    @Override
    public String ride(Visitor visitor) {
        return visitor + " кружится на карусели " + getDurationMinutes() + " мин.";
    }
}