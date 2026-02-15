package View;

import javax.swing.*;

public class SeniorView extends JFrame {
    public JTextField nameField = new JTextField(20);
    public JButton submitBtn = new JButton("Add Senior");

    public SeniorView() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(submitBtn);
        this.add(panel);
        this.pack();
    }
}