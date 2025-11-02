package hsb.mkss1.ordersystem.persistence;

import hsb.mkss1.ordersystem.model.Order;

import java.util.*;

public class InMemoryOrderRepo implements OrderRepo {

    private int idCounter = 0;

    private final Map<Integer, Order> entries = new HashMap<>();

    @Override
    public void save(Order order) {
        if (order.getId() == null) {
            order.setId(idCounter++);
        }

        entries.put(order.getId(), order);
    }

    @Override
    public void remove(Order order) {
        if (order == null || order.getId() == null) {
            return;
        }

        entries.remove(order.getId());
    }

    @Override
    public Optional<Order> findById(int id) {
        return Optional.ofNullable(entries.get(id)).map(Order::createCopy);
    }

    @Override
    public List<Order> findAll() {
        return entries.values().stream().map(Order::createCopy).toList();
    }
}
