package hsb.mkss1.ordersystem.ui.writer;

import hsb.mkss1.ordersystem.model.Service;
import hsb.mkss1.ordersystem.util.StringFormatterUtil;

public class ServiceWriter implements ItemWriter<Service> {

    @Override
    public void writeItem(Service service) {
        IO.println(service.getPersons() + " persons for " + service.getHours() + "h of " + service.getName());
        IO.println(" = " + StringFormatterUtil.formatPrice(service.getPrice()));
    }
}
