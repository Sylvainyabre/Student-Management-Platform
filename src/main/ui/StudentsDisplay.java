package ui;

import model.GradeLevel;
import model.Student;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

//Code here has been inspired by this tutorial: https://www.javatpoint.com/java-jtable
public class StudentsDisplay extends JPanel implements ListSelectionListener {
    private MainFrame frame;
    private String[][] data = new String[GradeLevel.MAX_CAPACITY][4];
    //private String[] gradeStrings = {"grade 7", "grade 8", "grade 9", "grade 10", "grade 11", "grade 12"};
    private String[] columns = {"ID", "First Name", "Last Name", "GPA (/20)"};
    private HashMap<Integer, GradeLevel> classes;
    private GradeLevel selectedGradeLevel;
    private JTable table;
    private ListSelectionModel selectionModel;
    private int[] selectedRow;

    public StudentsDisplay(MainFrame frame, GradeLevel gradeLevel) {
        this.frame = frame;
        this.selectedGradeLevel = gradeLevel;
        classes = frame.getClasses();

        table = new JTable(data, columns);

        table.setCellSelectionEnabled(true);
        selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(this);
        setup();
        selectedRow = table.getSelectedRows();
        setTableData();


    }

    private void setup() {
        table.setFillsViewportHeight(true);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setGridColor(Color.LIGHT_GRAY);
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane pane = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 55, 15));


        this.setBorder(BorderFactory.createEmptyBorder(50, 5, 5, 5));
        this.setBounds(400, 300, 100, 100);
        this.setBackground(new Color(50, 123, 168));;

        this.add(pane);

        frame.add(this, BorderLayout.EAST);
    }

    //EFFECT:
    //MODIFIES:
    private void setTableData() {
        if (selectedGradeLevel != null) {
            ArrayList<Student> students = selectedGradeLevel.getStudents();
            for (Student student : students) {

                addStudentToArray(student, data);
            }
        }


    }

    //EFFECT:
    //MODIFIES:
    private void addStudentToArray(Student student, String[][] arr) {
        arr[student.getId() - 1][0] = String.valueOf(student.getId());
        arr[student.getId() - 1][1] = student.getFirstName();
        arr[student.getId() - 1][2] = student.getLastName();
        arr[student.getId() - 1][3] = String.valueOf(student.getOverallGrade());
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

        //Student student =  selectedGradeLevel.findStudentById((int)selectedRow[1]);
        System.out.println(Arrays.toString(selectedRow));
    }
}
