package model;

import java.util.ArrayList;
import java.util.Objects;


public class Student {
    private String firstName;
    private String lastName;
    private int id;
    private ArrayList<Subject> gradeRecord;
    private GradeLevel gradeLevel;

    //constructor
    public Student(String first, String last) {
        this.firstName = capitalizeName(first);
        this.lastName = capitalizeName(last);
        this.gradeLevel = null;
        this.id = 0;
        this.gradeRecord = new ArrayList<>();

    }


    //EFFECTS:returns firstName
    public String getFirstName() {
        return firstName;
    }


    //EFFECTS:Returns LastName
    public String getLastName() {
        return lastName;
    }


    //EFFECTS:Returns a firstName lastName in one string
    public String getFullName() {
        return firstName + " " + lastName;
    }

    //MODIFIES:this
    //REQUIRES: subject not already in gradeRecord
    //EFFECTS: Adds subject to the gradeRecord
    public void addSubject(Subject subject) {
        gradeRecord.add(subject);
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public double getOverallGrade() {
        ArrayList<Subject> record = this.getGradeRecord();
        int totalCredits = record.stream().map(Subject::getWeight).reduce(0, Integer::sum);
        double sumOfRawGrades = record.stream().map(Subject::computeRawGrade).reduce(0.0, Double::sum);
        return sumOfRawGrades / (double) totalCredits;
    }
    //MODIFIES:
    //REQUIRES:
    //EFFECTS:

    public GradeLevel getGradeLevel() {
        return gradeLevel;
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public ArrayList<Subject> getGradeRecord() {
        return gradeRecord;
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public void setGradeLevel(GradeLevel gradeLevel) {
        this.gradeLevel = gradeLevel;
    }
    //MODIFIES:
    //REQUIRES:
    //EFFECTS:

    public long getId() {
        return id;
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public boolean updateSubjectGrade(String subjectName, double grade, int examNumber) {

        for (Subject subject : gradeRecord) {
            if (Objects.equals(subject.getName().toLowerCase(), subjectName.toLowerCase())) {
                changeSubjectGrade(subject, grade, examNumber);
                return true;

            }
        }
        return false;
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public static void changeSubjectGrade(Subject subject, double grade, int examNumber) {
        if (examNumber == 1) {
            subject.setFirstMidtermGrade(grade);

        }
        if (examNumber == 2) {
            subject.setSecondMidtermGrade(grade);

        }
        if (examNumber == 3) {
            subject.setFinalExamGrade(grade);

        }

    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public static String capitalizeName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public void setId(int id) {
        this.id = id;
    }
}
