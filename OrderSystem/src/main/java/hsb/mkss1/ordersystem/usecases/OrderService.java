package hsb.mkss1.ordersystem.usecases;

import hsb.mkss1.ordersystem.entities.ItemFactory;
import hsb.mkss1.ordersystem.entities.OrderEntity;
import hsb.mkss1.ordersystem.entities.OrderRepo;
import hsb.mkss1.ordersystem.usecases.dtos.LineItemDTO;
import hsb.mkss1.ordersystem.usecases.dtos.OrderDTO;
import hsb.mkss1.ordersystem.usecases.mapper.OrderMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class OrderService implements OrderHandler {

    private final OrderRepo repo;
    private final ItemFactory itemFactory;
    public OrderService(OrderRepo repo, ItemFactory itemFactory) {
        this.repo = repo;
        this.itemFactory = itemFactory;
    }

    @Override
    public void addItemToOrder(int orderId, LineItemDTO lineItemDTO, Consumer<Optional<OrderDTO>> consumer) {
        var optionalEntity = repo.findById(orderId);

        if (optionalEntity.isEmpty()) {
            consumer.accept(Optional.empty());
            return;
        }

        var entity = optionalEntity.get();
        entity.addItem(itemFactory.createLineItem(lineItemDTO));
        repo.save(entity);

        var dto = OrderMapper.mapEntityToDTO(entity);
        consumer.accept(Optional.of(dto));

    }

    @Override
    public void finalizeOrder(int orderId, Consumer<Optional<OrderDTO>> consumer) {
        var optionalEntity = repo.findById(orderId);

        if (optionalEntity.isEmpty()) {
            consumer.accept(Optional.empty());
            return;
        }

        var entity = optionalEntity.get();

        entity.setCheckoutTimestamp(LocalDateTime.now());
        repo.save(entity);

        var dto = OrderMapper.mapEntityToDTO(entity);
        consumer.accept(Optional.of(dto));
    }

    @Override
    public void initializeOrder(Consumer<OrderDTO> consumer) {
        var entity = new OrderEntity();
        repo.save(entity);
        var dto = OrderMapper.mapEntityToDTO(entity);
        consumer.accept(dto);
    }

    @Override
    public void getAll(Consumer<List<OrderDTO>> consumer) {
        var dtos = repo.findAll()
                .stream()
                .map(OrderMapper::mapEntityToDTO)
                .toList();

        consumer.accept(dtos);
    }
}
