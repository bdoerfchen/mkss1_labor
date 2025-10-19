package hsb.mkss1.ordersystem.ui.reader;

import hsb.mkss1.ordersystem.model.Service;
import hsb.mkss1.ordersystem.util.Input;

public class ServiceReader implements ItemReader {
    @Override
    public Service readItem() {
        IO.println("Service type: ");
        String l = Input.readString();
        IO.println("Number of persons: ");
        int p = Input.readInt();
        IO.println("Hours: ");
        int s = Input.readInt();
        return new Service(l, p, s) ;
    }

    @Override
    public String getPromptText() {
        return "Order service";
    }
}
