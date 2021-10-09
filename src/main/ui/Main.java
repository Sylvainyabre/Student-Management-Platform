package ui;

import model.GradeLevel;
import model.Student;
import model.Subject;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GradeLevel gradeLevel = new GradeLevel("Test GradeLevel");
        Student firstStudent = new Student("Mario", "Super");
        Student secondStudent = new Student("Pat", "Stan");
        Student thirdStudent = new Student("Stan", "Pat");
        Student fourthStudent = new Student("Zapata", "Watapa");
        Student fifthStudent = new Student("Zipita", "Wotapa");
        gradeLevel.registerStudent(firstStudent);
        gradeLevel.registerStudent(secondStudent);
        gradeLevel.registerStudent(thirdStudent);
        gradeLevel.registerStudent(fourthStudent);
        gradeLevel.registerStudent(fifthStudent);

    }
}
