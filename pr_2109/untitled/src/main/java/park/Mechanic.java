package park;

public class Mechanic extends Employee {
    public Mechanic(String name, int age, double salary) {
        super(name, age, "Механик", salary);
    }

    public String repair(Attraction attraction) {
        attraction.setOperational(true);
        return getPosition() + " " + getName() + " починил(а) аттракцион «" + attraction + "»";
    }

    @Override
    public String work() {
        return getPosition() + " " + getName() + " проверяет аттракционы";
    }
}