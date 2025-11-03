package hsb.mkss1.ordersystem.ui.gui;

import hsb.mkss1.ordersystem.model.dtos.ServiceDTO;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.util.function.Consumer;

public class InputServiceWindow extends JDialog {

    private Consumer<ServiceDTO> createdCallback;

    public InputServiceWindow() {
        setTitle("Add Service");
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
        JLabel labelPersons = new JLabel("Persons:");
        JTextField tfPersons = new JFormattedTextField(formatter);
        JLabel labelHours = new JLabel("Hours:");
        JTextField tfHours = new JFormattedTextField(formatter);

        JButton buttonCreate = new JButton("Create");
        buttonCreate.addActionListener(e -> {
            int persons = Integer.parseInt(tfPersons.getText());
            int hours = Integer.parseInt(tfHours.getText());
            ServiceDTO service = new ServiceDTO(tfName.getText(), persons, hours);
            createdCallback.accept(service);
            this.dispose();
        });

        panel.add(labelName);
        panel.add(tfName);
        panel.add(labelPersons);
        panel.add(tfPersons);
        panel.add(labelHours);
        panel.add(tfHours);
        panel.add(buttonCreate);

        // Build panel and add to window
        getContentPane().add(panel);
    }

    public void openAndNotify(Consumer<ServiceDTO> consumer) {
        createdCallback = consumer;
        this.setVisible(true);
    }
}
