package park;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public abstract class Attraction {
    private final String name;
    private final double cost;
    private final int capacity;
    private final int extremeness;
    private final int durationMinutes;
    private final int minAge;
    private final int minHeight;
    private boolean operational = true;

    private final Deque<Visitor> vipQueue = new ArrayDeque<>();
    private final Deque<Visitor> queue = new ArrayDeque<>();

    protected Attraction(String name, double cost, int capacity, int extremeness,
                         int durationMinutes, int minAge, int minHeight) {
        this.name = name;
        this.cost = cost;
        this.capacity = capacity;
        this.extremeness = extremeness;
        this.durationMinutes = durationMinutes;
        this.minAge = minAge;
        this.minHeight = minHeight;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getExtremeness() {
        return extremeness;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public boolean isOperational() {
        return operational;
    }

    public void setOperational(boolean operational) {
        this.operational = operational;
    }

    public String checkAccess(Visitor visitor) {
        if (!operational) {
            return "аттракцион не работает";
        }
        if (visitor.getAge() < minAge) {
            return "нужен возраст от " + minAge + " лет";
        }
        if (visitor.getHeight() < minHeight) {
            return "нужен рост от " + minHeight + " см";
        }
        return null;
    }

    public void enqueue(Visitor visitor) {
        if (visitor.isVip()) {
            vipQueue.add(visitor);
        } else {
            queue.add(visitor);
        }
    }

    public List<Visitor> takeGroup() {
        List<Visitor> group = new ArrayList<>();
        while (group.size() < capacity && (!vipQueue.isEmpty() || !queue.isEmpty())) {
            group.add(!vipQueue.isEmpty() ? vipQueue.poll() : queue.poll());
        }
        return group;
    }

    public abstract String ride(Visitor visitor);

    @Override
    public String toString() {
        return name;
    }
}