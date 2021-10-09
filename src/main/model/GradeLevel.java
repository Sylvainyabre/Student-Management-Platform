package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GradeLevel {
    public static final int MAX_CAPACITY = 75;
    public static final KeyValuePair[] SUBJECT_NAMES = {new KeyValuePair("Mathematics", 5),
            new KeyValuePair("Biology", 3), new KeyValuePair("Physics", 5),
            new KeyValuePair("Chemistry", 5), new KeyValuePair("English", 3), new KeyValuePair("French", 3),
            new KeyValuePair("Physical Education", 1), new KeyValuePair("Geography", 1), new KeyValuePair("History", 1),
            new KeyValuePair("Philosophy", 2)};

    private String name;
    private ArrayList<Student> students;


    public GradeLevel(String name) {
        this.name = name;
        students = new ArrayList<>();


    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public ArrayList<Student> getStudentsRanking() {
        students.sort(Comparator.comparing(Student::getOverallGrade));
        Collections.reverse(students);

        return students;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public ArrayList<Student> getStudentsAlphabeticallyOrdered() {
        students.sort(Comparator.comparing(Student::getLastName));
        return students;

    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public String getName() {
        return name;
    }


    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public int getRemainingNumberOfSpots() {
        return MAX_CAPACITY - students.size();
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public boolean removeStudentFromClass(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return students.remove(student);
            }
        }
        return false;
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public boolean registerStudent(Student student) {
        if (students.size() < MAX_CAPACITY) {
            setStudentSubjects(student);
            student.setId(students.size() + 1);
            student.setGradeLevel(this);
            students.add(student);
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    private void setStudentSubjects(Student student) {
        for (KeyValuePair keyValue : SUBJECT_NAMES) {
            student.addSubject(new Subject(keyValue.getKey(), keyValue.getValue()));
        }
    }


    //REQUIRES: there is a student in this grade level with the provided id
    //EFFECTS: Returns the student having the given id number
    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

}
