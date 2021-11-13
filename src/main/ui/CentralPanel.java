package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CentralPanel extends JPanel {
    private JButton studentUpdateButton = new JButton("Grade Entry");
    private JButton studentRankingButton = new JButton("Ranking");
    private JButton registrationButton = new JButton("Register");
    private JButton deleteButton = new JButton("Delete");
    private JButton transcriptButton = new JButton("Transcript");
    private JButton studentsButton = new JButton("Students");
    private JButton classesButton = new JButton("Classes");
    private JButton classSummaryButton = new JButton("Class Summary");

    //EFFECT:
    //MODIFIES
    public CentralPanel(JFrame frame) {
        Container container = frame.getContentPane();
        container.add(this, BorderLayout.WEST);
        this.setLayout(new FlowLayout(4, 4, 4));
        this.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
        this.setBackground(Color.WHITE);
        addLeftPanel();

    }

    //EFFECT:
    //MODIFIES:
    private void addLeftPanel() {
        JPanel leftGridPanel = new JPanel();
        leftGridPanel.setLayout(new GridLayout(20, 1, 5, 5));
        leftGridPanel.setBorder(new LineBorder(Color.CYAN,4));
        leftGridPanel.setBackground(new Color(172, 190, 191));
        leftGridPanel.add(studentUpdateButton);
        leftGridPanel.add(studentRankingButton);
        leftGridPanel.add(deleteButton);
        leftGridPanel.add(registrationButton);
        leftGridPanel.add(transcriptButton);
        leftGridPanel.add(studentsButton);
        leftGridPanel.add(classesButton);
        leftGridPanel.add(classSummaryButton);
        this.add(leftGridPanel);
    }
    //EFFECT:
    //MODIFIES:

}