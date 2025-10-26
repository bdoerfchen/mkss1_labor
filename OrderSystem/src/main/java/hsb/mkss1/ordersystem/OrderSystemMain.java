package hsb.mkss1.ordersystem;

import hsb.mkss1.ordersystem.service.OrderService;

public class OrderSystemMain {
    private OrderSystemMain() {
    }

    @SuppressWarnings("java:S2189")
    static void main() {
        OrderService orderService = new OrderService();
        while (true) {
            orderService.runMenuLoop();
        }
    }
}
