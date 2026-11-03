package park;

public class Guard extends Employee {
    public Guard(String name, int age, double salary) {
        super(name, age, "Охранник", salary);
    }

    @Override
    public String work() {
        return getPosition() + " " + getName() + " патрулирует территорию парка";
    }
}