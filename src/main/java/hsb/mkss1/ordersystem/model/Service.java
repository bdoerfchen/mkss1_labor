package hsb.mkss1.ordersystem.model;

public class Service extends Item {

    private final int persons;

    private final int hours;

    private static final int PRICE_PER_PERSON_HOUR = 1242;

    public Service(String name, int persons, int hours) {
        super(name);
        this.persons = persons;
        this.hours = hours;
    }

    public int getPersons() {
        return persons;
    }

    public int getHours() {
        return hours;
    }

    public int getPrice() {
        return PRICE_PER_PERSON_HOUR * hours * persons;
    }

}
