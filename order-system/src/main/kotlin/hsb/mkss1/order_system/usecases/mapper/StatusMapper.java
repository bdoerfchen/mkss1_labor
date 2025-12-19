package hsb.mkss1.order_system.usecases.mapper;

import de.hsbremen.mkss.shared.dtos.OrderStatusEnumDto;
import hsb.mkss1.order_system.entities.OrderStatusEnum;

public class StatusMapper {

    private StatusMapper() {


    }

    public static OrderStatusEnumDto mapEntityToDTO(OrderStatusEnum entity) {
        return switch (entity) {
            case EMPTY ->  OrderStatusEnumDto.EMPTY;
            case IN_PREPARATION ->  OrderStatusEnumDto.IN_PREPARATION;
            case COMMITED ->  OrderStatusEnumDto.COMMITED;
            case REJECTED ->  OrderStatusEnumDto.REJECTED;
            case ACCEPTED ->  OrderStatusEnumDto.ACCEPTED;
        };
    }

    public static OrderStatusEnum mapDtoToEntity(OrderStatusEnumDto dto) {
        return switch (dto) {
            case EMPTY ->  OrderStatusEnum.EMPTY;
            case IN_PREPARATION ->  OrderStatusEnum.IN_PREPARATION;
            case COMMITED ->  OrderStatusEnum.COMMITED;
            case REJECTED ->  OrderStatusEnum.REJECTED;
            case ACCEPTED ->  OrderStatusEnum.ACCEPTED;
        };
    }
}
