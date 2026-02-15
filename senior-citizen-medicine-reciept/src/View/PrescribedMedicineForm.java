package View;

import javax.swing.*;
import Controller.MedicationController;

/*
 * This form allows doctors to prescribe new medications.
 *
 * Data entered here is converted into XML and sent to the server.
 */

public class PrescribedMedicineForm {

    public PrescribedMedicineForm() {

        JFrame frame = new JFrame("Prescribe Medicine");

        JTextField name = new JTextField(10);
        JTextField dosage = new JTextField(10);
        JTextField frequency = new JTextField(10);
        JTextField instructions = new JTextField(10);

        JButton submit = new JButton("Prescribe");

        JPanel panel = new JPanel();

        panel.add(new JLabel("Name:")); panel.add(name);
        panel.add(new JLabel("Dosage:")); panel.add(dosage);
        panel.add(new JLabel("Frequency:")); panel.add(frequency);
        panel.add(new JLabel("Instructions:")); panel.add(instructions);
        panel.add(submit);

        submit.addActionListener(e -> {

            String response = MedicationController.prescribe(
                    name.getText(),
                    dosage.getText(),
                    frequency.getText(),
                    instructions.getText()
            );

            JOptionPane.showMessageDialog(frame, response);
        });

        frame.add(panel);
        frame.setSize(400,250);
        frame.setVisible(true);
    }
}
