package hsb.mkss1.ordersystem.usecases.mapper;

import hsb.mkss1.ordersystem.entities.OrderEntity;
import hsb.mkss1.ordersystem.usecases.dtos.OrderDTO;

public class OrderMapper {

    private OrderMapper() {

    }

    public static OrderDTO mapEntityToDTO(OrderEntity entity) {
        var lineItemDTOs = entity.getItems()
                .stream()
                .map(LineItemMapper::mapEntityToDTO)
                .toList();

        return new OrderDTO(entity.getId(), lineItemDTOs, entity.getCheckoutTimestamp(), entity.getLumpSum());
    }
}
