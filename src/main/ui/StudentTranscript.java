package ui;

import model.Student;
import model.Subject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// A class representing the transcript of a student
public class StudentTranscript {
    private String[][] transcriptData;
    private String[] columns = {"Subject", "Weight", "Midterm 1", "Midterm 2", "Final Grade"};

    //Creates a new transcript belonging to student to be displayed on frame
    public StudentTranscript(MainFrame frame, Student student) {

        transcriptData = new String[10][5];
        setTranscriptData(student);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 4));
        JTable table = new JTable(transcriptData, columns);

        table.setFillsViewportHeight(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane pane = new JScrollPane(table);

        panel.add(new JLabel(student.getFullName()));
        panel.add(new JLabel(student.getGradeLevel().getName()));
        panel.add(new JLabel("GPA: " + truncateGrade(student.getOverallGrade())));

        panel.add(pane);
        JOptionPane.showMessageDialog(frame, panel);

    }

    //MODIFIES: grade
    // EFFECTS: truncate the number into two decimal places and cast to float and returns it
    //REQUIRES: grade>0
    private float truncateGrade(double grade) {
        return (float) (Math.floor(grade * 100) / 100);
    }

    //MODIFIES: this
    // EFFECTS: populates the rows of the table with the info of each subject
    private void setTranscriptData(Student student) {
        ArrayList<Subject> gradeRecord = student.getGradeRecord();

        for (Subject subject : gradeRecord) {
            float grade1 = truncateGrade(subject.getFirstMidtermGrade());
            float grade2 = truncateGrade(subject.getSecondMidtermGrade());
            float grade3 = truncateGrade(subject.getSubjectGrade());
            transcriptData[gradeRecord.indexOf(subject)][0] = subject.getName();
            transcriptData[gradeRecord.indexOf(subject)][1] = String.valueOf(subject.getWeight());
            transcriptData[gradeRecord.indexOf(subject)][2] = String.valueOf(grade1);
            transcriptData[gradeRecord.indexOf(subject)][3] = String.valueOf(grade2);
            transcriptData[gradeRecord.indexOf(subject)][4] = String.valueOf(grade3);
        }

    }
}
