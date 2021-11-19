package ui;

import model.GradeLevel;
import model.Student;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

//Code here has been inspired by this tutorial: https://www.javatpoint.com/java-jtable
//Class representing a Student transcript display
public class StudentsDisplay extends JPanel implements ListSelectionListener {
    private MainFrame frame;
    private String[][] data = new String[GradeLevel.MAX_CAPACITY][4];
    private String[] columns = {"ID", "First Name", "Last Name", "GPA (/20)"};
    private HashMap<Integer, GradeLevel> classes;
    private GradeLevel selectedGradeLevel;
    private JTable table;
    private ListSelectionModel selectionModel;
    private int selectedRow;


    // Creates a new StudentDisplay and sets its properties
    public StudentsDisplay(MainFrame frame, GradeLevel gradeLevel) {
        this.frame = frame;
        this.selectedGradeLevel = gradeLevel;
        classes = frame.getClasses();
        table = new JTable(data, columns);

        table.setCellSelectionEnabled(true);
        selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(this);
        setup();
        selectedRow = table.getSelectedRow();
        setTableData();


    }

    //MDOFIES:this
    // EFFECTS: sets table properties and then add table to frame
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
        this.setBackground(new Color(50, 123, 168));
        ;

        this.add(pane);

        frame.add(this, BorderLayout.EAST);
    }


    //MODIFIES: this
    //EFFECT: if the selected gradeLevel is not null, get the students in the selected grade and
    // add them to table data to be displayed
    private void setTableData() {
        if (selectedGradeLevel != null) {
            ArrayList<Student> students = selectedGradeLevel.getStudents();
            for (Student student : students) {

                addStudentToArray(student, data);
            }
        }


    }

    //MODIFIES:arr
    //EFFECT:fills the row in arr corresponding to the student's id with the student's
    // ID, firstName, lastName and GPA
    private void addStudentToArray(Student student, String[][] arr) {
        // we subtract 1 from  id to start from 0 because id starts from 1
        arr[student.getId() - 1][0] = String.valueOf(student.getId());
        arr[student.getId() - 1][1] = student.getFirstName();
        arr[student.getId() - 1][2] = student.getLastName();
        arr[student.getId() - 1][3] = String.valueOf(truncateGrade(student.getOverallGrade()));
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    //MODIFIES: grade
    // EFFECTS: truncate the number into two decimal places and cast to float and returns it
    //REQUIRES: grade>0
    private float truncateGrade(double grade) {
        float truncated = (float) (Math.floor(grade * 100) / 100);
        return truncated;
    }

}
