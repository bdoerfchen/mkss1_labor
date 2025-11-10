package hsb.mkss1.ordersystem.adapters.ui.cli.reader;

import hsb.mkss1.ordersystem.usecases.dtos.LineItemDTO;
import hsb.mkss1.ordersystem.adapters.ui.util.Input;

public class SimpleLineItemReader implements ILineItemReader {

    public LineItemDTO readLineItem() {
        IO.println("Name: ");
        String name = Input.readString();
        IO.println("Unit price (in cents): ");
        int unitPrice = Input.readInt();
        IO.println("Quantity: ");
        int quantity = Input.readInt();
        return new LineItemDTO(name, unitPrice, quantity);
    }

}
