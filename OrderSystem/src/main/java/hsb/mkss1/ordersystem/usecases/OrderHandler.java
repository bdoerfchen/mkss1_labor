package hsb.mkss1.ordersystem.usecases;

import hsb.mkss1.ordersystem.usecases.dtos.LineItemDTO;
import hsb.mkss1.ordersystem.usecases.dtos.OrderDTO;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface OrderHandler {

    void addItemToOrder(int orderId, LineItemDTO lineItemDTO, Consumer<Optional<OrderDTO>> consumer);

    void finalizeOrder(int orderId, Consumer<Optional<OrderDTO>> consumer);

    void initializeOrder(Consumer<OrderDTO> consumer);

    void getAll(Consumer<List<OrderDTO>> consumer);
}
