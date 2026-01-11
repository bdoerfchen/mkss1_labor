package hsb.mkss1.order_system.usecases.mapper;

import hsb.mkss1.order_system.entities.Order;
import de.hsbremen.mkss.shared.dtos.OrderDto;

import java.util.ArrayList;

public class OrderMapper {



    private OrderMapper() {

    }

    public static OrderDto mapEntityToDTO(Order entity) {
        var lineItemDTOs = entity.getItems()
                .stream()
                .map(ItemMapper::mapEntityToDTO)
                .toList();

        var lumpSum = lineItemDTOs.stream()
                .mapToInt(item -> item.getPrice()*item.getQuantity())
                .sum();

        return new OrderDto(entity.getId(),
                lineItemDTOs,
                lumpSum,
                entity.getCheckoutTimestamp(),
                StatusMapper.mapEntityToDTO(entity.getStatus()),
                entity.getCustomerName()
        );
    }


    public static Order mapDtoToEntity(OrderDto dto) {
        return new Order(dto.getId(),
                StatusMapper.mapDtoToEntity(dto.getStatus()),
                new ArrayList<>(),
                dto.getCheckoutTimestamp(),
                dto.getCustomerName()
        );
    }
}
