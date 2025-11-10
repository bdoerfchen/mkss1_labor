package hsb.mkss1.ordersystem.adapters.persistence;

import hsb.mkss1.ordersystem.entities.OrderEntity;
import hsb.mkss1.ordersystem.entities.OrderRepo;

import java.util.*;

public class InMemoryOrderRepo implements OrderRepo {

    private int idCounter = 0;

    private final Map<Integer, OrderEntity> entries = new HashMap<>();

    @Override
    public void save(OrderEntity orderEntity) {
        if (orderEntity.getId() == null) {
            orderEntity.setId(idCounter++);
        }

        entries.put(orderEntity.getId(), orderEntity.createCopy());
    }

    @Override
    public void remove(OrderEntity orderEntity) {
        if (orderEntity == null || orderEntity.getId() == null) {
            return;
        }

        entries.remove(orderEntity.getId());
    }

    @Override
    public Optional<OrderEntity> findById(int id) {
        return Optional.ofNullable(entries.get(id)).map(OrderEntity::createCopy);
    }

    @Override
    public List<OrderEntity> findAll() {
        return entries.values().stream().map(OrderEntity::createCopy).toList();
    }
}
