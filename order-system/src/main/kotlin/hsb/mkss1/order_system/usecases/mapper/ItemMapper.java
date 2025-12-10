package hsb.mkss1.order_system.usecases.mapper;

import hsb.mkss1.order_system.entities.Item;
import hsb.mkss1.order_system.usecases.dtos.ItemDto;
import hsb.mkss1.order_system.usecases.dtos.ItemTemplate;

import java.util.UUID;

public class ItemMapper {

    private ItemMapper() {

    }

    public static ItemDto mapEntityToDTO(Item entity) {
        return new ItemDto(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getQuantity()
        );
    }


    public static Item mapDtoToEntity(ItemDto dto) {
        return new Item(dto.getId(),
                null ,
                dto.getName(),
                dto.getPrice(),
                dto.getQuantity() );
    }

    public static Item mapTemplateToEntity(ItemTemplate template) {
        return new Item(null,
                null ,
                template.getName(),
                template.getPrice(),
                template.getQuantity() );
    }

}
