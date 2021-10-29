package persistence;

import model.GradeLevel;
import model.Student;
import model.Subject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkStudent(String name,int id, Student student) {
        assertEquals(name, student.getFirstName());
        assertEquals(id,student.getId());

    }
    protected void checkSubject(String name,int weight, Subject subject){
        assertEquals(name,subject.getName());
        assertEquals(weight,subject.getWeight());
    }
    protected void checkGradeLevel(String name, GradeLevel grade){
        assertEquals(name, grade.getName());
    }
}

