package hsb.mkss1.ordersystem.ui.writer;

import hsb.mkss1.ordersystem.model.Item;
import hsb.mkss1.ordersystem.model.SimpleProduct;
import hsb.mkss1.ordersystem.model.SimpleService;

public interface ItemWriter<T extends Item> {
    String writeItem(T item);
}


