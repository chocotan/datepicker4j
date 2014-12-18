package io.loli.datepicker;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;

public class TimePicker {
    private String format = "HH:mm:ss";
    private JTextField field;

    private boolean setTimeAtSetup = true;
    Popup popup;

    public TimePicker(final JTextField field, String format) {
        this.format = format;
        this.field = field;
        if (setTimeAtSetup) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            setTime(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
        }

        this.field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (popup != null) {
                    popup.hide();
                    popup = null;
                    return;
                }
                TimePanel timePanel = new TimePanel(TimePicker.this);
                PopupFactory factory = PopupFactory.getSharedInstance();

                popup = factory.getPopup(field, timePanel, (int) field.getLocationOnScreen().getX(), (int) field
                    .getLocationOnScreen().getY() + field.getHeight());
                popup.show();
            }
        });

    }

    public void setTime(int hour, int min, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.SECOND, second);
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        String str = fmt.format(cal.getTime());
        field.setText(str);
    }
    
    
    

}
