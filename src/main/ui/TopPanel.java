package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TopPanel extends JPanel {
    private JButton registrationButton;
    private JButton deleteButton;
    private JButton transcriptButton;

    public TopPanel(MainFrame frame) {
        this.setBorder(new LineBorder(Color.PINK, 3));
        this.setBackground(new Color(228, 231, 237));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));


        registrationButton = new JButton("Register  student");
        deleteButton = new JButton("Remove student");
        transcriptButton = new JButton("Transcript");

        JLabel topLabel = new JLabel("<html><div style='text-align:center;'>"
                + "STUDENT MANAGEMENT SOFTWARE"
                + "</div></html>");
        topLabel.setFont(new Font("Serif", Font.BOLD, 40));
        topLabel.setForeground(new Color(50, 123, 168));
        //topLabel.setOpaque(true);
        this.add(topLabel);
        Container container = frame.getContentPane();
        container.add(this, BorderLayout.NORTH);

    }

    //EFFECT:
    //MODIFIES:
    public JButton getRegistrationButton() {
        return registrationButton;
    }

    //EFFECT:
    //MODIFIES:
    public JButton getDeleteButton() {
        return deleteButton;
    }

    //EFFECT:
    //MODIFIES:
    public JButton getTranscriptButton() {
        return transcriptButton;
    }
}
