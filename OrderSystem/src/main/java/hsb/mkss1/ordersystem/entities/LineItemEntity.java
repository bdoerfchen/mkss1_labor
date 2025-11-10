package hsb.mkss1.ordersystem.entities;

public class LineItemEntity {
    private final String name;
    private final int price;

    private final int quantity;

    public LineItemEntity(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public LineItemEntity createCopy() {
        return new LineItemEntity(getName(), getPrice(), getQuantity());
    }
}
