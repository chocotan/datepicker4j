package io.loli.datepicker;

import java.util.Date;

public class BasicDateFilter implements DateFilter {

    /*
     * (non-Javadoc)
     * 
     * @see io.loli.datepicker.DateFilter#filter(java.util.Date)
     */
    public boolean filter(Date date) {
        return false;
    }

}
