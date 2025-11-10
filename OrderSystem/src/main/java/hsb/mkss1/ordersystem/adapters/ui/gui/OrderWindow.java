package hsb.mkss1.ordersystem.adapters.ui.gui;

import hsb.mkss1.ordersystem.usecases.OrderService;
import hsb.mkss1.ordersystem.adapters.ui.util.StringFormatterUtil;
import hsb.mkss1.ordersystem.usecases.dtos.LineItemDTO;
import hsb.mkss1.ordersystem.usecases.dtos.OrderDTO;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.stream.Collectors;

public class OrderWindow extends JFrame {

    private final OrderService orderService;

    private OrderDTO currentOrderDto;

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
        JButton buttonAddProduct = new JButton("Add Line Item");
        buttonAddProduct.addActionListener(_ ->
            new InputLineItemWindow().openAndNotify(lineItemDTO -> orderService.addItemToOrder(
                    currentOrderDto.getId(),
                    lineItemDTO,
                    optionalResultOrder ->
                        optionalResultOrder.ifPresent(orderDTO -> {
                            currentOrderDto = orderDTO;
                            updateItemDisplay();
                        })
                    ))
        );
        JButton buttonFinish = new JButton("Finish");
        buttonFinish.addActionListener(_ -> onFinishOrder());
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
            new OrderWindow(orderService).setVisible(true);
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
        var outputText = currentOrderDto.getLineItems().stream()
                .sorted(Comparator.comparingInt(LineItemDTO::price))
                .map(StringFormatterUtil::formatLineItem)
                .collect(Collectors.joining(System.lineSeparator()));
        // Print output to text area
        itemArea.setText(outputText);
    }

    public OrderWindow(OrderService orderService) {
        this.orderService = orderService;

        orderService.initializeOrder(dto ->
                currentOrderDto = dto);
        this.build();
    }

    private void onFinishOrder() {
        // Finish order
        orderService.finalizeOrder(currentOrderDto.getId(), optionalOrderDTO -> {
            if (optionalOrderDTO.isEmpty()) {
                return;
            }
            currentOrderDto = optionalOrderDTO.get();

            labelTotalPrice.setText("Total price: %s".formatted(
                    StringFormatterUtil.formatPrice(currentOrderDto.getLumpSum())
            ));
            labelCheckoutDate.setText("Checkout date: %s".formatted(
                    StringFormatterUtil.formatDate(currentOrderDto.getCheckoutTimestamp())
            ));

            // Switch UI
            panelButtons.setVisible(false);
            panelFinished.setVisible(true);

        });
    }
}
