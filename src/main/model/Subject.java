package model;

// represents a subject with name, weight,first and second midterm grade,and a final exam grade
public class Subject {
    public static final double FIRST_MIDTERM_WEIGHT = 0.25;
    public static final double SECOND_MIDTERM_WEIGHT = 0.25;
    public static final double FINAL_EXAM_WEIGHT = 0.5;


    private String name;
    private int weight;
    private double firstMidtermGrade;
    private double secondMidtermGrade;
    private double finalExamGrade;

    // constructor
    //REQUIRES: weight is a positive integer
    //EFFECTS:  creates a subject named name and with weight = weight
            // initializes the three grades to 0
    public Subject(String name, int weight) {
        this.name = name;
        this.weight = weight;
        firstMidtermGrade = 0;
        secondMidtermGrade = 0;
        finalExamGrade = 0;

    }

    //EFFECTS: returns name
    public String getName() {
        return name;
    }


    //EFFECTS: returns weight
    public int getWeight() {
        return weight;
    }


    //EFFECTS: returns subjectGrade * subject weight
    public static double computeRawGrade(Subject subject) {
        return subject.getSubjectGrade() * subject.getWeight();
    }


    //EFFECTS returns final exam grade:
    public double getFinalExamGrade() {
        return finalExamGrade;
    }


    //EFFECTS: returns midterm 1 grade
    public double getFirstMidtermGrade() {
        return firstMidtermGrade;
    }


    //EFFECTS:returns the second midterm grade
    public double getSecondMidtermGrade() {
        return secondMidtermGrade;
    }

    //MODIFIES:this
    //REQUIRES: 0<= firstMidtermGrade <= 20
    //EFFECTS: sets midterm 1 grade to the provided value
    public void setFirstMidtermGrade(double firstMidtermGrade) {
        this.firstMidtermGrade = firstMidtermGrade;
    }

    //MODIFIES: this
    //REQUIRES: 0<= secondMidtermGrade <= 20
    //EFFECTS: sets midterm 2 grade to the provided value
    public void setSecondMidtermGrade(double secondMidtermGrade) {
        this.secondMidtermGrade = secondMidtermGrade;
    }

    //MODIFIES: this
    //REQUIRES:0<= secondMidtermGrade <= 20
    //EFFECTS: sets the student's final exam grade to the provided value
    public void setFinalExamGrade(double finalExamGrade) {
        this.finalExamGrade = finalExamGrade;
    }

    //EFFECTS: returns student's grade in the subject calculated as
             //         firstMidtermGrade * FIRST_MIDTERM_WEIGHT
             //          + secondMidtermGrade * SECOND_MIDTERM_WEIGHT
             //           + finalExamGrade * FINAL_EXAM_WEIGHT;
    public double getSubjectGrade() {

        return firstMidtermGrade * FIRST_MIDTERM_WEIGHT
                + secondMidtermGrade * SECOND_MIDTERM_WEIGHT
                + finalExamGrade * FINAL_EXAM_WEIGHT;
    }
}
