package hsb.mkss1.ordersystem.ui.reader;

import hsb.mkss1.ordersystem.model.dtos.ServiceDTO;
import hsb.mkss1.ordersystem.util.Input;

public class SimpleServiceReader implements IServiceReader {

    public ServiceDTO readService() {
        IO.println("Service type: ");
        String serviceType = Input.readString();
        IO.println("Number of persons: ");
        int persons = Input.readInt();
        IO.println("Hours: ");
        int hours = Input.readInt();
        return new ServiceDTO(serviceType, persons, hours);
    }
}
