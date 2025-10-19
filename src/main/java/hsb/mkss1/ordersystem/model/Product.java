package hsb.mkss1.ordersystem.model;

public record Product(String name, int unitPrice, int quantity) {


    public int getPrice() {
        return unitPrice * quantity;
    }

    @Override
    public String toString() {
        return quantity + " * " + name();
    }
}
