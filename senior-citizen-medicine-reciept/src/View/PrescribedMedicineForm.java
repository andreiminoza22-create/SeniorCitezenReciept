package View;

import javax.swing.*;
import Controller.MedicationController;

/*
 * This form allows doctors to prescribe new medications.
 * Updated to support Role-Based Access Control and improved data handling.
 */
public class PrescribedMedicineForm {

    public PrescribedMedicineForm() {

        JFrame frame = new JFrame("Prescribe Medicine");

        JTextField name = new JTextField(10);
        JTextField dosage = new JTextField(10);
        JTextField frequency = new JTextField(10);
        JTextField instructions = new JTextField(10);

        // In a complete implementation, this would be retrieved from the login session.
        String userRole = "Doctor";

        JButton submit = new JButton("Prescribe");

        JPanel panel = new JPanel();

        panel.add(new JLabel("Name:")); panel.add(name);
        panel.add(new JLabel("Dosage:")); panel.add(dosage);
        panel.add(new JLabel("Frequency:")); panel.add(frequency);
        panel.add(new JLabel("Instructions:")); panel.add(instructions);
        panel.add(submit);

        submit.addActionListener(e -> {
            // Requirement 4.A: Ensure basic input integrity before calling the controller.
            if (name.getText().trim().isEmpty() || dosage.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Error: Name and Dosage are required.");
                return;
            }

            // Requirement 2 & 4.C: Send prescription with the role for server-side verification.
            String response = MedicationController.prescribe(
                    name.getText(),
                    dosage.getText(),
                    frequency.getText(),
                    instructions.getText(),
                    userRole
            );

            JOptionPane.showMessageDialog(frame, response);
        });

        frame.add(panel);
        frame.setSize(400, 250);
        frame.setVisible(true);
    }
}