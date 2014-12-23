package io.loli.datepicker;

import java.util.Date;

import javax.swing.JTextField;
import javax.swing.Popup;

public interface Picker {

    Date getDate();

    JTextField getField();

    Popup getPopup();

    void setPopup(Popup popup);

    DateFilter getDateFilter();

    void set(Date date);
    
    void close();
    
    String getFormat();
}
