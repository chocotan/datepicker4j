package io.loli.datepicker;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class DateTimePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private DatePanel datePanel;
    private TimePanel timePanel;

    public DateTimePanel(Picker picker) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        datePanel = new DatePanel(picker);
        timePanel = new TimePanel(picker);
        add(datePanel);
        add(timePanel);
    }

}
