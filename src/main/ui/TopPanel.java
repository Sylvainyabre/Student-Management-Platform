package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

//Class representing the TopPanel with text
public class TopPanel extends JPanel {

//Create a new panel and sets add its children
    public TopPanel(MainFrame frame) {
        this.setBorder(new LineBorder(Color.PINK, 3));
        this.setBackground(new Color(228, 231, 237));
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel topLabel = new JLabel("<html><div style='text-align:center;'>"
                + "STUDENT MANAGEMENT SOFTWARE"
                + "</div></html>");
        topLabel.setFont(new Font("Serif", Font.BOLD, 40));
        topLabel.setForeground(new Color(50, 123, 168));
        this.add(topLabel);
        Container container = frame.getContentPane();
        container.add(this, BorderLayout.NORTH);

    }

}
