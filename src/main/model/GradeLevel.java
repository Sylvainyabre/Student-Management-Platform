package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// represents a class/grade having a name, a list of registered students,
// a maximum capacity and predefined list of subjects
public class GradeLevel {
    //maximum number of students that can be registered
    public static final int MAX_CAPACITY = 75;
    //all students registered in this class will be taking these subjects
    public static final KeyValuePair[] SUBJECT_NAMES = {new KeyValuePair("Mathematics", 5),
            new KeyValuePair("Biology", 3), new KeyValuePair("Physics", 5),
            new KeyValuePair("Chemistry", 5), new KeyValuePair("English", 3), new KeyValuePair("French", 3),
            new KeyValuePair("Physical Education", 1), new KeyValuePair("Geography", 1), new KeyValuePair("History", 1),
            new KeyValuePair("Philosophy", 2)};

    private String name;
    private ArrayList<Student> students;

    //Constructor
    //EFFECTS: creates a class named name with an empty list of student
    public GradeLevel(String name) {
        this.name = name;
        students = new ArrayList<>(); // initializing the list of students


    }


    //EFFECTS: returns a list of students sorted from highest to lowest GPA
    public ArrayList<Student> getStudentsRanking() {
        students.sort(Comparator.comparing(Student::getOverallGrade));// sorting students based on GPA
        Collections.reverse(students);

        return students;
    }


    //EFFECTS:returns all the students in a class
    public ArrayList<Student> getStudents() {
        return students;
    }


    //EFFECTS:returns the name of the student's class(this.name)
    public String getName() {
        return name;
    }


    //EFFECTS:returns the number of spots remaining in the class (MAX_CAPACITY - students.size())
    public int getRemainingNumberOfSpots() {
        return MAX_CAPACITY - students.size();
    }

    //MODIFIES:this
    //EFFECTS:removes the student having the provided id and returns true,
             //returns false if no student has the given id
    public boolean removeStudentFromClass(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return students.remove(student);
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: if class not full, adds student to the students in the class and
               //sets the student's id to the current size of the student list + 1
               //sets the student's subjects and returns true.
               //if class is full, returns false
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

    //MODIFIES:this
    //EFFECTS: adds all the elements in SUBJECT_NAMES to the student's list of subjects
    private void setStudentSubjects(Student student) {
        for (KeyValuePair keyValue : SUBJECT_NAMES) {
            student.addSubject(new Subject(keyValue.getKey(), keyValue.getValue()));
        }
    }


    //REQUIRES: id is an integer >0
    //EFFECTS: if there is a student in this class with the provided id, return it
    //               // returns null if no student is found with this id.
    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // EFFECTS: returns this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("students", studentsToJson());
        return json;
    }


    //EFFECTS: returns students in this Gradelevel as a JSON array
    private JSONArray studentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Student student : students) {
            jsonArray.put(student.toJson());
        }

        return jsonArray;
    }
}
