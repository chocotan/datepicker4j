package io.loli.datepicker;

import java.util.Date;

interface DateFilter {

    /**
     * Make date btn unclickable if returns true
     * 
     * @param date date to check
     * @return false if this date btn is clickable
     */
    boolean filter(Date date);
}
