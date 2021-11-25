# Student Management System

### By *Sylvain Yabre* ###

## Project Proposal
- ### What will the application do? ###
 
The application will store and manage the list of classes (as in a school) with their respective students. 
All students take the same set of subjects. 
Each subject will have three exams, two midterm exams worth 25% each and a final 
exam worth 50%. The final grade for a student in a given grade level with be computed using this scheme.
The application will  allow the admin to  register/add new students, delete students who leave the school 
or the class, or  update the grades of existing students.   
Also, the admin can print the list of all students in a class ranked by GPA/overall average
Finally, the application can  print the transcript of a student.
-------------
- ### Who will use it?
 
The application is intended to be used by a *high school principal* 
or any school official in charge of managing students and grade reporting them.
- --------------
- ### Why is this project of interest to you?
  
This project stems from my middle school experience, our grades and ranking were computed manually, which took a lot 
of time and effort to complete. Building this application is for me a chance to visit my childhood and provide
*a proof of concept solution to that problem*.

----------------------------
- ## User stories
- 
1. As a user, I want to be able to register/add multiple students in an existing class, provided that space is available
2. As a user, I want to be able to check the number of spots remaining in a any class
3. As a user, I want to  a find a student by ID in a given class and update their grades.
4. As a user, I want to be able to see the list of all students in class ranked by overall GPA.
5. As a user, I want to be able to see the transcript of a student.
6. As a user, I want to be able to see the summary information of a given class (spots available and number of students)
7. As a user, I want to be able to remove a student from a class
8. As a user, I want to be able to choose to save my all classes with their students and record to file whenever 
I quit my application;
9. As a user, I want to be able to choose to load my classes with their students and records from file whenever 
I open my application .



##Phase 4: Task 2

Sat Nov 20 14:20:54 PST 2021\
Final exam grade updated for Another Student

Sat Nov 20 14:20:54 PST 2021\
Midterm 1 grade updated for Another Student

Sat Nov 20 14:20:54 PST 2021\
Midterm 2 grade updated for Another Student

Sat Nov 20 14:20:54 PST 2021\
Final exam grade updated for Another Student

Sat Nov 20 14:21:50 PST 2021\
Set subjects forZakaria Sanogo

Sat Nov 20 14:21:50 PST 2021\
Set Grade 8 for Zakaria Sanogo

Sat Nov 20 14:21:50 PST 2021\
Registered Zakaria Sanogo in Grade 8

Sat Nov 20 14:22:14 PST 2021\
Midterm 1 grade updated for Emar Yabre

Sat Nov 20 14:22:52 PST 2021\
Deleted Another Student from Grade 12

Sat Nov 20 14:23:20 PST 2021\
Set subjects forEdmond Tabsoba

Sat Nov 20 14:23:20 PST 2021\
Set Grade 12 for Edmond Tabsoba

Sat Nov 20 14:23:20 PST 2021\
Registered Edmond Tabsoba in Grade 12

##Phase 4: Task 3
Below are the possible changes I would make if I had more time to work on this project:
- I would have used the Observer Pattern to connect changes in any of my 6 GradeLevels (classes) 
to changes in the MainFrame class. As one can see on the diagram, most of my classes have a field of type either 
GradeLevel or MainFrame which results in a lot of coupling, the Observer pattern would reduce the degree of coupling.

- The second change that I would make is to remove the KeyValuePair class, it plays the role of a Map,
but with a small difference, however, I now see that I could still make things work with just a HashMap 
instead of creating a new class.

- The next change that I would make is implement the MainFrame class following the Singleton pattern,
my application only needs one instance of MainFrame that is available globally 
so that all the subcomponents can be added to it. If I use the singleton pattern, the other classes 
will not need to maintain fields of type MainFrame. This would drastically reduce coupling in my code. 

- The last change that I would make would be targetted at reducing the multiplicity of the association between the Popup
class and JsonReader and JsonWriter. I can now see a better relationship where Popup uses only one 
instance of the reader and the writer to achieve the same goal.