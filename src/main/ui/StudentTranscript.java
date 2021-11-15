package ui;

import model.Student;
import model.Subject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StudentTranscript {
    private Student student;
    private MainFrame frame;
    private String[][] transcriptData;
    private String[] columns = {"Subject", "Weight", "Midterm 1", "Midterm 2", "Final Grade"};

    public StudentTranscript(MainFrame frame, Student student) {
        this.student = student;
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
        panel.add(new JLabel("GPA: " + student.getOverallGrade()));

        panel.add(pane);
        JOptionPane.showMessageDialog(frame, panel);

    }

    private void setTranscriptData(Student student) {
        System.out.println(student.getFullName());
        ArrayList<Subject> gradeRecord = student.getGradeRecord();

        for (Subject subject : gradeRecord) {
            transcriptData[gradeRecord.indexOf(subject)][0] = subject.getName();
            transcriptData[gradeRecord.indexOf(subject)][1] = String.valueOf(subject.getWeight());
            transcriptData[gradeRecord.indexOf(subject)][2] = String.valueOf(subject.getFirstMidtermGrade());
            transcriptData[gradeRecord.indexOf(subject)][3] = String.valueOf(subject.getSecondMidtermGrade());
            transcriptData[gradeRecord.indexOf(subject)][4] = String.valueOf(subject.getSubjectGrade());


        }

    }

}
