package hsb.mkss1.order_system.usecases;

import hsb.mkss1.order_system.usecases.dtos.InitializeOrderTemplate;
import hsb.mkss1.order_system.usecases.dtos.ItemDto;
import hsb.mkss1.order_system.usecases.dtos.ItemTemplate;
import hsb.mkss1.order_system.usecases.dtos.OrderDto;

import java.util.List;
import java.util.UUID;

public interface OrderHandler {

    ItemDto addItemToOrder(UUID orderId, ItemTemplate itemTemplate);

    OrderDto finalizeOrder(UUID orderId);

    OrderDto initializeOrder(InitializeOrderTemplate template);

    void removeItemFromOrder(UUID orderId, UUID itemId);

    void deleteOrder(UUID orderId);

    OrderDto getById(UUID orderId);

    List<OrderDto> getAll();
}
