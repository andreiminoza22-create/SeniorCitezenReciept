package View;

import javax.swing.*;
import Controller.SearchController;

/*
 * This form allows searching of seniors using:
 * - Name
 * - Barangay ID
 *
 * The query is sent to the server using XML.
 */

public class SearchForm {

    public SearchForm() {

        JFrame frame = new JFrame("Search Senior");

        JTextField keyword = new JTextField(15);
        JButton search = new JButton("Search");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Name / Barangay ID:"));
        panel.add(keyword);
        panel.add(search);

        search.addActionListener(e -> {

            String result = SearchController.search(keyword.getText());

            JOptionPane.showMessageDialog(frame, result);
        });

        frame.add(panel);
        frame.setSize(350,150);
        frame.setVisible(true);
    }
}
