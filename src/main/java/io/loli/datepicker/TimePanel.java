package io.loli.datepicker;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class TimePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private TimePicker picker;

    private JTextField hourField = new JTextField(3);

    private JTextField minField = new JTextField(3);

    private JTextField secField = new JTextField(3);

    public TimePanel(TimePicker picker) {
        this.picker = picker;
        this.setSize(300, 300);
        this.setVisible(true);
        add(hourField);
        add(minField);
        add(secField);
    }

}
