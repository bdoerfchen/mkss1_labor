package hsb.mkss1.ordersystem;

import hsb.mkss1.ordersystem.adapters.persistence.InMemoryOrderRepo;
import hsb.mkss1.ordersystem.entities.OrderRepo;
import hsb.mkss1.ordersystem.entities.ItemFactory;
import hsb.mkss1.ordersystem.usecases.OrderService;
import hsb.mkss1.ordersystem.adapters.OrderUserInterface;
import hsb.mkss1.ordersystem.adapters.ui.cli.CLI;
import hsb.mkss1.ordersystem.usecases.SimpleItemFactory;
import hsb.mkss1.ordersystem.adapters.ui.cli.reader.SimpleLineItemReader;
import hsb.mkss1.ordersystem.adapters.ui.gui.GUI;

public class OrderSystemMain {
    private OrderSystemMain() {
    }


    static void main(String[] args) {
        OrderRepo repo = new InMemoryOrderRepo();
        ItemFactory itemFactory = new SimpleItemFactory();
        OrderService orderService = new OrderService(repo, itemFactory);

        OrderUserInterface ui;
        if (args.length == 0 || args[0].equalsIgnoreCase("gui")) {
            ui = new GUI(orderService);
        } else {
            var cli = new CLI();
            cli.setItemReader(new SimpleLineItemReader());
            cli.setOrderService(orderService);
            ui = cli;
        }

        ui.open();
    }
}
