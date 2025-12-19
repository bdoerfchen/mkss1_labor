package hsb.mkss1.order_system.usecases;

import de.hsbremen.mkss.shared.dtos.InitializeOrderTemplate;
import de.hsbremen.mkss.shared.dtos.ItemDto;
import de.hsbremen.mkss.shared.dtos.ItemTemplate;
import de.hsbremen.mkss.shared.dtos.OrderDto;

import java.util.List;
import java.util.UUID;

public interface OrderHandler {

    ItemDto addItemToOrder(UUID orderId, ItemTemplate itemTemplate);

    OrderDto finalizeOrder(UUID orderId);
    void acceptOrder(UUID orderId);
    void rejectOrder(UUID orderId);

    OrderDto initializeOrder(InitializeOrderTemplate template);

    void removeItemFromOrder(UUID orderId, UUID itemId);

    void deleteOrder(UUID orderId);

    OrderDto getById(UUID orderId);

    List<OrderDto> getAll();
}
