package hsb.mkss1.ordersystem.adapters.ui.gui;

import hsb.mkss1.ordersystem.usecases.OrderService;
import hsb.mkss1.ordersystem.adapters.OrderUserInterface;

public class GUI implements OrderUserInterface {


    private final OrderService orderService;

    public GUI(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void open() {
        new OrderWindow(orderService).setVisible(true);
    }
}
