package hsb.mkss1.ordersystem.ui.reader;

import hsb.mkss1.ordersystem.model.Item;

public interface ItemReader {
    Item readItem();
    String getPromptText();
}
