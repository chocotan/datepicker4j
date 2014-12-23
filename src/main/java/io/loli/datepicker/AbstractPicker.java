package io.loli.datepicker;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.Popup;

public abstract class AbstractPicker implements Picker {

    protected String format;

    protected Popup popup;

    protected JTextField field;

    protected DateFilter filter;

    public DateFilter getFilter() {
        return filter;
    }

    public String getFormat() {
        return format;
    }

    public Popup getPopup() {
        return popup;
    }

    public void setPopup(Popup popup) {
        this.popup = popup;
    }

    public JTextField getField() {
        return field;
    }

    public void close() {
        if (getPopup() != null) {
            getPopup().hide();
            setPopup(null);
            return;
        }
    }

    /**
     * Convert text of textfield to Date
     * 
     * @return Date shown at textfield or new Date if failed to parse
     */
    public Date getDate() {
        Date date = new Date();
        try {
            String dateText = field.getText();
            SimpleDateFormat fmt = new SimpleDateFormat(format);
            date = fmt.parse(dateText);
        } catch (Exception e) {
        }
        return date;
    }
}
