package io.loli.datepicker;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;

class DatePicker {
    /**
     * Default date format
     */
    private String format = "yyyy-MM-dd";
    private SimpleDateFormat simpleDateFormat;
    JTextField field;
    ClickableDateFilter filter;

    private boolean setTimeAtSetup = false;

    public Date getDate() {
        try {
            return simpleDateFormat.parse(this.field.getText());
        } catch (Exception e) {
            return new Date();
        }
    }

    /**
     * Get text of text field
     * 
     * @return text of text field
     */
    public String getDateText() {
        return this.field.getText();
    }

    Popup popup;

    /**
     * @param field
     *            to add to
     * @param format
     *            date format
     */
    public DatePicker(final JTextField field, String format,
            ClickableDateFilter filter) {
        this.field = field;
        if (format != null) {
            this.format = format;
        }
        this.filter = filter;
        simpleDateFormat = new SimpleDateFormat(this.format);
        if (setTimeAtSetup)
            this.field.setText(simpleDateFormat.format(new Date()));

        this.field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (popup != null) {
                    popup.hide();
                    popup = null;
                    return;
                }
                DatePanel timePanel = new DatePanel(DatePicker.this);
                PopupFactory factory = PopupFactory.getSharedInstance();

                popup = factory.getPopup(field, timePanel, (int) field
                        .getLocationOnScreen().getX(), (int) field
                        .getLocationOnScreen().getY() + field.getHeight());
                popup.show();
            }
        });
    }

    DatePicker(JTextField field) {
        this(field, null, new BasicClickableDateFilter());
    }

    public static SimpleDateFormat getDateFormat(String format) {
        return new SimpleDateFormat(format);
    }

    /**
     * Get year val from text field
     * 
     * @return year val
     */
    public int getShowYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDate());
        return cal.get(Calendar.YEAR);
    }

    /**
     * Get month val from text field
     * 
     * @return month val
     */
    public int getShowMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDate());
        return cal.get(Calendar.MONDAY);
    }

    /**
     * Set date to text field
     * 
     * @param year
     *            year to set
     * @param month
     *            month to set
     * @param day
     *            day to set
     */
    void selectDay(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        Date date = cal.getTime();
        this.field.setText(simpleDateFormat.format(date));
        if (popup != null) {
            popup.hide();
            popup = null;
        }
    }

    /**
     * Add a date picker to a text field
     * 
     * @param field
     *            the text field you want to add to
     */
    public static void datePicker(JTextField field) {
        datePicker(field, null);
    }

    /**
     * Add a date picker to a text field with date format
     * 
     * @param field
     *            the text field you want to add to
     * @param format
     *            date format string
     */
    public static void datePicker(JTextField field, String format) {
        new DatePicker(field, format, new BasicClickableDateFilter());
    }

    /**
     * Add a date picker to a text field with date format
     * 
     * @param field
     *            the text field you want to add to
     * @param format
     *            date format string
     * @param filter
     *            filter
     */
    public static void datePicker(JTextField field, String format,
            ClickableDateFilter filter) {
        new DatePicker(field, format, filter);
    }

    /**
     * Get date field with format 'yyyy-MM-dd'
     * 
     * @return text field with format 'yyyy-MM-dd'
     */
    public static JTextField getDateField() {
        JTextField field = new JTextField();
        field.setEditable(false);
        datePicker(field);
        return field;
    }

    /**
     * Get date field with format
     * 
     * @param format
     *            date format
     * @return text field with format
     */
    public static JTextField getDateField(String format) {
        JTextField field = new JTextField();
        field.setEditable(false);
        datePicker(field);
        return field;
    }
}
