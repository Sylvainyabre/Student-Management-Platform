package ui;

import model.GradeLevel;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm implements ActionListener {
    private String[] gradeStrings = {"grade 7", "grade 8", "grade 9", "grade 10", "grade 11", "grade 12"};
    private MainFrame frame;
    private JButton registrationButton;
    private JLabel firstNameLabel;
    private JTextField firstNameField;
    private JLabel lastNameLabel;
    private JTextField lastNameField;
    private JComboBox<String> comboBox;
    private GradeLevel gradeLevel;


    public RegistrationForm(MainFrame frame) {
        firstNameLabel = new JLabel("First Name: ");
        firstNameField = new JTextField();
        lastNameLabel = new JLabel("Last Name: ");
        lastNameField = new JTextField();
        comboBox = new JComboBox<>(gradeStrings);
        registrationButton = new JButton("Register");
        JPanel panel = new JPanel(new GridLayout(0, 1));

        panel.add(comboBox);
        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Registration", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
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
