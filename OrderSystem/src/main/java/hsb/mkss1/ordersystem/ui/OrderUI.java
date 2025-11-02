package hsb.mkss1.ordersystem.ui;

import hsb.mkss1.ordersystem.model.Item;
import hsb.mkss1.ordersystem.model.Order;
import hsb.mkss1.ordersystem.model.SimpleProduct;
import hsb.mkss1.ordersystem.model.SimpleService;
import hsb.mkss1.ordersystem.service.ItemFactory;
import hsb.mkss1.ordersystem.service.OrderService;
import hsb.mkss1.ordersystem.ui.reader.IProductReader;
import hsb.mkss1.ordersystem.ui.reader.IServiceReader;
import hsb.mkss1.ordersystem.ui.writer.ItemWriter;
import hsb.mkss1.ordersystem.ui.writer.ProductWriter;
import hsb.mkss1.ordersystem.ui.writer.ServiceWriter;
import hsb.mkss1.ordersystem.util.Input;
import hsb.mkss1.ordersystem.util.StringFormatterUtil;

import java.util.Map;

public class OrderUI {

    private ItemFactory itemFactory;
    private IProductReader productReader;
    private IServiceReader serviceReader;
    private OrderService orderService;

    private final Map<Class<? extends Item>, ItemWriter<? extends Item>> itemWriters = Map.of(
            SimpleProduct.class, new ProductWriter(),
            SimpleService.class, new ServiceWriter()
    );

    @SuppressWarnings("java:S2189")
    public void runMenuLoop() {
        boolean run = true;
        while (run) {
            Order order = orderService.initializeOrder();
            int input;
            do {
                printMenu();
                input = Input.readInt();
                Item item = switch (input) {
                    case 1 -> itemFactory.createProduct(productReader.readProduct());
                    case 2 -> itemFactory.createService(serviceReader.readService());
                    default -> null;
                };
                if (item != null) {
                    orderService.addItemToOrder(order, item);
                } else if (input != 0) {
                    IO.println("invalid");
                }
            } while (input != 0);
            orderService.finalizeOrder(order);
            order.sort();
            printOrder(order);
            IO.println("Would you like to make another order? (true | false)");
            run = Input.readBoolean();
        }

        orderService.getAll().forEach(this::printOrder);
    }

    private void printMenu() {
        IO.println("Your choice?");
        IO.println("(0) Finish order");
        IO.println("(1) Order Product");
        IO.println("(2) Order Service");
    }

    private void printOrder(Order order) {
        IO.println("-----------------------");
        for (Item item : order.getItems()) {
            getItemWriter(item.getClass()).writeItem(item);
        }
        IO.println("-----------------------");
        IO.println("Sum: " + StringFormatterUtil.formatPrice(order.getLumpSum()));
        IO.println("=======================");
        IO.println("");
    }

    @SuppressWarnings("unchecked")
    private ItemWriter<Item> getItemWriter(Class<? extends Item> clazz) {
        return (ItemWriter<Item>) itemWriters.get(clazz);
    }

    public void setItemFactory(ItemFactory itemFactory) {
        this.itemFactory = itemFactory;
    }

    public void setServiceReader(IServiceReader serviceReader) {
        this.serviceReader = serviceReader;
    }

    public void setProductReader(IProductReader productReader) {
        this.productReader = productReader;
    }

    public void setOrderController(OrderService orderService) {
        this.orderService = orderService;
    }
}
