package hsb.mkss1.ordersystem.ui.reader;

import hsb.mkss1.ordersystem.model.Product;
import hsb.mkss1.ordersystem.util.Input;

public class ProductReader implements ItemReader {
    @Override
    public Product readItem() {
        IO.println("Name: ");
        String l = Input.readString();
        IO.println("Unit price (in cents): ");
        int p = Input.readInt();
        IO.println("Quantity: ");
        int s = Input.readInt();
        return new Product(l, p, s) ;
    }

    @Override
    public String getPromptText() {
        return "Order product";
    }
}
