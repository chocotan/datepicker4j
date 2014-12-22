package io.loli.datepicker;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;

public class DateTimePicker {
    private JTextField field;
    
    private Popup popup;
    
    public DateTimePicker(final JTextField field,String format){
        this.field = field;
        this.field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (popup != null) {
                    popup.hide();
                    popup = null;
                    return;
                }
                DateTimePanel timePanel = new DateTimePanel(DateTimePicker.this);
                PopupFactory factory = PopupFactory.getSharedInstance();
                popup = factory.getPopup(field, timePanel, (int) field
                        .getLocationOnScreen().getX(), (int) field
                        .getLocationOnScreen().getY() + field.getHeight());
                popup.show();
            }
        });
    }
}
