package ui;

import model.GradeLevel;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

// A class representing a popup with a message and two choice buttons
public class Popup {
    private String message;
    private String title;
    private JFrame frame;
    private int response;

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

    //EFFECTS: creates a new popup with a message and choice buttons
    public Popup(String message, String title, JFrame frame) {
        this.message = message;
        this.title = title;
        this.frame = frame;
        ImageIcon icon = new ImageIcon("./data/school.png");
        Image image = icon.getImage();
        Image resizedImage = getScaledImage(image, 120, 120);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        Object[] options = {"Yes", "No"};
        response = JOptionPane.showOptionDialog(frame,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                resizedIcon,     // use a custom Icon
                options,  //the titles of buttons
                options[0]); //default button title


    }

    public int getResponse() {
        return response;
    }

    //MODIFIES: this
    //EFFECTS: reads gradeLevels from file and returns the data into a Map, throws IOException
    public HashMap<Integer, GradeLevel> getGradeLevelsFromFile() throws IOException {
        HashMap<Integer, GradeLevel> classes = new HashMap<>();
        grade7Reader = new JsonReader("./data/grade7.json");
        grade8Reader = new JsonReader("./data/grade8.json");
        grade9Reader = new JsonReader("./data/grade9.json");
        grade10Reader = new JsonReader("./data/grade10.json");
        grade11Reader = new JsonReader("./data/grade11.json");
        grade12Reader = new JsonReader("./data/grade12.json");

        classes.put(7, grade7Reader.read());
        classes.put(8, grade8Reader.read());
        classes.put(9, grade9Reader.read());
        classes.put(10, grade10Reader.read());
        classes.put(11, grade11Reader.read());
        classes.put(12, grade12Reader.read());

        return classes;

    }

    //MODIFIES: this
    //EFFECTS: opens writers, writes classes to file and then closes writers
    public void writeClasses(HashMap<Integer, GradeLevel> classes) {
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

    //MODIFIES:this
    //EFFECTS: sets the value of each gradeLevel to a new instance
    public HashMap<Integer, GradeLevel> createNewClasses() {
        HashMap<Integer, GradeLevel> classes = new HashMap<>();

        classes.put(7, new GradeLevel("Grade 7"));
        classes.put(8, new GradeLevel("Grade 8"));
        classes.put(9, new GradeLevel("Grade 9"));
        classes.put(10, new GradeLevel("Grade 10"));
        classes.put(11, new GradeLevel("Grade 11"));
        classes.put(12, new GradeLevel("Grade 12"));
        return classes;


    }

    //MODIFIES:srcImg
    //REQUIRES: w and h are positive integers
    //EFFECTS: scales the image to dimensions w and h
    //code credit:https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}
