package persistence;

//my models

import model.GradeLevel;
import model.Subject;
import model.Student;

//java libraries
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;


//Class representing a reader that reads GradeLevels from JSON data stored in file
//CREDITS: All the code in this class are copied from the Demo repository
//REPO LINK https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }


    // EFFECTS: reads GradeLevels from file and returns them;
    // throws IOException if an error occurs while reading data from file
    public GradeLevel read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGradeLevel(jsonObject);
    }

    //EFFECT:parse GradeLevel from JSOn object
    private GradeLevel parseGradeLevel(JSONObject jsonObject) {
        String gradeName = jsonObject.getString("name");
        JSONArray studentsJsonArray = jsonObject.getJSONArray("students");
        //parseStudents(studentsJsonArray);
        GradeLevel gradeLevel = new GradeLevel(gradeName);
        addStudents(gradeLevel, jsonObject);
        return gradeLevel;
    }

//    //EFFECTS: parses students from a JSONArray and adds them to student
//   private void parseStudents(JSONArray jsonArray){
//        ArrayList<Student> students = new ArrayList<>();
//        for(Object object:jsonArray){
//            students.add(parseStudent(object));
//        }
//   }

    // MODIFIES: grade
    // EFFECTS: parses students from JSON object and adds them to grade
    private void addStudents(GradeLevel grade, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("students");
        for (Object json : jsonArray) {
            JSONObject nextStudent = (JSONObject) json;
            addStudent(grade, nextStudent);
        }
    }

    // MODIFIES: gradeLevel
    // EFFECTS: parses student from JSON object and adds it to gradeLevel
    private void addStudent(GradeLevel gradeLevel, JSONObject jsonObject) {
        String firstName = jsonObject.getString("first");
        String lastName = jsonObject.getString("last");
        int id = jsonObject.getInt("id");
        Student student = new Student(firstName, lastName);
        student.setId(id);
        student.setGradeLevel(gradeLevel);
        ArrayList<Subject> parseRecord = parseGradeRecord(jsonObject);
        gradeLevel.registerStudent(student);

        for (Subject sub : parseRecord) {

            student.updateSubjectGrade(sub.getName(), sub.getFirstMidtermGrade(), 1);
            student.updateSubjectGrade(sub.getName(), sub.getSecondMidtermGrade(), 2);
            student.updateSubjectGrade(sub.getName(), sub.getFinalExamGrade(), 3);
        }


    }

    // MODIFIES: student
    // EFFECTS: parses Subjects from JSON object and adds it to student
    public ArrayList<Subject> parseGradeRecord(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("gradeRecord");
        ArrayList<Subject> parseSubjects = new ArrayList<>();
        for (Object json : jsonArray) {
            JSONObject nextSubject = (JSONObject) json;
            parseSubjects.add(parseSubject(nextSubject));
        }
        return parseSubjects;
    }

    // MODIFIES: student
    // EFFECTS: parses information, creates a new subject and adds it to student
    public Subject parseSubject(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int weight = jsonObject.getInt("weight");
        double firstMidtermGrade = jsonObject.getDouble("firstMidtermGrade");
        double secondMidtermGrade = jsonObject.getDouble("secondMidtermGrade");
        double finalExamGrade = jsonObject.getDouble("finalExamGrade");
        Subject subject = new Subject(name, weight);
        subject.setFirstMidtermGrade(firstMidtermGrade);
        subject.setSecondMidtermGrade(secondMidtermGrade);
        subject.setFinalExamGrade(finalExamGrade);


        return subject;

    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }
}
