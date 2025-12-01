package hsb.mkss1.order_system.usecases.mapper;

import hsb.mkss1.order_system.entities.Item;
import hsb.mkss1.order_system.entities.Order;
import hsb.mkss1.order_system.usecases.dtos.ItemDto;
import hsb.mkss1.order_system.usecases.dtos.OrderDto;

import java.util.ArrayList;

public class OrderMapper {



    private OrderMapper() {

    }

    public static OrderDto mapEntityToDTO(Order entity) {
        var lineItemDTOs = entity.getItems()
                .stream()
                .map(ItemMapper::mapEntityToDTO)
                .toList();

        return new OrderDto(entity.getId(), lineItemDTOs, entity.getLumpSum(), entity.getCheckoutTimestamp(), entity.getCustomerName());
    }


    public static Order mapDtoToEntity(OrderDto dto) {
        return new Order(dto.getId(),
                new ArrayList<>(),
                dto.getCheckoutTimestamp(),
                dto.getLumpSum(),
                dto.getCustomerName());
    }
}
