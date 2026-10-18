package park;

public class TicketOffice extends Building {
    private double revenue;

    public TicketOffice() {
        super("Кассы");
    }

    public double priceFor(Visitor visitor, Attraction attraction) {
        return attraction.getCost() * (1 - visitor.getDiscount());
    }

    public Ticket sell(Visitor visitor, Attraction attraction) {
        double price = priceFor(visitor, attraction);
        if (!visitor.pay(price)) {
            return null;
        }
        revenue += price;
        Ticket ticket = new Ticket(attraction, price);
        visitor.addTicket(ticket);
        return ticket;
    }

    public double getRevenue() {
        return revenue;
    }
}