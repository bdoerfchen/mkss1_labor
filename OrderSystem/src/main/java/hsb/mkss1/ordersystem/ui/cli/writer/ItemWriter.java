package hsb.mkss1.ordersystem.ui.cli.writer;

import hsb.mkss1.ordersystem.model.Item;

public interface ItemWriter<T extends Item> {
    void writeItem(T item);
}
