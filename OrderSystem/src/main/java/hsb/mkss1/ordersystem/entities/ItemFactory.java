package hsb.mkss1.ordersystem.entities;

import hsb.mkss1.ordersystem.usecases.dtos.LineItemDTO;

public interface ItemFactory {

    LineItemEntity createLineItem(LineItemDTO lineItem);
}
