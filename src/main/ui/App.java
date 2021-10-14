package ui;

import model.GradeLevel;
import model.Student;
import model.Subject;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private GradeLevel gradeSeven;
    private GradeLevel gradeEight;
    private GradeLevel gradeNine;
    private GradeLevel gradeTen;
    private GradeLevel gradeEleven;
    private GradeLevel gradeTwelve;

    private ArrayList<GradeLevel> classes = new ArrayList<>();
    private Scanner userRequest;

    public App() {
        startApp();

    }

    private void startApp() {

        boolean isRunning = true;
        String actionCommand;
        setup();
        printGradeLevels();
        showOptions();
        while (isRunning) {
            userRequest = new Scanner(System.in);
            actionCommand = userRequest.next();
            actionCommand = actionCommand.toLowerCase();

            if (actionCommand.equals("q")) {
                isRunning = false;
            } else {
                executeCommand(actionCommand);
            }
        }


    }

    private void executeCommand(String actionCommand) {
        if (actionCommand.equals("reg")) {
            registerStudent();

        }
        if (actionCommand.equals("rm")) {
            removeStudent();
        }
        if (actionCommand.equals("sp")) {
            printSpots();
        }
        if (actionCommand.equals("tr")) {
            displayStudentTranscript();
        }
        if (actionCommand.equals("us")) {
            updateStudentGrades();
        }
        if (actionCommand.equals("sts")) {
            printStudentsByOrder();
        }
        if (actionCommand.equals("gr")) {
            printGradeSummary();
        }
        showOptions();
    }

    public void setup() {
        gradeSeven = new GradeLevel("Grade 7");
        gradeEight = new GradeLevel("Grade 8");
        gradeNine = new GradeLevel("Grade 9");
        gradeTen = new GradeLevel("Grade 10");
        gradeEleven = new GradeLevel("Grade 11");
        gradeTwelve = new GradeLevel("Grade 12");

        //the two lines below are taken from tellerApp
        userRequest = new Scanner(System.in);
        userRequest.useDelimiter("\n");

        classes.add(gradeSeven);
        classes.add(gradeEight);
        classes.add(gradeNine);
        classes.add(gradeTen);
        classes.add(gradeEleven);
        classes.add(gradeTwelve);


    }

    private void showOptions() {
        System.out.println("\nActions you can perform:");
        System.out.println("\tgr  -----> To see summary information in a grade.");
        System.out.println("\tsp -----> get remaining spots in a class");
        System.out.println("\treg -----> Register a student into a  class");
        System.out.println("\tsts -----> get the students  a class");
        System.out.println("\trm -----> to remove a student with int id from a class");
        System.out.println("\ttr -----> to get student transcript");
        System.out.println("\tus -----> to update student grade");
        System.out.println("\tq -----> quit");
    }


    public void printGradeLevel(GradeLevel gradeLevel) {
        System.out.println(gradeLevel.getName()
                + " ---> " + gradeLevel.getStudents().size() + " Students");
    }

    public void printGradeLevels() {
        for (GradeLevel grade : classes) {
            printGradeLevel(grade);
        }
    }


    public void registerStudent() {
        String lastName;
        String firstname;
        System.out.println("In which grade do you want to register a student?");
        GradeLevel chosenGrade = selectGradeLevel();
        System.out.println("Enter student's first name:  ");
        firstname = userRequest.next();
        System.out.println("Enter student's last name:  ");
        lastName = userRequest.next();
        Student student = new Student(firstname, lastName);
        chosenGrade.registerStudent(student);
        System.out.println("Student registered successfully");
        printStudents(chosenGrade);


    }

    public GradeLevel selectGradeLevel() {
        int selectedGrade = 0;
        while (selectedGrade < 7 || selectedGrade > 12) {
            showGradeLevelOptions();

            selectedGrade = userRequest.nextInt();


        }
        return handleGradeChoice(selectedGrade);
    }

    public GradeLevel handleGradeChoice(int selection) {
        if (selection == 7) {
            return gradeSeven;
        } else if (selection == 8) {
            return gradeEight;
        } else if (selection == 9) {
            return gradeNine;
        } else if (selection == 10) {
            return gradeTen;
        } else if (selection == 11) {
            return gradeEleven;
        } else {
            return gradeTwelve;
        }
    }

    public void showGradeLevelOptions() {
        System.out.println("7 for grade 7");
        System.out.println("8 for grade 8");
        System.out.println("9 for grade 9");
        System.out.println("10 for grade 10");
        System.out.println("11 for grade 11");
        System.out.println("12 for grade 12");
    }

    public void removeStudent() {
        System.out.println("Which grade do you want to delete a student from: ");
        GradeLevel chosenGrade = selectGradeLevel();
        System.out.println("Please, enter the id number of the student: ");
        int studentId = userRequest.nextInt();
        Student student = chosenGrade.findStudentById(studentId);
        if (student != null) {
            System.out.println("Are you sure you want to delete " + student.getFullName() + " ?");
            System.out.println("Type 'y' to delete or 'n' to cancel :");
            String confirmation = userRequest.next();
            if (confirmation.equalsIgnoreCase("y")) {
                if (chosenGrade.removeStudentFromClass(studentId)) {
                    System.out.println("Student removed successfully");
                    printStudents(chosenGrade);

                }
            }

        } else {
            System.out.println("No student  found with this id.".toUpperCase());
        }

    }


    public void displayStudentTranscript() {
        System.out.println("Enter the grade of the student: ");
        GradeLevel chosenGrade = selectGradeLevel();
        System.out.println("Enter the Student's Id: ");
        int studentId = userRequest.nextInt();
        Student student = chosenGrade.findStudentById(studentId);
        if (student == null) {
            System.out.println("No student found with this id ! ".toUpperCase());
        } else {
            formatTranscript(student);
        }


    }

    public void formatTranscript(Student student) {
        ArrayList<Subject> subjects = student.getGradeRecord();
        System.out.println("ID: " + student.getId());
        System.out.println("FIRST NAME: " + student.getFirstName());
        System.out.println("LAST NAME: " + student.getLastName());
        System.out.println("CLASS: " + student.getGradeLevel().getName());

        for (Subject subject : subjects) {
            formatStudentGrades(subject);
            System.out.println("------------------------------------------------------------");
        }
        System.out.println("GPA: " + student.getOverallGrade());
    }

    public void formatStudentGrades(Subject subject) {
        System.out.println(subject.getName().toUpperCase() + ": ");
        String firstMidtermGrade = Double.toString(subject.getFirstMidtermGrade());
        String secondMidtermGrade = Double.toString(subject.getSecondMidtermGrade());
        String finalGrade = Double.toString(subject.getFinalExamGrade());
        System.out.println("\t\t" + "Midterm 1: ------> " + firstMidtermGrade + "/20");
        System.out.println("\t\t" + "Midterm 2: ------> " + secondMidtermGrade + "/20");
        System.out.println("\t\t" + "Final: ------> " + finalGrade + "/20");
        System.out.println("\t\tCourse:-----> " + subject.getSubjectGrade() + "/20");

    }

    public void printSpots() {
        System.out.println("Which of which grade do you want to know the number of remaining spots ?");
        GradeLevel chosenGrade = selectGradeLevel();
        String gradeName = chosenGrade.getName().toLowerCase();
        int spots = chosenGrade.getRemainingNumberOfSpots();
        System.out.println("There are currently " + spots + " spots remaining in " + gradeName);

    }

    public void printStudents(GradeLevel grade) {
        ArrayList<Student> students = grade.getStudents();
        if (students.isEmpty()) {
            System.out.println("There are no students registered in " + grade.getName());
        } else {
            System.out.println("Students registered in " + grade.getName().toUpperCase());
            for (Student student : students) {
                System.out.println(student.getId() + " -> " + student.getFullName());
            }
        }
    }

    public void updateStudentGrades() {
        System.out.println("Selected the class of the student: ");
        GradeLevel chosenGrade = selectGradeLevel();
        System.out.println("Enter student id: ");
        int studentId = userRequest.nextInt();
        Student student = chosenGrade.findStudentById(studentId);
        if (student == null) {
            System.out.println("NO STUDENT FOUND WITH THIS ID ! ");
        } else {
            while (true) {
                Subject subject = chooseSubject(student);
                updateSubjectGrade(subject);
                System.out.println("\"type 'done' if you are done updating or"
                        + " any letter to continue updating " + student.getFullName() + "'s grade");
                String response = userRequest.next();
                if (response.equals("done")) {
                    break;
                } else {
                    updateSubjectGrade(chooseSubject(student));
                }

            }


        }

    }

    public Subject chooseSubject(Student student) {
        ArrayList<Subject> subjects = student.getGradeRecord();
        System.out.println("Which subject do you want to update? ".toUpperCase());
        for (int index = 0; index < subjects.size(); index++) {
            System.out.println("Enter " + (index + 1) + " to choose " + subjects.get(index).getName());


        }
        int response = userRequest.nextInt();
        return subjects.get(response - 1);

    }

    public void updateSubjectGrade(Subject subject) {

        System.out.println("Enter 1 to update the first midterm grade");
        System.out.println("Enter 2 to update the second midterm grade");
        System.out.println("Enter 3 to update the final exam grade");
        int subjectIndex = userRequest.nextInt();
        System.out.println("Enter new grade");
        double newGrade = userRequest.nextDouble();
        executeGradeUpdate(subject, subjectIndex, newGrade);


    }

    public void executeGradeUpdate(Subject subject, int index, Double newGrade) {
        if (index == 1) {
            subject.setFirstMidtermGrade(newGrade);
        } else if (index == 2) {
            subject.setSecondMidtermGrade(newGrade);
        } else {
            subject.setFinalExamGrade(newGrade);
        }
    }

    public void printStudentsByOrder() {
        System.out.println("Please select a grade: ");
        showOptions();
        GradeLevel chosenGrade = selectGradeLevel();
        formatStudentsOrder(chosenGrade);

    }

    public void formatStudentsOrder(GradeLevel grade) {

        ArrayList<Student> rankedStudents = grade.getStudentsRanking();

        if (rankedStudents.isEmpty()) {
            System.out.println("This grade has no students");
        } else {

            for (int index = 0; index < rankedStudents.size(); index++) {
                Student student = rankedStudents.get(index);

                System.out.println((index + 1) + ": "
                        + student.getFullName() + " ---------> " + student.getOverallGrade());
            }

        }
    }

    public void printGradeSummary() {
        GradeLevel grade = selectGradeLevel();
        System.out.println("GRADE: " + grade.getName());
        System.out.println("Remaining spots: " + grade.getRemainingNumberOfSpots());
        System.out.println("Registered students: " + grade.getStudents().size());

    }

}
