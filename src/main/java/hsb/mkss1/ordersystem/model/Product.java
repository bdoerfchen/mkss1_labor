package hsb.mkss1.ordersystem.model;

public class Product extends Item {

    private final int unitPrice;

    private final int quantity;

    public Product(String name, int unitPrice, int quantity) {
        super(name);
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return unitPrice * quantity;
    }

}
