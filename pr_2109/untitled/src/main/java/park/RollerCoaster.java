package park;

public class RollerCoaster extends Attraction {
    public RollerCoaster() {
        super("Американские горки", 300, 8, 9, 4, 12, 140);
    }

    @Override
    public String ride(Visitor visitor) {
        return visitor + " летит на американских горках " + getDurationMinutes() + " мин.";
    }
}