package ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class StudentsListSlider implements ChangeListener {
    private JSlider slider;
    private JPanel panel;


    public StudentsListSlider() {
        panel = new JPanel();
        slider = new JSlider();

    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
