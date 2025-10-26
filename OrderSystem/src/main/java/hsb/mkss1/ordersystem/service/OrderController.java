package hsb.mkss1.ordersystem.service;

import hsb.mkss1.ordersystem.model.Item;
import hsb.mkss1.ordersystem.model.Order;

import java.time.LocalDateTime;

public class OrderController {


    public Order initializeOrder() {
        return new Order();
    }

    public void addItemToOrder(Order order, Item item) {
        order.addItem(item);
    }

    public void finalizeOrder(Order order) {
        order.sort();
        order.setCheckoutTimestamp(LocalDateTime.now());
    }

}
