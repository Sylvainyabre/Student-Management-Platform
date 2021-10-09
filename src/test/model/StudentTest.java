package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class StudentTest {
    Student testStudent;
    Subject testSubject;
    GradeLevel testGradeLevel;

    @BeforeEach
    void setup() {
        testStudent = new Student("fake", "student");
        testSubject = new Subject("Test", 1);
        testGradeLevel = new GradeLevel("Test Grade Level");

    }

    @Test
    void testConstructor() {
        Student fakeStudent = new Student("fAkE", "StuDenT");
        assertEquals("Student", fakeStudent.getLastName());
        assertEquals("Fake", fakeStudent.getFirstName());
        assertNull(fakeStudent.getGradeLevel());
        assertEquals(0, fakeStudent.getId());
        assertEquals(0, fakeStudent.getGradeRecord().size());
    }
    @Test
    void testGetFullName(){
        assertEquals("Fake Student",testStudent.getFullName());
    }
    @Test
    void testSetId(){
        assertEquals(0,testStudent.getId());
        testStudent.setId(1234);
        assertEquals(1234,testStudent.getId());

    }
    @Test
    void testSetGradeLevel(){
        assertNull(testStudent.getGradeLevel());
        testStudent.setGradeLevel(testGradeLevel);
        assertEquals("Test Grade Level",testStudent.getGradeLevel().getName());
    }

    @Test
    void testAddSubject() {
        assertTrue(testStudent.getGradeRecord().isEmpty());
        testStudent.addSubject(testSubject);
        assertEquals(1, testStudent.getGradeRecord().size());
    }

    @Test
    void testUpdateSubjectGradeFirstMidtermSuccess() {
        testStudent.addSubject(testSubject);

        assertTrue(testStudent.updateSubjectGrade("Test", 16, 1));
        assertEquals(16, testStudent.getGradeRecord().get(0).getFirstMidtermGrade());
        assertEquals(0, testStudent.getGradeRecord().get(0).getSecondMidtermGrade());
        assertEquals(0, testStudent.getGradeRecord().get(0).getFinalExamGrade());


    }
    @Test
    void testUpdateSubjectGradeSecondMidtermSuccess(){
        testStudent.addSubject(testSubject);
        testStudent.updateSubjectGrade("test", 15, 2);
        assertEquals(0, testStudent.getGradeRecord().get(0).getFirstMidtermGrade());
        assertEquals(15, testStudent.getGradeRecord().get(0).getSecondMidtermGrade());
        assertEquals(0, testStudent.getGradeRecord().get(0).getFinalExamGrade());

    }
    @Test
    void testUpdateSubjectGradeFinalExamSuccess(){
        testStudent.addSubject(testSubject);
        testStudent.updateSubjectGrade("TEST", 18, 3);
        assertEquals(0, testStudent.getGradeRecord().get(0).getFirstMidtermGrade());
        assertEquals(0, testStudent.getGradeRecord().get(0).getSecondMidtermGrade());
        assertEquals(18, testStudent.getGradeRecord().get(0).getFinalExamGrade());

    }
    @Test
    void testUpdateSubjectGradeFail(){
        Subject subject = new Subject("second subject", 2);
        testStudent.addSubject(testSubject);
        testStudent.addSubject(subject);
        assertFalse(testStudent.updateSubjectGrade("Second", 18, 2));
        assertFalse(testStudent.updateSubjectGrade("test subject", 17, 3));
        assertFalse(testStudent.updateSubjectGrade("test subject", 17, 4));
        assertFalse(testStudent.updateSubjectGrade("test subject", 17, 0));
        assertEquals(0,testStudent.getGradeRecord().get(0).getFirstMidtermGrade());
        assertEquals(0,testStudent.getGradeRecord().get(0).getSecondMidtermGrade());
        assertEquals(0,testStudent.getGradeRecord().get(0).getFinalExamGrade());

        assertEquals(0,testStudent.getGradeRecord().get(1).getFirstMidtermGrade());
        assertEquals(0,testStudent.getGradeRecord().get(1).getSecondMidtermGrade());
        assertEquals(0,testStudent.getGradeRecord().get(1).getFinalExamGrade());



    }
    @Test
    void testGetOverallGrade(){
        Subject subject = new Subject("second subject", 2);
        Subject thirdSubject = new Subject("Physics",5);
        testStudent.addSubject(testSubject);
        testStudent.addSubject(subject);
        testStudent.addSubject(thirdSubject);
        testStudent.updateSubjectGrade("test",12,1);
        testStudent.updateSubjectGrade("test",13,2);
        testStudent.updateSubjectGrade("test",8,3);

        testStudent.updateSubjectGrade("seconD Subject", 15, 1);
        testStudent.updateSubjectGrade("seconD Subject", 10, 2);
        testStudent.updateSubjectGrade("seconD Subject", 17, 3);

        testStudent.updateSubjectGrade("physics",19,1);
        testStudent.updateSubjectGrade("physics",16,2);
        testStudent.updateSubjectGrade("physics",18.5,3);
        assertEquals(((12 * 0.25 + 13 * 0.25 + 8 * 0.5) *1
                        +(15 * 0.25 + 10 * 0.25 + 17 * 0.5)*2
                        + (19 * 0.25 + 16 * 0.25 + 18.5 * 0.5) *5) / 8,
                testStudent.getOverallGrade());



    }
}