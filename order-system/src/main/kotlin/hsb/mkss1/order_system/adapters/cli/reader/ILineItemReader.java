package hsb.mkss1.order_system.adapters.cli.reader;

import hsb.mkss1.order_system.usecases.dtos.ItemTemplate;

public interface ILineItemReader {

    ItemTemplate readLineItem();

}
