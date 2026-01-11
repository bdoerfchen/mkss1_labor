package hsb.mkss1.order_system.usecases;



import de.hsbremen.mkss.events.CrudEventProducer;
import hsb.mkss1.order_system.entities.ItemRepo;
import hsb.mkss1.order_system.entities.Order;
import hsb.mkss1.order_system.entities.OrderRepo;
import hsb.mkss1.order_system.entities.OrderStatusEnum;
import de.hsbremen.mkss.shared.dtos.InitializeOrderTemplate;
import de.hsbremen.mkss.shared.dtos.ItemDto;
import de.hsbremen.mkss.shared.dtos.ItemTemplate;
import de.hsbremen.mkss.shared.dtos.OrderDto;
import hsb.mkss1.order_system.usecases.mapper.ItemMapper;
import hsb.mkss1.order_system.usecases.mapper.OrderMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService implements OrderHandler {

    private final OrderRepo orderRepo;
    private final ItemRepo itemRepo;

    private final CrudEventProducer<OrderDto> eventProducer;


    @Override
    @Transactional
    public ItemDto addItemToOrder(UUID orderId, ItemTemplate itemTemplate) {
        var optionalEntity = orderRepo.findById(orderId);

        if (optionalEntity.isEmpty()) {
            throw new NoSuchElementException("Order with id " + orderId + " not found");
        }

        var entity = optionalEntity.get();

        if (isNotModifiable(entity)) {
            throw new IllegalStateException("Order no more modifiable");
        }

        var item = ItemMapper.mapTemplateToEntity(itemTemplate);
        item.setOrder(entity);
        itemRepo.save(item);

        entity.setStatus(OrderStatusEnum.IN_PREPARATION);
        orderRepo.save(entity);

        return ItemMapper.mapEntityToDTO(item);
    }

    @Override
    @Transactional
    public OrderDto finalizeOrder(UUID orderId) {
        var optionalEntity = orderRepo.findById(orderId);

        if (optionalEntity.isEmpty()) {
            throw new NoSuchElementException("Order with id " + orderId + " not found");
        }

        var entity = optionalEntity.get();

        if (entity.getStatus() != OrderStatusEnum.IN_PREPARATION) {
            throw new IllegalStateException("Order not in preparation status");
        }

        entity.setCheckoutTimestamp(LocalDateTime.now());
        entity.setStatus(OrderStatusEnum.COMMITED);

        orderRepo.save(entity);

        var dto = OrderMapper.mapEntityToDTO(entity);
        eventProducer.emitCreateEvent(dto);

        return dto;
    }

    @Override
    public void acceptOrder(UUID orderId) {
        var optionalEntity = orderRepo.findById(orderId);
        if (optionalEntity.isEmpty()) {
            throw new NoSuchElementException("Order with id " + orderId + " not found");
        }

        var entity = optionalEntity.get();
        if (entity.getStatus() == OrderStatusEnum.ACCEPTED) {
            return;
        }
        if (entity.getStatus() != OrderStatusEnum.COMMITED) {
            throw new IllegalStateException("Order not in commited status");
        }

        entity.setStatus(OrderStatusEnum.ACCEPTED);

        orderRepo.save(entity);
    }
    @Override
    public void rejectOrder(UUID orderId) {
        var optionalEntity = orderRepo.findById(orderId);
        if (optionalEntity.isEmpty()) {
            throw new NoSuchElementException("Order with id " + orderId + " not found");
        }

        var entity = optionalEntity.get();
        if (entity.getStatus() == OrderStatusEnum.REJECTED) {
            return;
        }
        if (entity.getStatus() != OrderStatusEnum.COMMITED) {
            throw new IllegalStateException("Order not in commited status");
        }

        entity.setStatus(OrderStatusEnum.REJECTED);

        orderRepo.save(entity);
    }

    @Override
    public OrderDto initializeOrder(InitializeOrderTemplate template) {
        var order = new Order();
        order.setCustomerName(template.getCustomerName());
        order.setStatus(OrderStatusEnum.EMPTY);
        orderRepo.save(order);
        return OrderMapper.mapEntityToDTO(order);
    }

    @Override
    public void removeItemFromOrder(UUID orderId, UUID itemId) {
        var optionalOrder = orderRepo.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new NoSuchElementException("Order with id " + orderId + " not found");
        }
        var order = optionalOrder.get();

        if (order.getStatus() != OrderStatusEnum.IN_PREPARATION) {
            throw new IllegalStateException("Order not in preparation status");
        }

        var optionalItem = itemRepo.findById(itemId);
        if (optionalItem.isEmpty()) {
            throw new NoSuchElementException("Item with id " + itemId + " not found");
        }
        var item = optionalItem.get();

        if(!item.getOrder().getId().equals(order.getId())) {
            throw new IllegalArgumentException("Item with id " + orderId +
                    " is not an item of the order with id " + order.getId());
        }

        itemRepo.deleteById(itemId);

        orderRepo.findById(orderId)
                .ifPresent(order1 -> {
                    if (order1.getItems().isEmpty()) {
                        order1.setStatus(OrderStatusEnum.EMPTY);
                        orderRepo.save(order1);
                    }
                });

    }

    @Override
    public void deleteOrder(UUID orderId) {
        var optionalOrder = orderRepo.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new NoSuchElementException("Order with id " + orderId + " not found");
        }
        var order = optionalOrder.get();

        orderRepo.delete(order);
    }

    @Override
    public OrderDto getById(UUID orderId) {
        var optionalEntity = orderRepo.findById(orderId);

        if (optionalEntity.isEmpty()) {
            throw new NoSuchElementException("Order with id " + orderId + " not found");
        }

        var entity = optionalEntity.get();
        return OrderMapper.mapEntityToDTO(entity);
    }

    @Override
    public List<OrderDto> getAll() {
        return orderRepo.findAll()
                .stream()
                .map(OrderMapper::mapEntityToDTO)
                .toList();
    }

    private static boolean isNotModifiable(Order order) {
        return order.getStatus() != OrderStatusEnum.EMPTY && order.getStatus() != OrderStatusEnum.IN_PREPARATION;
    }
}
