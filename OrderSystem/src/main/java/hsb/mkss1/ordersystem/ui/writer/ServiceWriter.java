package hsb.mkss1.ordersystem.ui.writer;

import hsb.mkss1.ordersystem.model.AbstractService;
import hsb.mkss1.ordersystem.util.StringFormatterUtil;

public class ServiceWriter implements ItemWriter<AbstractService> {

    @Override
    public String writeItem(AbstractService service) {
        return "%15s - %s (%dh x %d Persons)".formatted(
                StringFormatterUtil.formatPrice(service.getPrice()),
                service.getName(),
                service.getHours(),
                service.getPersons()
        );
    }
}
