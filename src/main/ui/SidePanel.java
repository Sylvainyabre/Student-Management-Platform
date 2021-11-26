package ui;

import model.GradeLevel;
import model.Student;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

// A class representing a panel with many child components
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
    private JButton studentsButton;
    private StudentsDisplay studentsDisplay;
    private HashMap<Integer, GradeLevel> classes;
    private int gradeLevelSelection;
    private JButton deleteButton;
    private JButton transcriptButton;
    private JComboBox<String> secondComboBox;
    private JTextField idField;
    private int secondComboBoxSelection;
    private GradeLevel secondComboBoxSelectedGrade;
    private int response;
    private JPanel imagePanel;
    private String[] subjectNames = {"Mathematics", "Biology", "Physics", "Chemistry",
            "English", "French", "Physical Education",
            "Geography", "History", "Philosophy" };


    //EFFECT: creates a new Panel, initializes variables and add Panel to Frame
    public SidePanel(MainFrame frame) {
        this.frame = frame;
        this.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
        this.setLayout(new GridLayout(4, 0));
        this.setBackground(Color.WHITE);
        addLeftPanel();
        frame.add(this, BorderLayout.WEST);
        frame.add(imagePanel, BorderLayout.CENTER);


    }


    //MODIFIES:this
    //EFFECT: initializes all variables, adds listeners to button and add subcomponents
    private void addLeftPanel() {
        initializeVariables();
        addActionListenerToButtons();
        addComponents();
        addSecondPanel();
    }

    //EFFECT:instantiates declared variables
    //MODIFIES:this
    private void initializeVariables() {

        firstNameLabel = new JLabel("First Name: ");
        firstNameField = new JTextField();
        lastNameLabel = new JLabel("Last Name: ");
        lastNameField = new JTextField();
        secondComboBox = new JComboBox<>(gradeStrings);
        comboBox = new JComboBox<>(gradeStrings);
        registrationButton = new JButton("Register");
        studentsButton = new JButton("Display Students");
        transcriptButton = new JButton("Show Transcript");
        deleteButton = new JButton("Delete Student");
        imagePanel = new JPanel();
        imagePanel.setLayout(new GridLayout(1, 0));
        imagePanel.setBackground(new Color(50, 123, 168));
        updateSelection();
    }

    //MODIFIES: this,leftGridPanel
    //EFFECT: creates a new Panel, sets its properties and adds its child components
    private void addComponents() {
        JPanel leftGridPanel = new JPanel();
        this.setBackground(new Color(50, 123, 168));
        leftGridPanel.setLayout(new GridLayout(4, 4, 10, 5));
        leftGridPanel.setBorder(new LineBorder(Color.CYAN, 4));
        leftGridPanel.setBackground(new Color(172, 190, 191));
        leftGridPanel.add(firstNameLabel);
        leftGridPanel.add(firstNameField);
        leftGridPanel.add(lastNameLabel);
        leftGridPanel.add(lastNameField);
        leftGridPanel.add(comboBox);
        leftGridPanel.add(registrationButton);
        leftGridPanel.add(studentsButton);
        ImageIcon icon = new ImageIcon("./data/school.png");
        Image image = icon.getImage();
        ImageIcon resizedIcon = new ImageIcon(getScaledImage(image, 420, 485));
        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(-55, 5, 0, 5));
        imagePanel.add(imageLabel);
        this.setBorder(BorderFactory.createEmptyBorder(50, 5, 5, 5));
        this.add(leftGridPanel);
        studentsDisplay = new StudentsDisplay(frame, gradeLevel);
    }

    //EFFECT:creates a new panel, adds a label to it and adds its subcomponents
    //MODIFIES:this,secondPanel
    //REQUIRES:
    private void addSecondPanel() {
        JPanel secondPanel = new JPanel();
        JLabel idLabel = new JLabel("Student ID");
        idField = new JTextField();
        secondComboBoxSelection = secondComboBox.getSelectedIndex();
        secondPanel.setLayout(new GridLayout(4, 4, 10, 5));
        secondPanel.setBorder(new LineBorder(Color.CYAN, 4));
        secondPanel.setBackground(new Color(172, 190, 191));
        secondPanel.add(idLabel);
        secondPanel.add(idField);
        secondPanel.add(secondComboBox);
        secondPanel.add(deleteButton);
        secondPanel.add(transcriptButton);
        new GradeUpdate(frame, this);
        this.add(secondPanel);

    }

    //MODIFIES: this,frame
    //EFFECT:adds event listeners to buttons, if comboBox selection has changed,
    // remove studentDisplay from frame, revalidates and repaints frame
    private void addActionListenerToButtons() {

        registrationButton.addActionListener(this);
        studentsButton.addActionListener(this);
        transcriptButton.addActionListener(this);
        deleteButton.addActionListener(this);
        comboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.DESELECTED) {

                updateSelection();
                frame.remove(studentsDisplay);
                studentsDisplay = new StudentsDisplay(frame, gradeLevel);
                frame.revalidate();
                frame.repaint();
            }
        });


    }

    //EFFECT: get selection index and the class corresponding to index
    // assigns that class to gradeLevel
    //MODIFIES:this
    //REQUIRES:
    private void updateSelection() {
        gradeLevelSelection = comboBox.getSelectedIndex();
        classes = frame.getClasses();
        gradeLevel = classes.get(gradeLevelSelection + 7);
    }

    //EFFECT: executes actions corresponding to pressed button
    //MODIFIES: this
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(registrationButton)) {
            updateSelection();
            executeRegistration();
        }
        if (e.getSource().equals(studentsButton)) {
            executeStudentDisplay();
        }
        if (e.getSource().equals(transcriptButton)) {

            handleTranscriptDisplay();

        }
        if (e.getSource().equals(deleteButton)) {

            handleStudentDelete();
        }

    }


    //EFFECT: if student delete button is pressed, grabs the entered id
    //if id is empty, show error popup, else  finds the student with the id and delete it,
    // throws NumberFormatException if id is not convertible to int
    //MODIFIES: this
    private void handleStudentDelete() {
        int studentId;
        try {
            studentId = Integer.parseInt(idField.getText());
            if (idField.getText().isEmpty() || !(Integer.parseInt(idField.getText()) > 0)) {
                JOptionPane.showMessageDialog(frame, "Please, enter a valid ID !");
            } else {
                executeDelete(studentId);
                idField.setText("");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Please, enter a valid ID !");
        }

    }

    //EFFECT: if a student exists with the studentId, show confirmation popup
    // and delete student if "yes" is selected and then show confirmation message of deletion
    //MODIFIES:this
    private void executeDelete(int studentId) {
        secondComboBoxSelection = secondComboBox.getSelectedIndex();
        secondComboBoxSelectedGrade = classes.get(secondComboBoxSelection + 7);
        Student studentToDelete = secondComboBoxSelectedGrade.findStudentById(studentId);
        if (studentToDelete != null) {
            Object[] options = {"Yes", "No"};
            response = JOptionPane.showOptionDialog(frame,
                    "Do you want to delete " + studentToDelete.getFullName()
                            + " from " + studentToDelete.getGradeLevel().getName() + "?",
                    "Remove Student",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,     //do not use a custom Icon
                    options,  //the titles of buttons
                    options[0]); //default button title
            if (response == 0) {
                secondComboBoxSelectedGrade.removeStudentFromClass(studentId);
                JOptionPane.showMessageDialog(frame, "Deleted successfully");

            }
        }
    }

    //EFFECT: if idField value is not empty and id is an integer greater than 0,
    //and selected grade is not null, find student with id and show their transcript,
    // if any of the aforementioned conditions is not met, an error popup message is show to the user
    // if an integer cannot be parsed from id field, show an error message too
    private void handleTranscriptDisplay() {
        int studentId = -1;
        if (idField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please, enter a valid ID !");
        } else {
            try {
                if (!(Integer.parseInt(idField.getText()) > 0)) {
                    JOptionPane.showMessageDialog(frame, "Please, enter a valid ID !");
                }
                studentId = Integer.parseInt(idField.getText());
                secondComboBoxSelection = secondComboBox.getSelectedIndex();
                secondComboBoxSelectedGrade = classes.get(secondComboBoxSelection + 7);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Please, enter a valid ID !");

            }
        }

        if (secondComboBoxSelectedGrade != null) {
            Student chosenStudent = secondComboBoxSelectedGrade.findStudentById(studentId);
            if (chosenStudent == null) {
                JOptionPane.showMessageDialog(frame, "No student found with this ID !");
            } else {
                new StudentTranscript(frame, chosenStudent);
            }

        }
    }

    //EFFECT: executes function that displays transcript,
    // if a runtime exception is thrown, show the error message
    private void executeStudentDisplay() {
        try {
            handleStudentDisplay();

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
        }
    }

    //EFFECT:  runs handleRegistration with the values from firstNameField and lastNameField
    // next resets the fields to empty strings, if an exception occurs,
    // show a popup message with the exception message
    private void executeRegistration() {
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

    //EFFECT: updates comboBox selections displays transcript and revalidates and repaints frame
    //MODIFIES: this,frame
    private void handleStudentDisplay() {
        updateSelection();

        frame.remove(studentsDisplay);
        studentsDisplay = new StudentsDisplay(frame, gradeLevel);
        frame.revalidate();
        frame.repaint();
    }

    //EFFECT:if gradeLevel is not null,firstName and lastName are not empty, creates a new student
    //with firstName and lastName and then registers in gradeLevel
    // if any of these above conditions are not met, show a popup error message.
    //MODIFIES:this, gradeLevel

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
            frame.remove(studentsDisplay);
            studentsDisplay = new StudentsDisplay(frame, gradeLevel);
            frame.revalidate();
            frame.repaint();
            JOptionPane.showMessageDialog(frame, "Student registered successfully");
        }
    }

    //MODIFIES:srcImg
    //REQUIRES: w and h are position integers
    //EFFECTS: sets the dimensions of srcImg to w and h and returns it
    //code credit:https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}
