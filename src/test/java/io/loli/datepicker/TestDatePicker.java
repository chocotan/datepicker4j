package io.loli.datepicker;

import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class TestDatePicker {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextField field = new JTextField();
        DatePicker.datePicker(field, "yyyy-MM-dd", new ClickableDateFilter() {
            public boolean filter(Date date) {
                return date.getDay() == 0 || date.getDay() == 6;
            }
        });
        frame.add(field);
        frame.pack();
    }
}
