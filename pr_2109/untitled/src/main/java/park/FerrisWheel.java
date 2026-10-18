package park;

public class FerrisWheel extends Attraction {
    public FerrisWheel() {
        super("Колесо обозрения", 150, 20, 2, 10, 0, 0);
    }

    @Override
    public String ride(Visitor visitor) {
        return visitor + " любуется видом с колеса обозрения " + getDurationMinutes() + " мин.";
    }
}