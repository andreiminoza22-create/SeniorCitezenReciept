package View;

import javax.swing.*;
import Controller.VitalsController;
import Utils.Validator;

/*
 * This form allows nurses or seniors to log vital signs.
 *
 * Updated to support Role-Based Access Control and
 * improved error handling.
 */
public class UpdateVitalsForm {

    public UpdateVitalsForm() {

        JFrame frame = new JFrame("Update Vitals");

        JTextField hr = new JTextField(5);
        JTextField sys = new JTextField(5);
        JTextField dia = new JTextField(5);
        JTextField glucose = new JTextField(5);

        // In a real application, this would come from a login session
        String userRole = "Senior";

        JButton submit = new JButton("Submit");

        JPanel panel = new JPanel();

        panel.add(new JLabel("Heart Rate:"));
        panel.add(hr);
        panel.add(new JLabel("Systolic:"));
        panel.add(sys);
        panel.add(new JLabel("Diastolic:"));
        panel.add(dia);
        panel.add(new JLabel("Glucose:"));
        panel.add(glucose);
        panel.add(submit);

        submit.addActionListener(e -> {
            try {
                int heartRate = Integer.parseInt(hr.getText());
                int systolic = Integer.parseInt(sys.getText());
                int diastolic = Integer.parseInt(dia.getText());
                double glucoseVal = Double.parseDouble(glucose.getText());

                // Requirement 4.B: Red Alert logic for dangerously high blood pressure
                if (systolic > 180) {
                    JOptionPane.showMessageDialog(frame,
                            "⚠ RED ALERT: Critical Blood Pressure Detected!",
                            "CRITICAL ALERT",
                            JOptionPane.ERROR_MESSAGE);
                }

                // Requirement 4.C: Pass the role to the controller for security verification
                String response = VitalsController.sendVitals(
                        heartRate,
                        systolic,
                        diastolic,
                        glucoseVal,
                        userRole
                );

                JOptionPane.showMessageDialog(frame, response);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter valid numeric values.");
            }
        });

        frame.add(panel);
        frame.setSize(350, 250);
        frame.setVisible(true);
    }
}