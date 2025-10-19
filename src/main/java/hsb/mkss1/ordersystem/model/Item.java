package hsb.mkss1.ordersystem.model;

public abstract class Item {
    protected final String name;

    protected Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract int getPrice();
}
