package View;

import javax.swing.*;
import Controller.VitalsController;
import Utils.Validator;

/*
 * This form allows nurses or seniors to log vital signs.
 *
 * It also includes validation and alert logic:
 * - Prevents invalid heart rate values
 * - Shows a RED ALERT if systolic BP exceeds 180
 */

public class UpdateVitalsForm {

    public UpdateVitalsForm() {

        JFrame frame = new JFrame("Update Vitals");

        JTextField hr = new JTextField(5);
        JTextField sys = new JTextField(5);
        JTextField dia = new JTextField(5);
        JTextField glucose = new JTextField(5);

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

            int heartRate = Integer.parseInt(hr.getText());
            int systolic = Integer.parseInt(sys.getText());

            // Validation check for impossible heart rate values
            if(!Validator.validHeartRate(heartRate)) {
                JOptionPane.showMessageDialog(frame, "Invalid Heart Rate!");
                return;
            }

            // Red Alert logic for dangerously high blood pressure
            if(systolic > 180) {
                JOptionPane.showMessageDialog(frame,
                        "⚠ RED ALERT: Critical Blood Pressure Detected!");
            }

            // Send vitals to server using controller
            String response = VitalsController.sendVitals(
                    heartRate,
                    systolic,
                    Integer.parseInt(dia.getText()),
                    Double.parseDouble(glucose.getText())
            );

            JOptionPane.showMessageDialog(frame, response);
        });

        frame.add(panel);
        frame.setSize(350,250);
        frame.setVisible(true);
    }
}
