package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;

import persistence.*;

import model.*;

public class MainFrame extends JFrame {
    private GradeLevel grade7;
    private GradeLevel grade8;
    private GradeLevel grade9;
    private GradeLevel grade10;
    private GradeLevel grade11;
    private GradeLevel grade12;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public MainFrame() {
        this.setVisible(true);
        this.setSize(1000,1700);
        this.setTitle("STUDENT MANAGEMENT SYSTEM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(50, 123, 168));


    }
}
