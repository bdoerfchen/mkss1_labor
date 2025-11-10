package hsb.mkss1.ordersystem.usecases.mapper;

import hsb.mkss1.ordersystem.entities.LineItemEntity;
import hsb.mkss1.ordersystem.usecases.dtos.LineItemDTO;

public class LineItemMapper {

    private LineItemMapper() {

    }

    public static LineItemDTO mapEntityToDTO(LineItemEntity entity) {
        return new LineItemDTO(
                entity.getName(),
                entity.getPrice(),
                entity.getQuantity()
        );
    }

}
