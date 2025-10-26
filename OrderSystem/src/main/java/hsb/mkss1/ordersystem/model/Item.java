package hsb.mkss1.ordersystem.model;

public abstract class Item {
    private final String name;

    protected Item(String name) {
        this.name = name;
    }

    public abstract int getPrice();

    public String getName() {
        return name;
    }
}
