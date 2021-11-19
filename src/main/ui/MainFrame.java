package ui;

import model.GradeLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.HashMap;

//A class representing the main frame on which all the other components will be placed
public class MainFrame extends JFrame implements WindowListener {
    private GradeLevel grade7;
    private GradeLevel grade8;
    private GradeLevel grade9;
    private GradeLevel grade10;
    private GradeLevel grade11;
    private GradeLevel grade12;
    private HashMap<Integer, GradeLevel> classes;


    //EFFECTS: creates a new frame and adds all the child-components
    public MainFrame() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        this.setTitle("STUDENT MANAGEMENT SYSTEM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(50, 123, 168));
        this.getContentPane().setLayout(new BorderLayout(7, 6));
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(5, 4, 5, 4, Color.CYAN));
        addWindowListener(this);
        new TopPanel(this);
        this.setVisible(true);


    }

    //MODIFIES: this
    //EFFECTS: if user selects to save application state, write classes to file, exits.
    private void handleWindowClose() {
        Popup popup = new Popup("Do you want your save your classes to file?", "Data Saving", this);
        if (popup.getResponse() == 0) {
            popup.writeClasses(this.classes);

        }
        System.exit(0);


    }

    //EFFECTS: prompts user to select an option
    // if user selects yes, load data from file, else, create new classes
    //MODIFIES:this
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


    //MODIFIES:this
    //EFFECTS: creates new classes and assigns them to the class variables
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

    //EFFECTS: reads data from file and assigns it to its variables
    //MODIFIES:this
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


    //MODIFIES: this
    //EFFECTS: repaints and revalidates the frame when the window is open, after adding the side pane
    @Override
    public void windowOpened(WindowEvent e) {
        handleDataLoading();
        new SidePanel(this);
        this.revalidate();
        this.repaint();



    }

    //MODIFIES: this
    //EFFECTS: runs the function handleWindowClose() when the window is closing
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


    public HashMap<Integer, GradeLevel> getClasses() {
        return classes;
    }
}
