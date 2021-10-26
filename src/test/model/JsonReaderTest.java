package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            GradeLevel gradeLevel = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testReaderEmptyGradeLevel() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGradeLevel.json");
        try {
            GradeLevel grade = reader.read();
            assertEquals("empty grade", grade.getName());
            assertEquals(0, grade.getStudents().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderGeneralGradeLevel() {
        JsonReader reader = new JsonReader("./data/testGrade.json");
        try {
            GradeLevel grade = reader.read();
            ArrayList<Student> students = grade.getStudents();
            assertEquals("test grade", grade.getName());
            assertEquals(3, students.size());
            checkStudent("Student 1",1, students.get(0));
            checkStudent("Student 2",2, students.get(1));
            checkSubject("Mathematics",5,students.get(0).getGradeRecord().get(0));
            checkSubject("Biology",3,students.get(0).getGradeRecord().get(1));
            checkSubject("Mathematics",5,students.get(1).getGradeRecord().get(0));
            checkSubject("Physics",5,students.get(1).getGradeRecord().get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
