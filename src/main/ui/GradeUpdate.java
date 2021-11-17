package ui;

import model.GradeLevel;
import model.Student;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
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

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    private void updateGrade() {
        try {
            String gradeBuffer = gradeField.getText();
            double enteredGrade = Double.parseDouble(gradeBuffer);
            String idBuffer = idField.getText();
            if (enteredGrade > 20 || enteredGrade < 0) {
                JOptionPane.showMessageDialog(frame, "Grade should be in range [0,20]!");
            } else {
                int studentId = Integer.parseInt(idBuffer);
                int gradeIndex = gradeChoiceCombo.getSelectedIndex();
                subjectName = (String) subjectCombo.getSelectedItem();
                selectedGrade = frame.getClasses().get(gradeIndex + 7);
                executeUpdate(enteredGrade, studentId, gradeIndex + 1);
                // index starts at 0, so add 1 to have indexes [1,2,3]
            }


        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "grade should be in [0,20] and ID greater than 0");
        }
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    private void executeUpdate(double enteredGrade, int studentId, int gradeIndex) {
        Student student;
        if (selectedGrade == null) {
            JOptionPane.showMessageDialog(frame, "The chosen class could not be found");
        } else {
            student = selectedGrade.findStudentById(studentId);
            if (student == null) {
                JOptionPane.showMessageDialog(frame, "No student found with this ID");
            } else {
                boolean isUpdateSuccessful = student.updateSubjectGrade(subjectName, enteredGrade, gradeIndex);
                if (isUpdateSuccessful) {
                    JOptionPane.showMessageDialog(frame, "grade updated successfully");
                }
            }
        }
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        updateGrade();
    }
}

