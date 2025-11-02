package hsb.mkss1.ordersystem.ui.cli.reader;

import hsb.mkss1.ordersystem.model.dtos.ProductDTO;
import hsb.mkss1.ordersystem.util.Input;

public class SimpleProductReader implements  IProductReader{

    public ProductDTO readProduct() {
        IO.println("Name: ");
        String name = Input.readString();
        IO.println("Unit price (in cents): ");
        int unitPrice = Input.readInt();
        IO.println("Quantity: ");
        int quantity = Input.readInt();
        return new ProductDTO(name, unitPrice, quantity);
    }

}
