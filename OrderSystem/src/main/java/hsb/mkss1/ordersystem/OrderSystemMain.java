package hsb.mkss1.ordersystem;

import hsb.mkss1.ordersystem.persistence.InMemoryOrderRepo;
import hsb.mkss1.ordersystem.persistence.OrderRepo;
import hsb.mkss1.ordersystem.service.ItemFactory;
import hsb.mkss1.ordersystem.service.OrderService;
import hsb.mkss1.ordersystem.ui.OrderUserInterface;
import hsb.mkss1.ordersystem.ui.cli.CLI;
import hsb.mkss1.ordersystem.service.SimpleItemFactory;
import hsb.mkss1.ordersystem.ui.cli.reader.SimpleProductReader;
import hsb.mkss1.ordersystem.ui.cli.reader.SimpleServiceReader;
import hsb.mkss1.ordersystem.ui.gui.GUI;

public class OrderSystemMain {
    private OrderSystemMain() {
    }


    static void main(String[] args) {
        OrderRepo repo = new InMemoryOrderRepo();
        OrderService orderService = new OrderService(repo);
        ItemFactory itemFactory = new SimpleItemFactory();

        OrderUserInterface ui;
        if (args.length == 0 || args[0].equalsIgnoreCase("gui")) {
            ui = new GUI(itemFactory, orderService);
        } else {
            var cli = new CLI();
            cli.setItemFactory(itemFactory);
            cli.setProductReader(new SimpleProductReader());
            cli.setServiceReader(new SimpleServiceReader());
            cli.setOrderService(orderService);
            ui = cli;
        }

        ui.open();
    }
}
