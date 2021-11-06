package ui;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.FontMetrics;
//import java.awt.Graphics;
//import java.util.ArrayList;
import javax.swing.*;

import persistence.*;

import model.*;

public class MainFrame extends JFrame implements WindowListener {
    private GradeLevel grade7;
    private GradeLevel grade8;
    private GradeLevel grade9;
    private GradeLevel grade10;
    private GradeLevel grade11;
    private GradeLevel grade12;
    private HashMap<Integer, GradeLevel> classes;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public MainFrame() {
        this.setVisible(true);
        this.setSize(1000, 1700);
        this.setTitle("STUDENT MANAGEMENT SYSTEM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(50, 123, 168));
        addWindowListener(this);


    }

    //EFFECTS:
    //MODIFIES:
    private void handleWindowClose() {
        Popup popup = new Popup("Do you want your classes to file?", "Data Saving", this);
        popup.writeClasses(this.classes);


    }

    //EFFECTS:
    //MODIFIES:
    private void handleDataLoading() {
        Popup popup = new Popup("Do you want to load classes from file?", "Data Load", this);
        if (popup.getResponse() == 0) {
            try {
                HashMap<Integer, GradeLevel> classes = popup.getGradeLevelsFromFile();
                grade7 = classes.get(7);
                grade8 = classes.get(8);
                grade9 = classes.get(9);
                grade10 = classes.get(10);
                grade11 = classes.get(11);
                grade12 = classes.get(12);
                this.classes = classes;

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "An error occurred");
            }
        } else {
            HashMap<Integer, GradeLevel> classes = popup.createNewClasses();
            grade7 = classes.get(7);
            grade8 = classes.get(8);
            grade9 = classes.get(9);
            grade10 = classes.get(10);
            grade11 = classes.get(11);
            grade12 = classes.get(12);
        }

    }


    @Override
    public void windowOpened(WindowEvent e) {
        handleDataLoading();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        handleWindowClose();

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
