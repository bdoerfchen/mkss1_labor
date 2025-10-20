package hsb.mkss1.ordersystem.ui.reader;

import hsb.mkss1.ordersystem.model.Service;
import hsb.mkss1.ordersystem.util.Input;

public class ServiceReader implements ItemReader {
    @Override
    public Service readItem() {
        IO.println("Service type: ");
        String serviceType = Input.readString();
        IO.println("Number of persons: ");
        int persons = Input.readInt();
        IO.println("Hours: ");
        int hours = Input.readInt();
        return new Service(serviceType, persons, hours);
    }

    @Override
    public String getPromptText() {
        return "Order service";
    }
}
