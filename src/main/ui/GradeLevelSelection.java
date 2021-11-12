package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeLevelSelection extends JPanel implements ActionListener {

    private JComboBox<String> comboBox;
    private int gradeKey;

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public GradeLevelSelection() {
        String[] classStrings = {"Grade 7", "Grade 8", "Grade 9",
                "Grade 10", "Grade 11", "Grade 12"};
        comboBox = new JComboBox<>(classStrings);
        comboBox.setVisible(true);
        this.setVisible(true);
        gradeKey = 0;
        comboBox.addActionListener(this);
        this.add(comboBox);


    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public int getGradeKey() {
        return gradeKey;
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            int chosenIndex = comboBox.getSelectedIndex();
            gradeKey = chosenIndex + 1;
        }
    }
}
