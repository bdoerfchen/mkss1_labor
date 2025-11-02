package hsb.mkss1.ordersystem.ui.gui;

import hsb.mkss1.ordersystem.service.ItemFactory;
import hsb.mkss1.ordersystem.service.OrderService;
import hsb.mkss1.ordersystem.ui.OrderUserInterface;

public class GUI implements OrderUserInterface {


    private final ItemFactory itemFactory;
    private final OrderService orderService;

    public GUI(ItemFactory itemFactory, OrderService orderService) {
        this.itemFactory = itemFactory;
        this.orderService = orderService;
    }

    @Override
    public void open() {
        new OrderWindow(itemFactory, orderService).setVisible(true);
    }
}
