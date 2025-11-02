package hsb.mkss1.ordersystem.service;

import hsb.mkss1.ordersystem.model.Item;
import hsb.mkss1.ordersystem.model.Order;
import hsb.mkss1.ordersystem.persistence.OrderRepo;

import java.time.LocalDateTime;
import java.util.List;

public class OrderService {

    private final OrderRepo repo;
    public OrderService(OrderRepo repo) {
        this.repo = repo;
    }

    public Order initializeOrder() {
        var order = new Order();
        repo.save(order);
        return order;
    }

    public void addItemToOrder(Order order, Item item) {
        order.addItem(item);
        repo.save(order);
    }

    public void finalizeOrder(Order order) {
        order.setCheckoutTimestamp(LocalDateTime.now());
        repo.save(order);
    }

    public List<Order> getAll() {
        return repo.findAll();
    }

}
