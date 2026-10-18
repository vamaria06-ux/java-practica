package park;

public class Bar extends Eatery {
    public Bar() {
        super("Бар", 18);
        addItem("Пиво", 250);
        addItem("Коктейль", 400);
        addItem("Сок", 150);
    }
}