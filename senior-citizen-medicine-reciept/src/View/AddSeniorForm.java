package View;

import javax.swing.*;
import Controller.SeniorController;
import Models.Senior;

public class AddSeniorForm {

    public static void main(String[] args){

        JFrame frame = new JFrame("Add Senior");

        JTextField name = new JTextField(15);
        JTextField age = new JTextField(3);
        JTextField address = new JTextField(15);

        JButton submit = new JButton("Submit");

        JPanel panel = new JPanel();

        panel.add(new JLabel("Name:"));
        panel.add(name);

        panel.add(new JLabel("Age:"));
        panel.add(age);

        panel.add(new JLabel("Address:"));
        panel.add(address);

        panel.add(submit);

        frame.add(panel);
        frame.setSize(300,200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        submit.addActionListener(e -> {

            Senior s = new Senior(
                    name.getText(),
                    Integer.parseInt(age.getText()),
                    address.getText(),
                    "", "", "", ""
            );

            String response = SeniorController.addSenior(s);

            JOptionPane.showMessageDialog(frame, response);
        });
    }
}
