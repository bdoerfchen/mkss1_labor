package hsb.mkss1.ordersystem.ui.reader;

import hsb.mkss1.ordersystem.model.Product;
import hsb.mkss1.ordersystem.util.Input;

public class ProductReader implements ItemReader {
    @Override
    public Product readItem() {
        IO.println("Name: ");
        String name = Input.readString();
        IO.println("Unit price (in cents): ");
        int unitPrice = Input.readInt();
        IO.println("Quantity: ");
        int quantity = Input.readInt();
        return new Product(name, unitPrice, quantity);
    }

    @Override
    public String getPromptText() {
        return "Order product";
    }
}
