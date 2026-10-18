package park;

public class Main {
    public static void main(String[] args) {
        Park park = new Park("Диво остров");

        Attraction carousel = new Carousel();
        Attraction wheel = new FerrisWheel();
        Attraction coaster = new RollerCoaster();
        park.addAttraction(carousel);
        park.addAttraction(wheel);
        park.addAttraction(coaster);

        Eatery bar = new Bar();
        Eatery cafe = new Cafe();
        Eatery fastFood = new FastFood();
        park.addEatery(bar);
        park.addEatery(cafe);
        park.addEatery(fastFood);

        Mechanic mechanic = new Mechanic("Сергей", 40, 60000);
        park.hire(new Guard("Игорь", 35, 50000));
        park.hire(mechanic);
        park.startWorkDay();

        Visitor anna = new Visitor("Анна", 25, 165, 1500);
        Visitor misha = new Visitor("Миша", 10, 130, 800);
        Visitor oleg = new Visitor("Олег", 30, 180, 100);
        Visitor viktor = new VipVisitor("Виктор", 45, 178, 5000);

        System.out.println("Вход в парк");
        park.admit(anna);
        park.admit(misha);
        park.admit(oleg);
        park.admit(viktor);

        System.out.println("Покупка билетов");
        park.goOnRide(anna, coaster);
        park.goOnRide(misha, coaster);
        park.goOnRide(misha, carousel);
        park.goOnRide(oleg, wheel);
        park.goOnRide(viktor, coaster);
        park.goOnRide(anna, wheel);

        park.runAttractions();

        System.out.println("Еда и здания");
        System.out.println(cafe.serve(anna, "Кофе"));
        System.out.println(bar.serve(misha, "Пиво"));
        System.out.println(bar.serve(viktor, "Коктейль"));
        System.out.println(fastFood.serve(oleg, "Бургер"));
        System.out.println(park.getToilet().use(misha));

        System.out.println("Поломка");
        coaster.setOperational(false);
        park.goOnRide(anna, coaster);
        System.out.println(mechanic.repair(coaster));
        park.goOnRide(anna, coaster);
        park.runAttractions();

        park.printSummary();
    }
}