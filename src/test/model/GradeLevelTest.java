package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GradeLevelTest {
    GradeLevel gradeLevel;
    GradeLevel fullGradeLevel;

    @BeforeEach
    void setup() {
        gradeLevel = new GradeLevel("Test GradeLevel");
        fullGradeLevel = new GradeLevel("testing");

        for (int num = 0; num < 75; num++) {
            fullGradeLevel.registerStudent(new Student("Student", "Test" + Integer.toString(num)));
        }
    }

    @Test
    void testConstructor() {
        assertTrue(gradeLevel.getStudents().isEmpty());
        assertEquals("Test GradeLevel", gradeLevel.getName());

    }

    @Test
    void testRegisterStudentEmpty() {
        assertTrue(gradeLevel.getStudents().isEmpty());
        assertTrue(gradeLevel.registerStudent(new Student("Mario", "Super")));
        assertEquals(1, gradeLevel.getStudents().size());
        assertEquals(74, gradeLevel.getRemainingNumberOfSpots());
    }

    @Test
    void testRegisterStudentFull() {
        assertEquals(75, fullGradeLevel.getStudents().size());
        assertEquals(0, fullGradeLevel.getRemainingNumberOfSpots());
        assertFalse(fullGradeLevel.registerStudent(new Student("Mario", "Super")));
        assertEquals(75, fullGradeLevel.getStudents().size());
    }

    @Test
    void testRegisterStudentAlmostFull() {
        assertEquals(75, fullGradeLevel.getStudents().size());
        assertEquals(0, fullGradeLevel.getRemainingNumberOfSpots());
        assertTrue(fullGradeLevel.removeStudentFromClass(74));
        assertEquals(1, fullGradeLevel.getRemainingNumberOfSpots());
        assertTrue(fullGradeLevel.registerStudent(new Student("Mario", "Super")));
        assertEquals(0, fullGradeLevel.getRemainingNumberOfSpots());

    }

    @Test
    void testRemoveStudentFromClassFail() {
        assertEquals(75,fullGradeLevel.getStudents().size());
        assertFalse(fullGradeLevel.removeStudentFromClass(0));
        assertEquals(75,fullGradeLevel.getStudents().size());
        assertFalse(fullGradeLevel.removeStudentFromClass(76));
        assertEquals(75,fullGradeLevel.getStudents().size());
    }

    @Test
    void testRemoveStudentFromClass(){
        assertEquals(75,fullGradeLevel.getStudents().size());
        assertTrue(fullGradeLevel.removeStudentFromClass(1));
        assertEquals(74,fullGradeLevel.getStudents().size());
        assertTrue(fullGradeLevel.removeStudentFromClass(75));
        assertEquals(73,fullGradeLevel.getStudents().size());
        assertTrue(fullGradeLevel.removeStudentFromClass(50));
        assertEquals(72,fullGradeLevel.getStudents().size());
    }
    @Test
    void testGetStudentsAlphabeticallyOrderedMany() {
        Student firstStudent = new Student("Mario", "Super");
        Student secondStudent = new Student("Pat", "Stan");
        Student thirdStudent = new Student("Stan", "Pat");
        Student fourthStudent = new Student("Zapata", "Watapa");
        Student fifthStudent = new Student("Zipita", "Wotapa");
        Student sixthStudent = new Student("Wotapa", "aZepata");
        Student seventhStudent = new Student("Wotapa", "Zipita");
        Student eightStudent = new Student("Wotapa", "Zipota");
        Student ninthStudent = new Student("Wotapa", "Zepata");
        Student tenthStudent = new Student("Wotapa", "xZepata");
        Student elenventhStudent = new Student("Wotapa", "aZepata1");
        gradeLevel.registerStudent(firstStudent);
        gradeLevel.registerStudent(secondStudent);
        gradeLevel.registerStudent(thirdStudent);
        gradeLevel.registerStudent(fourthStudent);
        gradeLevel.registerStudent(fifthStudent);
        gradeLevel.registerStudent(sixthStudent);
        gradeLevel.registerStudent(seventhStudent);
        gradeLevel.registerStudent(eightStudent);
        gradeLevel.registerStudent(ninthStudent);
        gradeLevel.registerStudent(tenthStudent);
        gradeLevel.registerStudent(elenventhStudent);

        ArrayList<Student> orderedStudents = gradeLevel.getStudentsAlphabeticallyOrdered();
        assertEquals("Wotapa Azepata", orderedStudents.get(0).getFullName());
        assertEquals("Wotapa Azepata1", orderedStudents.get(1).getFullName());
        assertEquals("Stan Pat", orderedStudents.get(2).getFullName());
        assertEquals("Pat Stan", orderedStudents.get(3).getFullName());
        assertEquals("Mario Super", orderedStudents.get(4).getFullName());
        assertEquals("Zapata Watapa", orderedStudents.get(5).getFullName());
        assertEquals("Zipita Wotapa", orderedStudents.get(6).getFullName());
        assertEquals("Wotapa Xzepata", orderedStudents.get(7).getFullName());
        assertEquals("Wotapa Zepata", orderedStudents.get(8).getFullName());
        assertEquals("Wotapa Zipita", orderedStudents.get(9).getFullName());
        assertEquals("Wotapa Zipota", orderedStudents.get(10).getFullName());


    }

    @Test
    void testGetRemainingNumberOfSpotsEmpty() {
        assertEquals(75, gradeLevel.getRemainingNumberOfSpots());
    }

    @Test
    void testGetRemainingNumberOfSpotsNotEmpty() {
        Student firstStudent = new Student("Mario", "Super");
        Student secondStudent = new Student("Pat", "Stan");
        Student thirdStudent = new Student("Stan", "Pat");
        Student fourthStudent = new Student("Zapata", "Watapa");
        Student fifthStudent = new Student("Zipita", "Wotapa");
        Student sixthStudent = new Student("Wotapa", "aZepata");
        Student seventhStudent = new Student("Wotapa", "Zipita");
        Student eightStudent = new Student("Wotapa", "Zipota");
        Student ninthStudent = new Student("Wotapa", "Zepata");
        Student tenthStudent = new Student("Wotapa", "xZepata");
        Student elenventhStudent = new Student("Wotapa", "aZepata1");
        gradeLevel.registerStudent(firstStudent);
        gradeLevel.registerStudent(secondStudent);
        gradeLevel.registerStudent(thirdStudent);
        gradeLevel.registerStudent(fourthStudent);
        gradeLevel.registerStudent(fifthStudent);
        gradeLevel.registerStudent(sixthStudent);
        gradeLevel.registerStudent(seventhStudent);
        gradeLevel.registerStudent(eightStudent);
        gradeLevel.registerStudent(ninthStudent);
        gradeLevel.registerStudent(tenthStudent);
        gradeLevel.registerStudent(elenventhStudent);
        assertEquals(75 - 11, gradeLevel.getRemainingNumberOfSpots());
    }

    @Test
    void testGetRemainingNumberOfSpotsFull() {
        assertEquals(75, gradeLevel.getRemainingNumberOfSpots());
        for (int num = 0; num < 75; num++) {
            gradeLevel.registerStudent(new Student("Student", "Test" + Integer.toString(num)));
        }
        assertEquals(0, gradeLevel.getRemainingNumberOfSpots());
    }

    @Test
    void testGetStudentsRanking() {
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

        Student sixthStudent = new Student("Wotapa", "aZepata");
        for (Subject subject : firstStudent.getGradeRecord()) {
            subject.setFirstMidtermGrade(10);
            subject.setSecondMidtermGrade(13);
            subject.setFinalExamGrade(15);
        }
        for (Subject subject : secondStudent.getGradeRecord()) {
            subject.setFirstMidtermGrade(2);
            subject.setSecondMidtermGrade(10);
            subject.setFinalExamGrade(20);
        }
        for (Subject subject : thirdStudent.getGradeRecord()) {
            subject.setFirstMidtermGrade(12);
            subject.setSecondMidtermGrade(18);
            subject.setFinalExamGrade(14);
        }
        for (Subject subject : fourthStudent.getGradeRecord()) {
            subject.setFirstMidtermGrade(2);
            subject.setSecondMidtermGrade(10);
            subject.setFinalExamGrade(20);
        }
        for (Subject subject : fifthStudent.getGradeRecord()) {
            subject.setFirstMidtermGrade(20);
            subject.setSecondMidtermGrade(10);
            subject.setFinalExamGrade(20);
        }
        for (Subject subject : fifthStudent.getGradeRecord()) {
            subject.setFirstMidtermGrade(20);
            subject.setSecondMidtermGrade(10);
            subject.setFinalExamGrade(20);
        }

        ArrayList<Student> orderStudents = gradeLevel.getStudentsRanking();
        assertEquals(0, orderStudents.indexOf(fifthStudent));
        assertEquals(1, orderStudents.indexOf(thirdStudent));
        assertEquals(2, orderStudents.indexOf(firstStudent));
        assertEquals(3, orderStudents.indexOf(fourthStudent));
        assertEquals(4, orderStudents.indexOf(secondStudent));



    }

    @Test
    void testGetStudentByIdNonExistent() {
        assertNull(gradeLevel.findStudentById(1));
    }

    @Test
    void testGetStudentByIdExistent() {
        for (int num = 0; num < 75; num++) {
            gradeLevel.registerStudent(new Student("Student", "Test" + Integer.toString(num)));
        }
        assertEquals("Student Test0", gradeLevel.findStudentById(1).getFullName());
        assertEquals("Student Test50", gradeLevel.findStudentById(51).getFullName());
        assertEquals("Student Test74", gradeLevel.findStudentById(75).getFullName());

    }

    @Test
    void testGetStudentByIdOutOfBoundIndex() {
        for (int num = 0; num < 75; num++) {
            gradeLevel.registerStudent(new Student("Student", "Test" + Integer.toString(num)));
        }
        assertNull(gradeLevel.findStudentById(0));
        assertNull(gradeLevel.findStudentById(76));
    }

}
