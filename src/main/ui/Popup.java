package ui;

import model.GradeLevel;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class Popup {
    private String message;
    private String title;
    private JFrame frame;
    private int response;

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

    public Popup(String message, String title, JFrame frame) {
        this.message = message;
        this.title = title;
        this.frame = frame;
        Object[] options = {"Yes", "No"};
        response = JOptionPane.showOptionDialog(frame,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]); //default button title


    }

    //EFFECTS: return pane

    public int getResponse() {
        return response;
    }

    //EFFECTS: reads gradeLevels from file
    public HashMap<Integer,GradeLevel> getGradeLevelsFromFile() throws IOException {
        HashMap<Integer,GradeLevel> classes = new HashMap<>();
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
        classes.put(7,gradeSeven);
        classes.put(8,gradeEight);
        classes.put(9,gradeNine);
        classes.put(10,gradeTen);
        classes.put(11,gradeEleven);
        classes.put(12,gradeTwelve);

        return classes;

    }

    public void writeClasses(HashMap<Integer,GradeLevel> classes) {
        grade7Writer = new JsonWriter("./data/grade7.json");
        grade8Writer = new JsonWriter("./data/grade8.json");
        grade9Writer = new JsonWriter("./data/grade9.json");
        grade10Writer = new JsonWriter("./data/grade10.json");
        grade11Writer = new JsonWriter("./data/grade11.json");
        grade12Writer = new JsonWriter("./data/grade12.json");

        try {
            openWriters();
            grade7Writer.write(classes.get(7));
            grade8Writer.write(classes.get(8));
            grade9Writer.write(classes.get(9));
            grade10Writer.write(classes.get(10));
            grade11Writer.write(classes.get(11));
            grade12Writer.write(classes.get(12));
            closeWriters();
            JOptionPane.showMessageDialog(frame, "Classes saved successfully.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Error while writing to file");
        }


    }

    //EFFECT:open all files for writing, throws FileNotFoundException if source not found
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

    //EFFECTS: sets the value of each gradeLevel to a new instance
    public HashMap<Integer,GradeLevel> createNewClasses() {
        HashMap<Integer,GradeLevel> classes = new HashMap<>();
        gradeSeven = new GradeLevel("Grade 7");
        gradeEight = new GradeLevel("Grade 8");
        gradeNine = new GradeLevel("Grade 9");
        gradeTen = new GradeLevel("Grade 10");
        gradeEleven = new GradeLevel("Grade 11");
        gradeTwelve = new GradeLevel("Grade 12");
        classes.put(7, gradeSeven);
        classes.put(8, gradeEight);
        classes.put(9, gradeNine);
        classes.put(10, gradeTen);
        classes.put(11, gradeEleven);
        classes.put(12, gradeTwelve);
        return classes;


    }

}
