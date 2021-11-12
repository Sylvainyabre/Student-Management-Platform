package ui;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.HashMap;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.FontMetrics;
//import java.awt.Graphics;
//import java.util.ArrayList;
import javax.swing.*;

import model.*;

public class MainFrame extends JFrame implements WindowListener {
    private GradeLevel grade7;
    private GradeLevel grade8;
    private GradeLevel grade9;
    private GradeLevel grade10;
    private GradeLevel grade11;
    private GradeLevel grade12;
    private HashMap<Integer, GradeLevel> classes;


    public MainFrame() {
        this.setVisible(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        this.setTitle("STUDENT MANAGEMENT SYSTEM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(50, 123, 168));
        this.getContentPane().setLayout(new BorderLayout(7, 6));
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(5, 4, 5, 4, Color.CYAN));
        addWindowListener(this);
        new TopPanel(this);
        new SidePanel(this);


    }


    //EFFECTS:
    //MODIFIES:
    private void handleWindowClose() {
        Popup popup = new Popup("Do you want your save your classes to file?", "Data Saving", this);
        if (popup.getResponse() == 0) {
            popup.writeClasses(this.classes);
            System.exit(0);
        } else {
            System.exit(0);
        }

    }

    //EFFECTS:
    //MODIFIES:
    private void handleDataLoading() {
        Popup popup = new Popup("Do you want to load classes from file?", "Data Load", this);
        if (popup.getResponse() == 0) {
            executeDataLoading(popup);
            JOptionPane.showMessageDialog(this, "Saved classes loaded successfully");
        } else {
            setNewClasses(popup);
            JOptionPane.showMessageDialog(this, "Application running with new empty classes");
        }

    }

    //EFFECTS:
    //MODIFIES:
    private void setNewClasses(Popup popup) {
        HashMap<Integer, GradeLevel> classes = popup.createNewClasses();
        grade7 = classes.get(7);
        grade8 = classes.get(8);
        grade9 = classes.get(9);
        grade10 = classes.get(10);
        grade11 = classes.get(11);
        grade12 = classes.get(12);
        this.classes = classes;
    }

    //EFFECTS:
    //MODIFIES:
    private void executeDataLoading(Popup popup) {
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
    }

    //EFFECTS:
    //MODIFIES:
    @Override
    public void windowOpened(WindowEvent e) {
        handleDataLoading();
        System.out.println(this.classes.size());
    }

    //EFFECTS:
    //MODIFIES:
    @Override
    public void windowClosing(WindowEvent e) {
        handleWindowClose();

    }

    //EFFECTS:
    //MODIFIES:
    @Override
    public void windowClosed(WindowEvent e) {

    }

    //EFFECTS:
    //MODIFIES:
    @Override
    public void windowIconified(WindowEvent e) {

    }

    //EFFECTS:
    //MODIFIES:
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    //EFFECTS:
    //MODIFIES:
    @Override
    public void windowActivated(WindowEvent e) {

    }

    //EFFECTS:
    //MODIFIES:
    @Override
    public void windowDeactivated(WindowEvent e) {

    }


    //EFFECTS:
    //MODIFIES:
    public HashMap<Integer, GradeLevel> getClasses() {
        return classes;
    }
}
