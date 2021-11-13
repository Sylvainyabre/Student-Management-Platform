package ui;

import model.GradeLevel;
import model.Student;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidePanel extends JPanel implements ActionListener {
    private String[] gradeStrings = {"grade 7", "grade 8", "grade 9", "grade 10", "grade 11", "grade 12"};
    private MainFrame frame;
    private JButton registrationButton;
    private JLabel firstNameLabel;
    private JTextField firstNameField;
    private JLabel lastNameLabel;
    private JTextField lastNameField;
    private JComboBox<String> comboBox;
    private GradeLevel gradeLevel;

    //EFFECT:
    //MODIFIES
    public SidePanel(MainFrame frame) {
        Container container = frame.getContentPane();
        this.frame = frame;
        container.setLayout(new BorderLayout(8,6));
        container.add(this, BorderLayout.WEST);
       // this.setLayout(new BorderLayout());
        this.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
        this.setBackground(Color.WHITE);
        addLeftPanel();

    }

    //EFFECT:
    //MODIFIES:
    private void addLeftPanel() {
        JPanel leftGridPanel = new JPanel();
        firstNameLabel = new JLabel("First Name: ");
        firstNameField = new JTextField();
        lastNameLabel = new JLabel("Last Name: ");
        lastNameField = new JTextField();
        comboBox = new JComboBox<>(gradeStrings);
        registrationButton = new JButton("Register");
        addActionListenerToButtons();
        leftGridPanel.setLayout(new GridLayout(4, 4, 10, 5));
        leftGridPanel.setBorder(new LineBorder(Color.CYAN, 4));
        leftGridPanel.setBackground(new Color(172, 190, 191));
        leftGridPanel.add(firstNameLabel);
        leftGridPanel.add(firstNameField);
        leftGridPanel.add(lastNameLabel);
        leftGridPanel.add(lastNameField);
        leftGridPanel.add(comboBox);
        leftGridPanel.add(registrationButton);

        this.add(leftGridPanel);
    }

    //EFFECT:
    //MODIFIES:
    private void addActionListenerToButtons() {

        registrationButton.addActionListener(this);

    }

    //EFFECT:
    //MODIFIES:
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(registrationButton)) {
            int gradeLevelSelection = comboBox.getSelectedIndex();

            gradeLevel = frame.getClasses().get(gradeLevelSelection + 7);

            try {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                handleRegistration(gradeLevel, firstName, lastName);
                firstNameField.setText("");
                lastNameField.setText("");


            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        }

    }

    //EFFECT:
    //MODIFIES:
    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    private void handleRegistration(GradeLevel gradeLevel, String firstName, String lastName) {
        if (gradeLevel == null) {
            JOptionPane.showMessageDialog(frame, "Invalid Grade chosen");
        } else if (firstName.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid first name");
        } else if (lastName.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid last name");
        } else {
            Student newStudent = new Student(firstName, lastName);
            gradeLevel.registerStudent(newStudent);
            JOptionPane.showMessageDialog(frame, "Student registered successfully");
        }
    }
}
