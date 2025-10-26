package hsb.mkss1.ordersystem.model;

public class SimpleProduct extends AbstractProduct {



    private final int unitPrice;

    private final int quantity;


    public SimpleProduct(String name, int unitPrice, int quantity) {
        super(name);
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }


    @Override
    public int getPrice() {
        return unitPrice * quantity;
    }

    @Override
    public int getQuantity()
    {
        return quantity;
    }
}



