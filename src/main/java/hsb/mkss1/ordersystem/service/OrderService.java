package hsb.mkss1.ordersystem.service;

import hsb.mkss1.ordersystem.model.Item;
import hsb.mkss1.ordersystem.model.Order;
import hsb.mkss1.ordersystem.model.Product;
import hsb.mkss1.ordersystem.model.Service;
import hsb.mkss1.ordersystem.ui.reader.ItemReader;
import hsb.mkss1.ordersystem.ui.reader.ProductReader;
import hsb.mkss1.ordersystem.ui.reader.ServiceReader;
import hsb.mkss1.ordersystem.ui.writer.ItemWriter;
import hsb.mkss1.ordersystem.ui.writer.ProductWriter;
import hsb.mkss1.ordersystem.ui.writer.ServiceWriter;
import hsb.mkss1.ordersystem.util.Input;
import hsb.mkss1.ordersystem.util.StringFormatterUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderService {

    private final List<ItemReader> itemReaders = List.of(
            new ProductReader(),
            new ServiceReader()
    );

    private final Map<Class<? extends Item>, ItemWriter> itemWriters = Map.of(
            Product.class, new ProductWriter(),
            Service.class, new ServiceWriter()
    );

    private static final int ITEM_READER_INDEX_OFFSET = 1;

	public void runMenuLoop() {
        Order order = new Order();
        int input;
        do {
            printMenu();
            input = Input.readInt();
            int index = input - ITEM_READER_INDEX_OFFSET;
            if (input > 0 && index < itemReaders.size()) {
                Item item = itemReaders.get(index).readItem();
                order.addItem(item);
            } else if (input != 0){
                IO.println("invalid");
            }
        } while (input != 0);
        order.sort();
        order.setCheckoutTimestamp(LocalDateTime.now());
        printOrder(order);
	}

    private void printMenu() {
		IO.println("Your choice?");
		IO.println("(0) Finish order");
        for (int i = 0; i < itemReaders.size(); i++) {
            int index = i + ITEM_READER_INDEX_OFFSET;
            IO.println("(%d) %s".formatted(index, itemReaders.get(i).getPromptText()));
        }
	}
	
	private void printOrder(Order order) {
        for (Item item : order.getItems()) {
            itemWriters.get(item.getClass()).writeItem(item);
        }

		IO.println("Sum: " + StringFormatterUtil.formatPrice(order.getLumpSum()));
	}
}
