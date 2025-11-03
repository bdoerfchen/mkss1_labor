package hsb.mkss1.ordersystem.model;

public abstract class AbstractProduct extends Item {



    protected AbstractProduct(String name) {
        super(name);
    }

    public abstract int getQuantity();
    public abstract int getUnitPrice();

    

}
