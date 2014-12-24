package io.loli.datepicker;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.PopupFactory;

public class TimePicker extends AbstractPicker {
    {
        format = "HH:mm:ss";
    }

    private boolean setTimeAtSetup = false;
    
    public TimePicker(final JTextField field, String format) {
        if (format != null){
            this.format = format;
        }
        this.field = field;
        if (setTimeAtSetup) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            setTime(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
                    cal.get(Calendar.SECOND));
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
                popup = factory.getPopup(field, timePanel, (int) field
                        .getLocationOnScreen().getX(), (int) field
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

    /**
     * Add a time picker to a text field with date format
     * 
     * @param field
     *            the text field you want to add to
     * @param format
     *            date format string
     */
    public static void timePicker(JTextField field, String format) {
        new TimePicker(field, format);
    }

    /**
     * Add a time picker to a text field
     * 
     * @param field
     *            the text field you want to add to
     */
    public static void timePicker(JTextField field) {
        new TimePicker(field, null);
    }

    /**
     * Get time field with format 'HH:mm:ss'
     * 
     * @return text field
     */
    public static JTextField getTimeField() {
        JTextField field = new JTextField();
        field.setEditable(false);
        timePicker(field);
        return field;
    }

    /**
     * Get time field with format
     * 
     * @param format
     *            time format
     * @return text field with format
     */
    public static JTextField getDateField(String format) {
        JTextField field = new JTextField();
        field.setEditable(false);
        timePicker(field);
        return field;
    }

   


    public DateFilter getDateFilter() {
        return new BasicDateFilter();
    }

    
    public void set(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDate());

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, cal2.get(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal2.get(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal2.get(Calendar.SECOND));

        SimpleDateFormat fmt = new SimpleDateFormat(format);
        String result = fmt.format(cal.getTime());
        getField().setText(result);
    }
 

}
