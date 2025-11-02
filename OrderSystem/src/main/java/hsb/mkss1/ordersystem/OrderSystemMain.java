package hsb.mkss1.ordersystem;

import hsb.mkss1.ordersystem.persistence.InMemoryOrderRepo;
import hsb.mkss1.ordersystem.persistence.OrderRepo;
import hsb.mkss1.ordersystem.service.OrderController;
import hsb.mkss1.ordersystem.ui.OrderUI;
import hsb.mkss1.ordersystem.service.SimpleItemFactory;
import hsb.mkss1.ordersystem.ui.reader.SimpleProductReader;
import hsb.mkss1.ordersystem.ui.reader.SimpleServiceReader;

public class OrderSystemMain {
    private OrderSystemMain() {
    }


    static void main() {
        OrderRepo repo = new InMemoryOrderRepo();
        OrderUI ui = new OrderUI(repo);
        ui.setItemFactory(new SimpleItemFactory());
        ui.setProductReader(new SimpleProductReader());
        ui.setServiceReader(new SimpleServiceReader());
        ui.setOrderController(new OrderController());
        ui.runMenuLoop();
    }
}
