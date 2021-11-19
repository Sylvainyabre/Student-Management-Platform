package ui;

import model.GradeLevel;
import model.Student;

import javax.swing.*;
import java.awt.*;


// A class that represents a student registration panel
public class RegistrationForm {
    private String[] gradeStrings = {"grade 7", "grade 8", "grade 9", "grade 10", "grade 11", "grade 12"};
    private MainFrame frame;
    private JButton registrationButton;
    private JLabel firstNameLabel;
    private JTextField firstNameField;
    private JLabel lastNameLabel;
    private JTextField lastNameField;
    private JComboBox<String> comboBox;
    private GradeLevel gradeLevel;

    //EFFECTS: creates a new Registration class and initializes variables, adds components to panel
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
        panel.add(registrationButton);

        int result = JOptionPane.showConfirmDialog(frame, panel,
                "Registration", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        register(result);

    }

    //EFFECT:if user chooses OK_OPTION, get combobox selection index,
    // find the corresponding gradeLevel register a student with the entered first and last names,
    //RuntimeException is thrown, show popup message with the exception message
    //MODIFIES:this

    public void register(int choice) {
        if (choice == JOptionPane.OK_OPTION) {
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

    //EFFECT:if gradeLevel is not null and firstName and lastName are not empty,
    // creates a new student with firstName and lastName, register student in gradeLevel,
    // else show error popup
    //MODIFIES:this,Student
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
            frame.repaint();
            frame.revalidate();
            JOptionPane.showMessageDialog(frame, "Student registered successfully");
        }
    }


}
