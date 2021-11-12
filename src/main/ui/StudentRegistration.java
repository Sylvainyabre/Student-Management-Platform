package ui;

import model.GradeLevel;
import model.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class StudentRegistration extends JPanel implements ActionListener {
    private JLabel firstNameLabel;
    private JTextArea firstNameField;
    private JLabel lastNameLabel;
    private JTextArea lastNameField;
    private MainFrame frame;
    private JButton registrationButton;
    private GradeLevel gradeLevel;



    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public StudentRegistration(MainFrame frame) {
        this.frame = frame;
        setup();



    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    private void setup() {
        firstNameField = new JTextArea();
        firstNameLabel = new JLabel("First Name: ");
        lastNameLabel = new JLabel("Last Name: ");
        lastNameField = new JTextArea();
        registrationButton = new JButton("register");
        this.add(firstNameLabel);
        this.add(firstNameField);
        this.add(lastNameLabel);
        this.add(lastNameField);
        this.add(registrationButton);
        frame.add(this);

    }


    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(registrationButton)) {
            GradeLevelSelection gradeLevelSelection = new GradeLevelSelection();
            int selectedGradeKey = new GradeLevelSelection().getGradeKey();
            HashMap<Integer, GradeLevel> classesMap = frame.getClasses();
            gradeLevel = classesMap.get(selectedGradeKey);
            System.out.println(gradeLevel);
            this.add(gradeLevelSelection);
            try {
                System.out.println("Button pressed");
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                handleRegistration(gradeLevel, firstName, lastName);


            } catch (RuntimeException ex) {
                ex.printStackTrace();
            }
        }
    }

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
