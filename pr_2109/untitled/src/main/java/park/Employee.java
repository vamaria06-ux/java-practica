package park;

public abstract class Employee extends Person {
    private final String position;
    private final double salary;

    protected Employee(String name, int age, String position, double salary) {
        super(name, age);
        this.position = position;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public abstract String work();
}