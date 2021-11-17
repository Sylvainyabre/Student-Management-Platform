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
            "Geography", "History", "Philosophy"};


    //EFFECT:
    //MODIFIES
    public SidePanel(MainFrame frame) {
        this.frame = frame;
        this.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
        this.setLayout(new GridLayout(4, 0));
        this.setBackground(Color.WHITE);
        addLeftPanel();
        frame.add(this, BorderLayout.WEST);
        frame.add(imagePanel,BorderLayout.CENTER);


    }

    //EFFECT:
    //MODIFIES:
    private void addLeftPanel() {
        initializeVariables();
        addActionListenerToButtons();
        addComponents();
        addSecondPanel();
    }

    //EFFECT:
    //MODIFIES:
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

    //EFFECT:
    //MODIFIES:
    //REQUIRES:
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

    //EFFECT:
    //MODIFIES:
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

    //EFFECT:
    //MODIFIES:
    //REQUIRES:
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

    //EFFECT:
    //MODIFIES:
    //REQUIRES:
    private void updateSelection() {
        gradeLevelSelection = comboBox.getSelectedIndex();
        classes = frame.getClasses();
        gradeLevel = classes.get(gradeLevelSelection + 7);
    }

    //EFFECT:
    //MODIFIES:
    //REQUIRES:
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


    //EFFECT:
    //MODIFIES:
    //REQUIRES:
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

    //EFFECT:
    //MODIFIES:
    //REQUIRES:
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

    //EFFECT:
    //MODIFIES:
    //REQUIRES:
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

    //EFFECT:
    //MODIFIES:
    //REQUIRES:
    private void executeStudentDisplay() {
        try {
            handleStudentDisplay();

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage());
        }
    }

    //EFFECT:
    //MODIFIES:
    //REQUIRES:
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

    //EFFECT:
    //MODIFIES:
    //REQUIRES:
    private void handleStudentDisplay() {
        updateSelection();

        frame.remove(studentsDisplay);
        studentsDisplay = new StudentsDisplay(frame, gradeLevel);
        frame.revalidate();
        frame.repaint();
    }

    //EFFECT:
    //MODIFIES:
    //REQUIRES:
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

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
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
