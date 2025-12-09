package hsb.mkss1.order_system.adapters.cli.reader;

import hsb.mkss1.order_system.adapters.cli.util.Input;
import hsb.mkss1.order_system.usecases.dtos.ItemDto;
import hsb.mkss1.order_system.usecases.dtos.ItemTemplate;
import jakarta.validation.constraints.Null;
import org.springframework.stereotype.Component;

@Component
public class SimpleLineItemReader implements ILineItemReader {

    public ItemTemplate readLineItem() {
        System.out.println("Name: ");
        String name = Input.readString();
        System.out.println("Unit price (in cents): ");
        int unitPrice = Input.readInt();
        System.out.println("Quantity: ");
        int quantity = Input.readInt();
        return new ItemTemplate(name, unitPrice, quantity);
    }

}
