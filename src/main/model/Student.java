package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

// represents a student with a first name, a last name, and id number,
// class registered in and subjects that the student is taking
public class Student {
    private String firstName;
    private String lastName;
    private int id;
    private ArrayList<Subject> gradeRecord; // subjects the student is taking
    private GradeLevel gradeLevel; // class of the student

    //constructor
    //EFFECTS: creates a new student with first name  and last name ,
    // sets the student's grade/class to null, id to 0 and gradeRecord to an empty list.
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


    //REQUIRES: gradeRecord not empty
    //EFFECTS:returns the student's overall GPA from all the courses taken,
    // GPA is calculated as sum(subject grade * subject weight)/(sum of weights)
    public double getOverallGrade() {
        ArrayList<Subject> record = this.getGradeRecord();
        int totalCredits = record.stream().map(Subject::getWeight).reduce(0, Integer::sum);
        double sumOfRawGrades = record.stream().map(Subject::computeRawGrade).reduce(0.0, Double::sum);
        return sumOfRawGrades / (double) totalCredits;
    }


    //EFFECTS: returns the class of the student
    public GradeLevel getGradeLevel() {
        return gradeLevel;
    }


    //EFFECTS: returns student's gradeRecord(subjects with their grades
    public ArrayList<Subject> getGradeRecord() {
        return gradeRecord;
    }

    //MODIFIES:this
    //EFFECTS: sets the student's class to gradeLevel
    public void setGradeLevel(GradeLevel gradeLevel) {
        EventLog.getInstance().logEvent(new Event("Set " + gradeLevel.getName()
                + " for " + this.getFullName()));
        this.gradeLevel = gradeLevel;
    }


    //EFFECTS:returns the student's id
    public int getId() {
        return id;
    }

    //MODIFIES: this
    //REQUIRES: grade >= 0 and examNumber is one of (1,2,3)
    //EFFECTS: finds the subject in the student's subjects named subjectName
    // and sets its midterm1 grade to grade if examNumber is 1 and return true
    // or if examNumber is 2, set midterm2 grade to grade and return true
    //else if examNumber is 3 set finalExam grade to grade and return true,
    // if no subject exists with the given name, return false
    public boolean updateSubjectGrade(String subjectName, double grade, int examNumber) {

        for (Subject subject : gradeRecord) {
            if (Objects.equals(subject.getName().toLowerCase(), subjectName.toLowerCase())) {
                changeSubjectGrade(subject, grade, examNumber);
                return true;

            }
        }
        return false;
    }

    //MODIFIES: subject
    //REQUIRES:grade >= 0 and examNumber is one of (1,2,3)
    //EFFECTS: sets its midterm1 grade to grade if examNumber is
    // or if examNumber is 2, set midterm2 grade to grade and return true
    //else if examNumber is 3 set finalExam grade to grade and return true,
    // if no subject exists with given name or if exam number is not one of (1,2,3),

    public void changeSubjectGrade(Subject subject, double grade, int examNumber) {
        if (examNumber == 1) {
            subject.setFirstMidtermGrade(grade);
            EventLog.getInstance().logEvent(new Event("Midterm 1 grade updated for " + this.getFullName()));

        }
        if (examNumber == 2) {
            subject.setSecondMidtermGrade(grade);
            EventLog.getInstance().logEvent(new Event("Midterm 2 grade updated for " + this.getFullName()));

        }
        if (examNumber == 3) {
            subject.setFinalExamGrade(grade);
            EventLog.getInstance().logEvent(new Event("Final exam grade updated for " + this.getFullName()));

        }

    }

    //MODIFIES: name
    //EFFECTS: turns the first letter into a Capital letter and keep the rest in lower case,
    // and then returns the transformed string
    public static String capitalizeName(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    //MODIFIES: this
    //REQUIRES: id is of type int and is positive and non-zero
    //EFFECTS: sets the student's id to the provided id
    public void setId(int id) {
        this.id = id;
    }


    //EFFECTS: transform a student into a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("first", firstName);
        json.put("last", lastName);
        json.put("id", id);
        json.put("gradeRecord", gradeRecordToJson());
        return json;
    }

    //EFFECTS: transform a student's subjects  into a JSONArray
    public JSONArray gradeRecordToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Subject subject : gradeRecord) {
            jsonArray.put(subject.toJson());
        }

        return jsonArray;
    }


}
