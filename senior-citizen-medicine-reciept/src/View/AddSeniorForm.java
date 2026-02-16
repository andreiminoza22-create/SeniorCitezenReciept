package View;

import javax.swing.*;
import Controller.SeniorController;
import Models.Senior;
import java.awt.GridLayout;

public class AddSeniorForm {

    public AddSeniorForm() { // Fixed: Changed from main() to constructor

        JFrame frame = new JFrame("Add Senior");

        JTextField name = new JTextField(15);
        JTextField age = new JTextField(5);
        JTextField address = new JTextField(15);
        JTextField medicalHistory = new JTextField(15);
        JTextField phone = new JTextField(15);
        JTextField emergencyName = new JTextField(15);
        JTextField emergencyPhone = new JTextField(15);

        JButton submit = new JButton("Submit");

        // Added GridLayout to make the form look neat
        JPanel panel = new JPanel(new GridLayout(8, 2, 5, 5));

        panel.add(new JLabel("Name:"));
        panel.add(name);
        panel.add(new JLabel("Age:"));
        panel.add(age);
        panel.add(new JLabel("Address:"));
        panel.add(address);
        panel.add(new JLabel("Medical History:"));
        panel.add(medicalHistory);
        panel.add(new JLabel("Phone Number:"));
        panel.add(phone);
        panel.add(new JLabel("Emergency Contact Name:"));
        panel.add(emergencyName);
        panel.add(new JLabel("Emergency Contact Phone:"));
        panel.add(emergencyPhone);

        panel.add(new JLabel("")); panel.add(submit); // Spacer

        frame.add(panel);
        frame.setSize(400, 300);
        frame.setVisible(true);
        // Fixed: DISPOSE_ON_CLOSE ensures only this window closes, not the Dashboard
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        submit.addActionListener(e -> {
            try {
                // Fixed: Now captures all Senior data
                Senior s = new Senior(
                        name.getText(),
                        Integer.parseInt(age.getText()),
                        address.getText(),
                        medicalHistory.getText(),
                        phone.getText(),
                        emergencyName.getText(),
                        emergencyPhone.getText()
                );

                String response = SeniorController.addSenior(s);
                JOptionPane.showMessageDialog(frame, response);

                if (response.contains("Success")) {
                    frame.dispose(); // Close form upon success
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Error: Please enter a valid numerical age.");
            }
        });
    }
}