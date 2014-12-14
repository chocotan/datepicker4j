package io.loli.datepicker;

import java.util.Date;

interface ClickableDateFilter {

    /**
     * Make date btn unclickable if returns true
     * 
     * @param date
     * @return false if this date btn is clickable
     */
    boolean filter(Date date);
}
