package hsb.mkss1.ordersystem.usecases;

import hsb.mkss1.ordersystem.entities.ItemFactory;
import hsb.mkss1.ordersystem.entities.LineItemEntity;
import hsb.mkss1.ordersystem.usecases.dtos.LineItemDTO;

public class SimpleItemFactory implements ItemFactory {


    @Override
    public LineItemEntity createLineItem(LineItemDTO lineItem) {
        return new LineItemEntity(lineItem.name(), lineItem.price(), lineItem.quantity());
    }
}
