package hsb.mkss1.ordersystem.adapters.ui.gui;

import hsb.mkss1.ordersystem.usecases.dtos.LineItemDTO;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.util.function.Consumer;

public class InputLineItemWindow extends JDialog {

    private Consumer<LineItemDTO> createdCallback;

    public InputLineItemWindow() {
        setTitle("Add Line Item");
        setSize(250, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Number formatter
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setAllowsInvalid(false);

        // Fields
        JLabel labelName = new JLabel("Name:");
        JTextField tfName = new JTextField();
        JLabel labelPrice = new JLabel("Unit Price (cents):");
        JTextField tfPrice = new JFormattedTextField(formatter);
        JLabel labelQuantity = new JLabel("Quantity:");
        JTextField tfQuantity = new JFormattedTextField(formatter);

        JButton buttonCreate = new JButton("Create");
        buttonCreate.addActionListener(_ -> {
            int persons = Integer.parseInt(tfPrice.getText());
            int hours = Integer.parseInt(tfQuantity.getText());
            LineItemDTO lineItemDTO = new LineItemDTO(tfName.getText(), persons, hours);
            createdCallback.accept(lineItemDTO);
            this.dispose();
        });

        panel.add(labelName);
        panel.add(tfName);
        panel.add(labelPrice);
        panel.add(tfPrice);
        panel.add(labelQuantity);
        panel.add(tfQuantity);
        panel.add(buttonCreate);

        // Build panel and add to window
        getContentPane().add(panel);
    }

    public void openAndNotify(Consumer<LineItemDTO> consumer) {
        createdCallback = consumer;
        this.setVisible(true);
    }
}
