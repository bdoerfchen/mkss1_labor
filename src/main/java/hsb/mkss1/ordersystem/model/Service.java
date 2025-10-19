package hsb.mkss1.ordersystem.model;

public record Service(String name, int persons, int hours) {

    private static final int PRICE_PER_PERSON_HOUR = 1242;


    public int getPrice() {
        return PRICE_PER_PERSON_HOUR * hours * persons;
    }

    @Override
    public String toString() {
        return persons + " persons for " + hours + "h of " + name();
    }
}
