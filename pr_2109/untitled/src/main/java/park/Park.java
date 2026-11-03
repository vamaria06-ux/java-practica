package park;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Park {
    private final String name;
    private final List<Attraction> attractions = new ArrayList<>();
    private final List<Eatery> eateries = new ArrayList<>();
    private final List<Employee> staff = new ArrayList<>();
    private final List<Visitor> visitors = new ArrayList<>();

    private final TicketOffice ticketOffice = new TicketOffice();
    private final Toilet toilet = new Toilet();
    private final FirstAidPost firstAidPost = new FirstAidPost();

    private final Random random = new Random(42);

    public Park(String name) {
        this.name = name;
    }

    public void addAttraction(Attraction attraction) {
        attractions.add(attraction);
    }

    public void addEatery(Eatery eatery) {
        eateries.add(eatery);
    }

    public void hire(Employee employee) {
        staff.add(employee);
    }

    public void admit(Visitor visitor) {
        visitors.add(visitor);
        System.out.println(visitor + " вошёл(а) в парк «" + name + "»");
    }

    public Toilet getToilet() {
        return toilet;
    }

    public TicketOffice getTicketOffice() {
        return ticketOffice;
    }

    public void startWorkDay() {
        System.out.println("Рабочий день");
        for (Employee e : staff) {
            System.out.println(e.work());
        }
    }

    public void goOnRide(Visitor visitor, Attraction attraction) {
        String denied = attraction.checkAccess(visitor);
        if (denied != null) {
            System.out.println(visitor + " не допущен(а) на «" + attraction + "»: " + denied);
            return;
        }
        Ticket ticket = ticketOffice.sell(visitor, attraction);
        if (ticket == null) {
            double price = ticketOffice.priceFor(visitor, attraction);
            System.out.println(visitor + " не хватает денег на билет «" + attraction
                    + "» (" + (int) price + " руб.)");
            return;
        }
        System.out.println(visitor + " купил(а) билет на «" + attraction + "» за "
                + (int) ticket.price() + " руб. и встал(а) в очередь");
        attraction.enqueue(visitor);
    }

    public void runAttractions() {
        System.out.println("Запуск аттракционов");
        for (Attraction attraction : attractions) {
            List<Visitor> group = attraction.takeGroup();
            if (group.isEmpty()) {
                continue;
            }
            System.out.println("Запуск «" + attraction + "», пассажиров: " + group.size());
            for (Visitor visitor : group) {
                visitor.useTicket(attraction);
                System.out.println("  " + attraction.ride(visitor));
                if (random.nextDouble() < attraction.getExtremeness() * 0.04) {
                    System.out.println("  " + firstAidPost.treat(visitor));
                }
            }
        }
    }

    public void printSummary() {
        System.out.println("Итоги дня");
        System.out.println("Выручка касс: " + (int) ticketOffice.getRevenue() + " руб.");
        System.out.println("Обращений в травмпункт: " + firstAidPost.getTreated());
        System.out.println("Посещений туалета: " + toilet.getVisits());
        for (Visitor v : visitors) {
            System.out.println(v + ": осталось " + (int) v.getMoney() + " руб.");
        }
    }
}