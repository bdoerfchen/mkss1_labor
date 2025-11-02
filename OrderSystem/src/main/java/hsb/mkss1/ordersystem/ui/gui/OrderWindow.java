package hsb.mkss1.ordersystem.ui.gui;

import hsb.mkss1.ordersystem.model.Item;
import hsb.mkss1.ordersystem.model.Order;
import hsb.mkss1.ordersystem.model.dtos.ServiceDTO;
import hsb.mkss1.ordersystem.service.ItemFactory;
import hsb.mkss1.ordersystem.service.OrderService;
import hsb.mkss1.ordersystem.util.StringFormatterUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderWindow extends JFrame {

    private final ItemFactory itemFactory;
    private final OrderService orderService;

    private final Order order;

    JTextArea itemArea;

    private void build() {
        setTitle("Order");
        setSize(600, 400);

        // Set the default close operation
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a panel with BoxLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // TextArea
        itemArea = new JTextArea();
        itemArea.setText("Hello");
        itemArea.setEnabled(false);
        itemArea.setDisabledTextColor(Color.BLACK);

        // Button panel
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());

        // Create buttons
        JButton buttonAddService = new JButton("Add Service");
        buttonAddService.addActionListener(e -> {
            new InputServiceWindow().openAndNotify(s -> {
                var service = itemFactory.createService(s);
                orderService.addItemToOrder(order, service);
                updateItemDisplay();
            });
        });
        JButton buttonAddProduct = new JButton("Add Product");
        buttonAddProduct.addActionListener(e -> {
            new InputProductWindow().openAndNotify(p -> {
                var product = itemFactory.createProduct(p);
                orderService.addItemToOrder(order, product);
                updateItemDisplay();
            });
        });
        JButton buttonFinish = new JButton("Finish");
        panelButtons.add(buttonAddService);
        panelButtons.add(buttonAddProduct);
        panelButtons.add(buttonFinish);

        // Build panel and add to window
        panel.add(itemArea);
        panel.add(panelButtons);
        getContentPane().add(panel);
    }

    private void updateItemDisplay() {
        StringBuilder sb = new StringBuilder();
        for (Item item : order.getItems()) {
            sb.append("%s - %s\n".formatted(StringFormatterUtil.formatPrice(item.getPrice()), item.getName()));
        }
        itemArea.setText(sb.toString());
    }

    public OrderWindow(ItemFactory itemFactory, OrderService orderService) {
        this.itemFactory = itemFactory;
        this.orderService = orderService;

        this.order = orderService.initializeOrder();
        this.build();
    }
}
