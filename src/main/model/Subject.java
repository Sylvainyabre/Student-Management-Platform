package model;

public class Subject {
    public static final double FIRST_MIDTERM_WEIGHT = 0.25;
    public static final double SECOND_MIDTERM_WEIGHT = 0.25;
    public static final double FINAL_EXAM_WEIGHT = 0.5;


    private String name;
    private int weight;
    private double firstMidtermGrade;
    private double secondMidtermGrade;
    private double finalExamGrade;


    public Subject(String name, int weight) {
        this.name = name;
        this.weight = weight;
        firstMidtermGrade = 0;
        secondMidtermGrade = 0;
        finalExamGrade = 0;

    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public String getName() {
        return name;
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public int getWeight() {
        return weight;
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public static double computeRawGrade(Subject subject) {
        return subject.getSubjectGrade() * subject.getWeight();
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public double getFinalExamGrade() {
        return finalExamGrade;
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public double getFirstMidtermGrade() {
        return firstMidtermGrade;
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public double getSecondMidtermGrade() {
        return secondMidtermGrade;
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public void setFirstMidtermGrade(double firstMidtermGrade) {
        this.firstMidtermGrade = firstMidtermGrade;
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public void setSecondMidtermGrade(double secondMidtermGrade) {
        this.secondMidtermGrade = secondMidtermGrade;
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public void setFinalExamGrade(double finalExamGrade) {
        this.finalExamGrade = finalExamGrade;
    }

    //MODIFIES:
    //REQUIRES:
    //EFFECTS:
    public double getSubjectGrade() {

        return firstMidtermGrade * FIRST_MIDTERM_WEIGHT
                + secondMidtermGrade * SECOND_MIDTERM_WEIGHT
                + finalExamGrade * FINAL_EXAM_WEIGHT;
    }
}
