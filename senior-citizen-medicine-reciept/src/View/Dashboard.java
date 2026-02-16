package View;

import javax.swing.*;

/*
 * This is the main dashboard of the client application.
 *
 * It serves as the navigation hub for all major actions:
 * - Add Senior
 * - Update Vitals
 * - Prescribe Medicine
 * - Search Senior
 */

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("Senior Care Dashboard");
        setSize(500,300);
        setLayout(null);

        JButton addSenior = new JButton("Add Senior");
        JButton vitals = new JButton("Update Vitals");
        JButton meds = new JButton("Prescribe Medicine");
        JButton search = new JButton("Search");

        // Absolute positioning for simplicity
        addSenior.setBounds(50,40,180,40);
        vitals.setBounds(250,40,180,40);
        meds.setBounds(50,100,180,40);
        search.setBounds(250,100,180,40);

        add(addSenior);
        add(vitals);
        add(meds);
        add(search);

        // Each button opens its corresponding form
        addSenior.addActionListener(e -> new AddSeniorForm());
        vitals.addActionListener(e -> new UpdateVitalsForm());
        meds.addActionListener(e -> new PrescribedMedicineForm());
        search.addActionListener(e -> new SearchForm());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
