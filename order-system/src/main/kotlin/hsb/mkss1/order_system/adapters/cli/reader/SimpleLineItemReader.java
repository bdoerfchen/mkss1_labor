package hsb.mkss1.order_system.adapters.cli.reader;

import hsb.mkss1.order_system.adapters.cli.util.Input;
import de.hsbremen.mkss.shared.dtos.ItemTemplate;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("java:S106") // Requirement to use System.out in CLI since it is a command line user interface
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
