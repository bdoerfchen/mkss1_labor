package hsb.mkss1.order_system.usecases;



import hsb.mkss1.order_system.entities.ItemRepo;
import hsb.mkss1.order_system.entities.Order;
import hsb.mkss1.order_system.entities.OrderRepo;
import hsb.mkss1.order_system.usecases.dtos.InitializeOrderTemplate;
import hsb.mkss1.order_system.usecases.dtos.ItemDto;
import hsb.mkss1.order_system.usecases.dtos.ItemTemplate;
import hsb.mkss1.order_system.usecases.dtos.OrderDto;
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


    @Override
    @Transactional
    public ItemDto addItemToOrder(UUID orderId, ItemTemplate itemTemplate) {
        var optionalEntity = orderRepo.findById(orderId);

        if (optionalEntity.isEmpty()) {
            throw new NoSuchElementException("Order with id " + orderId + " not found");
        }

        var entity = optionalEntity.get();

        var item = ItemMapper.mapTemplateToEntity(itemTemplate);
        item.setOrder(entity);
        itemRepo.save(item);
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

        entity.setCheckoutTimestamp(LocalDateTime.now());
        orderRepo.save(entity);
        return OrderMapper.mapEntityToDTO(entity);
    }

    @Override
    public OrderDto initializeOrder(InitializeOrderTemplate template) {
        var order = new Order();
        order.setCustomerName(template.getCustomerName());
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

        var optionalItem = itemRepo.findById(itemId);
        if (optionalItem.isEmpty()) {
            throw new NoSuchElementException("Item with id " + orderId + " not found");
        }
        var item = optionalItem.get();

        if(!item.getOrder().getId().equals(order.getId())) {
            throw new IllegalArgumentException("Item with id " + orderId +
                    " is not an item of the order with id " + order.getId());
        }

        itemRepo.deleteById(itemId);

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
}
