package model;


import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            GradeLevel grade = new GradeLevel("Test Grade");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testWriterEmptyGrade() {
        try {
            GradeLevel grade = new GradeLevel("test grade");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGradeLevel.json");
            writer.open();
            writer.write(grade);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGradeLevel.json");
            GradeLevel readGrade = reader.read();
            checkGradeLevel("test grade", readGrade);
            assertEquals(0, grade.getStudents().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGrade() {
        try {
            GradeLevel fakeGrade = new GradeLevel("fake grade");
            Student Sylvain = new Student("Sylvain", "Yabre");
            Student fake = new Student("fake", "Student");
            Student Alain = new Student("Alain", "Yabre");

            fakeGrade.registerStudent(new Student("Sylvain", "Yabre"));
            fakeGrade.registerStudent(new Student("fake", "Student"));
            fakeGrade.registerStudent(new Student("Alain", "Yabre"));
            JsonWriter writer = new JsonWriter("./data/testGrade.json");
            writer.open();
            writer.write(fakeGrade);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGrade.json");
            GradeLevel gradeFromFile = reader.read();
            checkGradeLevel("fake grade", gradeFromFile);

            ArrayList<Student> students = fakeGrade.getStudents();
            assertEquals(3, students.size());
            checkStudent("Sylvain", 1, students.get(0));
            checkStudent("Fake", 2, students.get(1));
            checkStudent("Alain", 3, students.get(2));
            checkSubject("Mathematics", 5, students.get(0).getGradeRecord().get(0));
            checkSubject("Mathematics", 5, students.get(1).getGradeRecord().get(0));
            checkSubject("Mathematics", 5, students.get(2).getGradeRecord().get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
