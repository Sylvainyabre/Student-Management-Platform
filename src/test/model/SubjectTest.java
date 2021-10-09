package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SubjectTest {
    Subject testSubject;

    @BeforeEach
    void setup() {
        testSubject = new Subject("Mathematics", 5);
    }

    @Test
    void testConstructor() {
        Subject subject = new Subject("Test subject", 1);
        assertEquals(1, subject.getWeight());
        assertEquals("Test subject", subject.getName());
        assertEquals(0, subject.getFirstMidtermGrade());
        assertEquals(0, subject.getSecondMidtermGrade());
        assertEquals(0, subject.getFinalExamGrade());
    }

    @Test
    void testGetSubjectGradeDefaultGrades() {
        assertEquals(0, testSubject.getSubjectGrade());
    }

    @Test
    void testGetSubjectGradeNewGrades() {
        testSubject.setFirstMidtermGrade(12);
        testSubject.setSecondMidtermGrade(14);
        testSubject.setFinalExamGrade(11);
        assertEquals(0.25 * 12 + 14 * 0.25 + 0.5 * 11, testSubject.getSubjectGrade());
    }

}
