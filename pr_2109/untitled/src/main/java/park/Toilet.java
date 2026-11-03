package park;

public class Toilet extends Building {
    private int visits;

    public Toilet() {
        super("Туалет");
    }

    public String use(Visitor visitor) {
        visits++;
        return visitor + " посетил(а) туалет";
    }

    public int getVisits() {
        return visits;
    }
}