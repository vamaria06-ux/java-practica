package park;

public class Cafe extends Eatery {
    public Cafe() {
        super("Кафе", 0);
        addItem("Кофе", 150);
        addItem("Чай", 100);
        addItem("Пирожное", 180);
    }
}