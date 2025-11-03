package hsb.mkss1.ordersystem.ui.gui;

import hsb.mkss1.ordersystem.model.Item;
import hsb.mkss1.ordersystem.model.Order;
import hsb.mkss1.ordersystem.service.ItemFactory;
import hsb.mkss1.ordersystem.service.OrderService;
import hsb.mkss1.ordersystem.ui.writer.AvailableWriters;
import hsb.mkss1.ordersystem.ui.writer.ItemWriter;
import hsb.mkss1.ordersystem.util.StringFormatterUtil;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.stream.Collectors;

public class OrderWindow extends JFrame {

    private final ItemFactory itemFactory;
    private final OrderService orderService;

    private final Order order;

    // UI references
    private JTextArea itemArea;
    private JPanel panelButtons;
    private JPanel panelFinished;
    private JLabel labelTotalPrice;
    private JLabel labelCheckoutDate;

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
        itemArea.setText("No items added yet");
        itemArea.setEnabled(false);
        itemArea.setDisabledTextColor(Color.BLACK);

        // Button panel
        panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());

        // Create buttons
        JButton buttonAddService = new JButton("Add Service");
        buttonAddService.addActionListener(_ ->
            new InputServiceWindow().openAndNotify(s -> {
                var service = itemFactory.createService(s);
                orderService.addItemToOrder(order, service);
                updateItemDisplay();
            })
        );
        JButton buttonAddProduct = new JButton("Add Product");
        buttonAddProduct.addActionListener(_ ->
            new InputProductWindow().openAndNotify(p -> {
                var product = itemFactory.createProduct(p);
                orderService.addItemToOrder(order, product);
                updateItemDisplay();
            })
        );
        JButton buttonFinish = new JButton("Finish");
        buttonFinish.addActionListener(_ -> onFinishOrder());
        panelButtons.add(buttonAddService);
        panelButtons.add(buttonAddProduct);
        panelButtons.add(buttonFinish);

        // Finish panel
        panelFinished = new JPanel();
        panelFinished.setVisible(false); // Not shown before finished
        panelFinished.setLayout(new BoxLayout(panelFinished, BoxLayout.Y_AXIS));
        labelTotalPrice = new JLabel("Total:");
        panelFinished.add(labelTotalPrice);
        labelCheckoutDate = new JLabel("Checkout Date:");
        panelFinished.add(labelCheckoutDate);

        JPanel panelFinishedButtons = new JPanel();
        JButton buttonClose = new JButton("Close");
        buttonClose.addActionListener(_ -> this.dispose());
        panelFinishedButtons.add(buttonClose);
        JButton buttonNewOrder = new JButton("New Order");
        buttonNewOrder.addActionListener(_ -> {
            new OrderWindow(itemFactory, orderService).setVisible(true);
            this.dispose();
        });
        panelFinishedButtons.add(buttonNewOrder);
        panelFinished.add(panelFinishedButtons);

        // Build panel and add to window
        panel.add(itemArea);
        panel.add(panelButtons);
        panel.add(panelFinished);

        getContentPane().add(panel);
    }

    private void updateItemDisplay() {
        // Prepare output
        var outputText = order.getItems().stream()
                .sorted(Comparator.comparingInt(Item::getPrice))
                .map(item -> AvailableWriters.getItemWriter(item.getClass()).writeItem(item))
                .collect(Collectors.joining(System.lineSeparator()));
        // Print output to text area
        itemArea.setText(outputText);
    }

    public OrderWindow(ItemFactory itemFactory, OrderService orderService) {
        this.itemFactory = itemFactory;
        this.orderService = orderService;

        this.order = orderService.initializeOrder();
        this.build();
    }

    private void onFinishOrder() {
        // Finish order
        orderService.finalizeOrder(order);
        labelTotalPrice.setText("Total price: %s".formatted(
                StringFormatterUtil.formatPrice(order.getLumpSum())
        ));
        labelCheckoutDate.setText("Checkout date: %s".formatted(
                StringFormatterUtil.formatDate(order.getCheckoutTimestamp())
        ));

        // Switch UI
        panelButtons.setVisible(false);
        panelFinished.setVisible(true);
    }
}
