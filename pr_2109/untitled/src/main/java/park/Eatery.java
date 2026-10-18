package park;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Eatery {
    private final String name;
    private final int minAge;
    private final Map<String, Double> menu = new LinkedHashMap<>();

    protected Eatery(String name, int minAge) {
        this.name = name;
        this.minAge = minAge;
    }

    protected void addItem(String item, double price) {
        menu.put(item, price);
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getMenu() {
        return menu;
    }

    public String serve(Visitor visitor, String item) {
        if (visitor.getAge() < minAge) {
            return name + ": " + visitor + " не обслуживается (нужен возраст от " + minAge + " лет)";
        }
        Double price = menu.get(item);
        if (price == null) {
            return name + ": в меню нет «" + item + "»";
        }
        if (!visitor.pay(price)) {
            return name + ": " + visitor + " не хватает денег на «" + item + "»";
        }
        return name + ": " + visitor + " купил(а) «" + item + "» за " + (int) price.doubleValue() + " руб.";
    }
}