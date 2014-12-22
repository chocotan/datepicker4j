package io.loli.datepicker;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class TestTimePicker {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextField field = new JTextField(10);
        new TimePicker(field, "HH:mm");
        frame.add(field);
        frame.pack();
    }
}