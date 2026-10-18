package park;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Visitor extends Person {
    private final int height;
    private double money;
    private final List<Ticket> tickets = new ArrayList<>();

    public Visitor(String name, int age, int height, double money) {
        super(name, age);
        this.height = height;
        this.money = money;
    }

    public int getHeight() {
        return height;
    }

    public double getMoney() {
        return money;
    }

    public double getDiscount() {
        return 0.0;
    }

    public boolean isVip() {
        return false;
    }

    public boolean pay(double amount) {
        if (amount > money) {
            return false;
        }
        money -= amount;
        return true;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public boolean useTicket(Attraction attraction) {
        Iterator<Ticket> it = tickets.iterator();
        while (it.hasNext()) {
            if (it.next().attraction() == attraction) {
                it.remove();
                return true;
            }
        }
        return false;
    }
}