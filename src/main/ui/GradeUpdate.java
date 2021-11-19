package ui;

import model.GradeLevel;
import model.Student;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//A class  tha represents a student update panel
public class GradeUpdate extends JPanel implements ActionListener {
    private JComboBox<String> subjectCombo;
    private JComboBox<String> examChoiceCombo;
    private MainFrame frame;
    private String[] subjectNames = {"Mathematics", "Biology", "Physics", "Chemistry",
            "English", "French", "Physical Education",
            "Geography", "History", "Philosophy"};
    private String[] gradeStrings = {"grade 7", "grade 8", "grade 9", "grade 10", "grade 11", "grade 12"};
    private JButton updateButton;
    private JTextField idField;
    private JTextField gradeField;
    private String subjectName;
    private JLabel idLabel;
    private JLabel gradeLabel;
    private GradeLevel selectedGrade;
    private JComboBox<String> gradeChoiceCombo;
    private int gradeIndex;
    private int examIndex;

    //EFFECTS: creates a student grade update panel and adds its components
    public GradeUpdate(MainFrame frame, JPanel panel) {
        this.frame = frame;
        initializeVariables();
        this.setLayout(new GridLayout(4, 4, 10, 5));
        this.setBorder(new LineBorder(Color.CYAN, 4));
        this.setBackground(new Color(172, 190, 191));
        this.add(idLabel);
        this.add(idField);
        this.add(gradeLabel);
        this.add(gradeField);
        this.add(subjectCombo);
        this.add(examChoiceCombo);
        this.add(gradeChoiceCombo);
        this.add(updateButton);
        panel.add(this);


    }

    //MODIFIES: this
    //EFFECTS: assigns gradeLevel and chosenExam to their comboBox selections
    private void updateSelection() {
        subjectName = (String) subjectCombo.getSelectedItem();
        examIndex = examChoiceCombo.getSelectedIndex() + 1;
        gradeIndex = gradeChoiceCombo.getSelectedIndex();
        selectedGrade = frame.getClasses().get(gradeIndex + 7);

    }

    //MODIFIES: this
    //EFFECTS: initializes all the class-level variables
    private void initializeVariables() {
        subjectCombo = new JComboBox<>(subjectNames);
        examChoiceCombo = new JComboBox<>(new String[]{"Midterm 1", "Midterm 2", "Final Exam"});
        gradeChoiceCombo = new JComboBox<>(gradeStrings);
        updateButton = new JButton("Update Grade");
        updateButton.addActionListener(this);
        gradeField = new JTextField();
        idField = new JTextField();
        idLabel = new JLabel("Student ID:");
        gradeLabel = new JLabel("New Grade:");
        subjectName = null;
    }

    //MODIFIES:this
    //EFFECTS: if grade is in range [0,20] find a student with the given id and update their grade
    // show wrong input popup otherwise
    private void updateGrade() {
        updateSelection();
        try {
            String gradeBuffer = gradeField.getText();
            double enteredGrade = Double.parseDouble(gradeBuffer);
            String idBuffer = idField.getText();
            if (enteredGrade > 20 || enteredGrade < 0) {
                JOptionPane.showMessageDialog(frame, "Grade should be in range [0,20]!");
            } else {
                int studentId = Integer.parseInt(idBuffer);
                executeUpdate(enteredGrade, studentId, examIndex);
                idField.setText("");
                gradeField.setText("");
                // index starts at 0, so add 1 to have indexes [1,2,3]
            }


        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "grade should be in [0,20] and ID greater than 0");
        }
    }

    //MODIFIES: nothing
    //REQUIRES: enteredGrade is of type double, not a letter, studentId is >0
    //EFFECTS:
    private void executeUpdate(double enteredGrade, int studentId, int examIndex) {
        Student student;
        if (selectedGrade == null) {
            JOptionPane.showMessageDialog(frame, "The chosen class could not be found");
        } else {
            student = selectedGrade.findStudentById(studentId);
            if (student == null) {
                JOptionPane.showMessageDialog(frame, "No student found with this ID");
            } else {
                boolean isUpdateSuccessful = student.updateSubjectGrade(subjectName, enteredGrade, examIndex);

                if (isUpdateSuccessful) {
                    JOptionPane.showMessageDialog(frame, "grade updated successfully");
                    frame.revalidate();
                    frame.repaint();
                }
            }
        }
    }


    //EFFECTS: if upgrade button is pressed, executes grade update
    @Override
    public void actionPerformed(ActionEvent e) {
        updateGrade();
    }
}

