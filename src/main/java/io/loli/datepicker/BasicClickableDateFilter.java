package io.loli.datepicker;

import java.util.Date;

public class BasicClickableDateFilter implements ClickableDateFilter {

    /*
     * (non-Javadoc)
     * 
     * @see io.loli.datepicker.DateFilter#filter(java.util.Date)
     */
    public boolean filter(Date date) {
        return false;
    }

}
