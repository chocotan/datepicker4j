package io.loli.datepicker;

import javax.swing.JTextField;
import javax.swing.Popup;

public abstract class AbstractPicker implements Picker {

    protected String format;

    protected Popup popup;

    protected JTextField field;

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
}
