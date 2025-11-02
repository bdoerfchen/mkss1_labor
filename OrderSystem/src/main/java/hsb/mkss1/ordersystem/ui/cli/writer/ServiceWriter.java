package hsb.mkss1.ordersystem.ui.cli.writer;

import hsb.mkss1.ordersystem.model.AbstractService;
import hsb.mkss1.ordersystem.util.StringFormatterUtil;

public class ServiceWriter implements ItemWriter<AbstractService> {

    @Override
    public void writeItem(AbstractService service) {
        IO.print(service.getPersons() + " persons for " + service.getHours() + "h of " + service.getName());
        IO.println(" = " + StringFormatterUtil.formatPrice(service.getPrice()));
    }
}
