package hsb.mkss1.ordersystem.ui.gui;

import hsb.mkss1.ordersystem.model.dtos.ProductDTO;
import hsb.mkss1.ordersystem.model.dtos.ServiceDTO;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.util.function.Consumer;

public class InputProductWindow extends JDialog {

    private Consumer<ProductDTO> createdCallback;

    public InputProductWindow() {
        setTitle("Add Service");
        setSize(250, 400);

        // Set the default close operation
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

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
        buttonCreate.addActionListener(e -> {
            int persons = Integer.parseInt(tfPrice.getText());
            int hours = Integer.parseInt(tfQuantity.getText());
            ProductDTO product = new ProductDTO(tfName.getText(), persons, hours);
            createdCallback.accept(product);
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

    public void openAndNotify(Consumer<ProductDTO> consumer) {
        createdCallback = consumer;
        this.setVisible(true);
    }
}
