package ui;

import model.GradeLevel;
import model.Student;
import model.Subject;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// represents an application that manages 6 school classes
public class App {
    private GradeLevel gradeSeven;
    private GradeLevel gradeEight;
    private GradeLevel gradeNine;
    private GradeLevel gradeTen;
    private GradeLevel gradeEleven;
    private GradeLevel gradeTwelve;
    //readers
    private JsonReader grade7Reader;
    private JsonReader grade8Reader;
    private JsonReader grade9Reader;
    private JsonReader grade10Reader;
    private JsonReader grade11Reader;
    private JsonReader grade12Reader;
    //writers
    private JsonWriter grade7Writer;
    private JsonWriter grade8Writer;
    private JsonWriter grade9Writer;
    private JsonWriter grade10Writer;
    private JsonWriter grade11Writer;
    private JsonWriter grade12Writer;

    private ArrayList<GradeLevel> classes = new ArrayList<>();
    private Scanner userRequest;

    //constructor
    // EFFECTS: initializes the application
    //
    public App() {
        try {
            startApp();
        } catch (IOException e) {
            System.out.println("Failed to read classes from file");
        }


    }

    //MODIFIES: this
    //EFFECTS: creates grades 7,8,9,10,11,12, displays command menu,
    //  processes user input and requests
    // This method indirectly references code from the TellerApp class
    // Link: [https://github.students.cs.ubc.ca/CPSC210/TellerApp.git]
    private void startApp() throws IOException {

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
                System.out.println("Enter y to save the current state to file or 'n' to quit without saving ");
                String canSave = userRequest.next();
                if (canSave.toLowerCase().equals("y")) {
                    writeClasses();
                    System.out.println("State saved successfully !");
                } else {
                    System.out.println("Quitting without saving state to file...");
                }
                isRunning = false;

            } else {
                executeCommand(actionCommand);
            }
        }


    }


    //MODIFIES: this
    //EFFECTS: executes the command chosen by the user
    //This method is inspired by the example in the TellApp
    //Link: [https://github.students.cs.ubc.ca/CPSC210/TellerApp.git]
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

    //MODIFIES: this
    //EFFECTS: creates grade 7,8,9,10,11,12,and adds them to the list of classes,
    // allow user input, throws IOException if reading fails
    public void setup() throws IOException {

        userRequest = new Scanner(System.in);
        userRequest.useDelimiter("\n");
        System.out.println("Do you want to load your classes from file?");
        System.out.println("Enter 'y' to load classes or 'n' to start with new classrooms");
        String loadChoice = userRequest.next();
        setClasses(loadChoice);

        classes.add(gradeSeven);
        classes.add(gradeEight);
        classes.add(gradeNine);
        classes.add(gradeTen);
        classes.add(gradeEleven);
        classes.add(gradeTwelve);
    }

    //EEFECTS: load classes from file if users inputs y, otherwise creates new classes to work with
    public void setClasses(String loadingChoice) throws IOException {

        if (loadingChoice.equalsIgnoreCase("y")) {
            getGradeLevelsFromFile();
        } else {
            createNewClasses();
        }
    }

    //EFFECTS: sets the value of each gradeLevel to a new instance
    public void createNewClasses() {
        gradeSeven = new GradeLevel("Grade 7");
        gradeEight = new GradeLevel("Grade 8");
        gradeNine = new GradeLevel("Grade 9");
        gradeTen = new GradeLevel("Grade 10");
        gradeEleven = new GradeLevel("Grade 11");
        gradeTwelve = new GradeLevel("Grade 12");
    }

    //EEFECTS: reads gradeLevels from file
    public void getGradeLevelsFromFile() throws IOException {
        grade7Reader = new JsonReader("./data/grade7.json");
        grade8Reader = new JsonReader("./data/grade8.json");
        grade9Reader = new JsonReader("./data/grade9.json");
        grade10Reader = new JsonReader("./data/grade10.json");
        grade11Reader = new JsonReader("./data/grade11.json");
        grade12Reader = new JsonReader("./data/grade12.json");

        gradeSeven = grade7Reader.read();
        gradeEight = grade8Reader.read();
        gradeNine = grade9Reader.read();
        gradeTen = grade10Reader.read();
        gradeEleven = grade11Reader.read();
        gradeTwelve = grade12Reader.read();
    }

    //EFFECT:Writes gradeLevels to file
    private void writeClasses() {
        grade7Writer = new JsonWriter("./data/grade7.json");
        grade8Writer = new JsonWriter("./data/grade8.json");
        grade9Writer = new JsonWriter("./data/grade9.json");
        grade10Writer = new JsonWriter("./data/grade10.json");
        grade11Writer = new JsonWriter("./data/grade11.json");
        grade12Writer = new JsonWriter("./data/grade12.json");

        try {
            openWriters();
            grade7Writer.write(gradeSeven);
            grade8Writer.write(gradeEight);
            grade9Writer.write(gradeNine);
            grade10Writer.write(gradeTen);
            grade11Writer.write(gradeEleven);
            grade12Writer.write(gradeTwelve);

            closeWriters();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file");
        }


    }

    //EFFECT:open all files fro writing, throws FileNotFoundException if source not found
    private void openWriters() throws FileNotFoundException {
        grade7Writer.open();
        grade8Writer.open();
        grade9Writer.open();
        grade10Writer.open();
        grade11Writer.open();
        grade12Writer.open();
    }


    //EFFECTS:close all files  throws FileNotFoundException if source not found
    private void closeWriters() {
        grade7Writer.close();
        grade8Writer.close();
        grade9Writer.close();
        grade10Writer.close();
        grade11Writer.close();
        grade12Writer.close();
    }

    //EFFECTS:displays command menu to the user
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


    //EFFECTS:shows the name of the grade with the number of students registered in it.
    public void printGradeLevel(GradeLevel gradeLevel) {
        System.out.println(gradeLevel.getName()
                + " ---> " + gradeLevel.getStudents().size() + " Students");
    }


    //EFFECTS:shows all the names of the classes(grades) with their number of students
    public void printGradeLevels() {
        for (GradeLevel grade : classes) {
            printGradeLevel(grade);
        }
    }

    //MODIFIES:this
    //REQUIRES:lastName and firstName are strings
    //EFFECTS: prompts the user to choose a class, enter a first and last names
    // creates a new student with lastName and firstName and adds it to the chosen class
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


    //REQUIRES: user input must be an integer between 7 and 12 included,
    //EFFECTS: returns the class corresponding to the user input
    public GradeLevel selectGradeLevel() {
        int selectedGrade = 0;
        while (selectedGrade < 7 || selectedGrade > 12) {
            showGradeLevelOptions();

            selectedGrade = userRequest.nextInt();


        }
        return handleGradeChoice(selectedGrade);
    }


    //REQUIRES: selection is a positive integer
    //EFFECTS: returns the class whose name ends in the integer selection [7,12]
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


    //EFFECTS:displays the grades to chose by the user
    public void showGradeLevelOptions() {
        System.out.println("7 for grade 7");
        System.out.println("8 for grade 8");
        System.out.println("9 for grade 9");
        System.out.println("10 for grade 10");
        System.out.println("11 for grade 11");
        System.out.println("12 for grade 12");
    }

    //MODIFIES:this
    //REQUIRES: studentId is a positive integer >0
    //EFFECTS: prompts the user to choose a grade, enter a studentId,
    // finds the student with studentId and removes it from the selected class.
    // notifies the user if there is no student with provided id.
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

    //REQUIRES: studentId must be a positive integer > 0
    //EFFECTS:prompts user to choose a class, to enter a student id,
    // finds the student with the given id and prints the transcript accordingly
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


    //EFFECTS: prints out the student's personal info, along with subjects and their grades
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
        System.out.println("GPA: " + student.getOverallGrade() + "/20");
    }


    //EFFECTS:prints out all exam grades in subject formatted accordingly
    public void formatStudentGrades(Subject subject) {
        System.out.println(subject.getName().toUpperCase() + ": ");
        String firstMidtermGrade = Double.toString(subject.getFirstMidtermGrade());
        String secondMidtermGrade = Double.toString(subject.getSecondMidtermGrade());
        String finalGrade = Double.toString(subject.getFinalExamGrade());
        System.out.println("\t\t" + "Midterm 1: ------> " + firstMidtermGrade + "/20");
        System.out.println("\t\t" + "Midterm 2: ------> " + secondMidtermGrade + "/20");
        System.out.println("\t\t" + "Final Exam: ------> " + finalGrade + "/20");
        System.out.println("\t\tCourse:-----> " + subject.getSubjectGrade() + "/20");

    }


    //EFFECTS: prints out the number of remaining spots in the selected class
    public void printSpots() {
        System.out.println("Which of which grade do you want to know the number of remaining spots ?");
        GradeLevel chosenGrade = selectGradeLevel();
        String gradeName = chosenGrade.getName().toLowerCase();
        int spots = chosenGrade.getRemainingNumberOfSpots();
        System.out.println("There are currently " + spots + " spots remaining in " + gradeName);

    }


    //EFFECTS: prints out the id and full names of students registered in grade
    // is registered student is empty, print a message to the user to let them know
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

    //MODIFIES:this
    //REQUIRES: studentId  must be an integer > 1 and provided subject grade must be a double in [0,20],
    //EFFECTS:prompts the user to select a class, a input a student id,
    // if a student exists with the provided id, prompts the user to selected exam to update the grade,
    // user inputs the new grade a the chosen grades gets set to the new value
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

    //REQUIRES: response must a positive integer >= 1 and <= 10
    //EFFECTS:prompts the user to select a subject to update by inputting an integer,
    // returns subject with index response-1

    public Subject chooseSubject(Student student) {
        ArrayList<Subject> subjects = student.getGradeRecord();
        System.out.println("Which subject do you want to update? ".toUpperCase());
        for (int index = 0; index < subjects.size(); index++) {
            System.out.println("Enter " + (index + 1) + " to choose " + subjects.get(index).getName());


        }
        int response = userRequest.nextInt();
        return subjects.get(response - 1);

    }

    //MODIFIES: this
    //REQUIRES: subjectIndex is one of (1,2,3) and newGrade is a double in [0,20]
    //EFFECTS: prompts the user to choose a grade to update by enter SubjectIndex and newGrade
    // sets first midterm grade to newGrade if subjectIndex is 1
    // sets second midterm grade to newGrade if subjectIndex is 2
    // sets final exam grade to newGrade if subjectIndex is 3
    public void updateSubjectGrade(Subject subject) {

        System.out.println("Enter 1 to update the first midterm grade");
        System.out.println("Enter 2 to update the second midterm grade");
        System.out.println("Enter 3 to update the final exam grade");
        int subjectIndex = userRequest.nextInt();
        System.out.println("Enter new grade");
        double newGrade = userRequest.nextDouble();
        executeGradeUpdate(subject, subjectIndex, newGrade);


    }

    //MODIFIES:this
    //REQUIRES:newGrade is a double in [0,20], index is one of (1,2,3)
    //EFFECTS: // sets first midterm grade to newGrade if index is 1
    // sets second midterm grade to newGrade if index is 2
    // else sets final exam grade to newGrade
    public void executeGradeUpdate(Subject subject, int index, Double newGrade) {
        if (index == 1) {
            subject.setFirstMidtermGrade(newGrade);
        } else if (index == 2) {
            subject.setSecondMidtermGrade(newGrade);
        } else {
            subject.setFinalExamGrade(newGrade);
        }
    }


    //EFFECTS:prompts the user to select a class to operate in,
    // then prints the students registered in that ranked from highest to lowest GPA
    public void printStudentsByOrder() {
        System.out.println("Please select a grade: ");
        showOptions();
        GradeLevel chosenGrade = selectGradeLevel();
        formatStudentsOrder(chosenGrade);

    }


    //EFFECTS: formats the information of all students in the provided grade to be printed later
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


    //EFFECTS: prints the name of the class, the number of remaining spots, number of registered students
    public void printGradeSummary() {
        GradeLevel grade = selectGradeLevel();
        System.out.println("GRADE: " + grade.getName());
        System.out.println("Remaining spots: " + grade.getRemainingNumberOfSpots());
        System.out.println("Registered students: " + grade.getStudents().size());

    }

}
