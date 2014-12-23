package io.loli.datepicker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Popup;

/**
 * Date panel to popup
 * 
 * @author choco
 *
 */
public class DatePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * panel for controllers
     */
    private JPanel topPanel = new JPanel();

    private JLabel lastYearBtn = new JLabel(" < ");
    private JLabel nextYearBtn = new JLabel(" > ");
    private JLabel lastMonthBtn = new JLabel(" < ");
    private JLabel nextMonthBtn = new JLabel(" > ");

    private JLabel closeBtn = new JLabel(" X ");

    private JPanel daysPanel = new JPanel();
    private JTextField yearTextField = new JTextField(4);
    private JTextField monthTextField = new JTextField(2);

    private Picker picker = null;

    public DatePanel(final Picker picker) {
        this.setLayout(new BorderLayout());
        this.picker = picker;
        add(topPanel, BorderLayout.NORTH);
        yearTextField.setEditable(false);
        monthTextField.setEditable(false);

        Calendar cal = Calendar.getInstance();
        cal.setTime(picker.getDate());

        yearTextField.setText(String.valueOf(cal.get(Calendar.YEAR)));
        monthTextField.setText(String.valueOf(cal.get(Calendar.MONDAY) + 1));

        topPanel.add(lastYearBtn);
        topPanel.add(yearTextField);
        topPanel.add(nextYearBtn);
        topPanel.add(lastMonthBtn);
        topPanel.add(monthTextField);
        topPanel.add(nextMonthBtn);
        topPanel.add(closeBtn);

        lastYearBtn.setBorder(BorderFactory.createRaisedBevelBorder());
        nextYearBtn.setBorder(BorderFactory.createRaisedBevelBorder());
        lastMonthBtn.setBorder(BorderFactory.createRaisedBevelBorder());
        nextMonthBtn.setBorder(BorderFactory.createRaisedBevelBorder());
        closeBtn.setBorder(BorderFactory.createRaisedBevelBorder());

        add(daysPanel, BorderLayout.SOUTH);

        refreshDayBtns();

        lastYearBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                addYear(-1);
                refreshDayBtns();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                lastYearBtn.setBorder(BorderFactory.createLoweredBevelBorder());
            }

            public void mouseReleased(MouseEvent e) {
                lastYearBtn.setBorder(BorderFactory.createRaisedBevelBorder());
            }

        });
        nextYearBtn.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                addYear(1);
                refreshDayBtns();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                nextYearBtn.setBorder(BorderFactory.createLoweredBevelBorder());
            }

            public void mouseReleased(MouseEvent e) {
                nextYearBtn.setBorder(BorderFactory.createRaisedBevelBorder());
            }

        });

        lastMonthBtn.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                addMonth(-1);
                refreshDayBtns();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                lastMonthBtn.setBorder(BorderFactory.createLoweredBevelBorder());
            }

            public void mouseReleased(MouseEvent e) {
                lastMonthBtn.setBorder(BorderFactory.createRaisedBevelBorder());
            }

        });
        nextMonthBtn.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                addMonth(1);
                refreshDayBtns();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                nextMonthBtn.setBorder(BorderFactory.createLoweredBevelBorder());
            }

            public void mouseReleased(MouseEvent e) {
                nextMonthBtn.setBorder(BorderFactory.createRaisedBevelBorder());
            }

        });

        closeBtn.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                picker.close();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                closeBtn.setBorder(BorderFactory.createLoweredBevelBorder());
            }

            public void mouseReleased(MouseEvent e) {
                closeBtn.setBorder(BorderFactory.createRaisedBevelBorder());
            }

        });

        setBorder(BorderFactory.createEtchedBorder());
    }

    /**
     * Add month count to month field
     * 
     * @param i
     *            month to add
     */
    private void addMonth(int i) {
        Calendar cal = Calendar.getInstance();
        cal.set(getDisplayYear(), getDisplayMonth() - 1, 1);
        cal.add(Calendar.MONTH, i);
        monthTextField.setText(String.valueOf(cal.get(Calendar.MONTH) + 1));
    }

    /**
     * Add year count to year field
     * 
     * @param i
     *            year nums to add
     */
    private void addYear(int i) {
        Calendar cal = Calendar.getInstance();
        cal.set(getDisplayYear(), 1, 1);
        cal.add(Calendar.YEAR, i);
        yearTextField.setText(String.valueOf(cal.get(Calendar.YEAR)));
    }

    /**
     * Get int val of year field
     * 
     * @return int val of year field
     */
    private int getDisplayYear() {
        return Integer.parseInt(yearTextField.getText());
    }

    /**
     * Get month val of month field
     * 
     * @return int val of month field
     */
    private int getDisplayMonth() {
        return Integer.parseInt(monthTextField.getText());
    }

    /**
     * Get days of a month
     * 
     * @param year
     *            value of the year
     * @param month
     *            value of the month
     * @return days array of this month
     */
    private String[] getDays(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        int dayNum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        String[] days = new String[dayNum];
        for (int i = 0; i < dayNum; i++) {
            days[i] = String.valueOf(i + 1);
        }

        return days;
    }

    /**
     * Get DAY_OF_WEEK of a day
     * 
     * @param year
     *            the year
     * @param month
     *            the month
     * @param day
     *            the day
     * @return DAY_OF_WEEK of this day
     */
    private int getDayIndex(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Get date arrays before 1st day of the month in that week
     * 
     * 
     * @param year
     *            the year
     * @param month
     *            the month
     * @return date arrays before 1st day of the month in that week
     */
    private Date[] getBeforeDays(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        int dayNum = getDayIndex(year, month, 1) - 1;
        Date[] date = new Date[dayNum];
        if (dayNum > 0)
            for (int i = 0; i < dayNum; i++) {
                cal.add(Calendar.DAY_OF_MONTH, -1);
                date[i] = cal.getTime();
            }

        return date;
    }

    /**
     * Get date arrays after the last day of the month in that week
     * 
     * @param year
     *            the year
     * @param month
     *            the month
     * @return date arrays after 1st day of the month in that week
     */
    private Date[] getAfterDays(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        int dayNum = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(year, month, dayNum);
        int afterDayNum = 7 - getDayIndex(year, month, dayNum);
        Date[] date = new Date[afterDayNum];
        if (afterDayNum > 0)

            for (int i = 0; i < afterDayNum; i++) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
                date[i] = cal.getTime();
            }

        return date;
    }

    /**
     * Add day header to top of month btns
     */
    private void addDayHeadLabels() {
        for (JLabel it : getDayHeadLabels()) {
            it.setVerticalAlignment(JLabel.CENTER);
            it.setHorizontalAlignment(JLabel.CENTER);
            daysPanel.add(it);

        }
    }

    private JLabel[] getDayHeadLabels() {
        return new JLabel[] { new JLabel("SUN"), new JLabel("MON"),
                new JLabel("TUE"), new JLabel("WED"), new JLabel("THU"),
                new JLabel("FRI"), new JLabel("SAT") };
    }

    private List<DayBtn> dayBtns = new ArrayList<DayBtn>();

    /**
     * Refresh day btn tables
     */
    @SuppressWarnings("deprecation")
    private void refreshDayBtns() {

        remove(daysPanel);

        daysPanel = new JPanel();

        daysPanel.removeAll();
        daysPanel.setLayout(new GridLayout(0, 7));

        int displayYear = getDisplayYear();
        int displayMonth = getDisplayMonth();
        String[] days = getDays(displayYear, displayMonth - 1);

        addDayHeadLabels();

        Date d = picker.getDate();

        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int currentYear = cal.get(Calendar.YEAR);

        int currentMonth = cal.get(Calendar.MONTH);
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
        /*
         * Add days label before 1st
         */
        for (Date it : getBeforeDays(displayYear, displayMonth - 1)) {
            JLabel label = new JLabel(String.valueOf(it.getDate()));
            label.setVerticalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setEnabled(false);
            daysPanel.add(label);
        }

        for (String it : days) {
            DayBtn btn = new DayBtn(it);
            if (displayYear == currentYear && displayMonth == currentMonth + 1
                    && currentDay == Integer.parseInt(it)) {
                btn.setBackground(Color.GRAY);
            }

            dayBtns.add(btn);
            daysPanel.add(btn);
        }

        /*
         * Add days label after last day
         */
        for (Date it : getAfterDays(displayYear, displayMonth - 1)) {
            JLabel label = new JLabel(String.valueOf(it.getDate()));
            label.setVerticalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            daysPanel.add(label);
            label.setEnabled(false);
        }

        add(daysPanel);

        /*
         * Update ui
         */
        updateUI();
        if (picker.getPopup() != null) {
            Method method;
            try {
                method = Popup.class.getDeclaredMethod("pack");
                method.setAccessible(true);
                method.invoke(picker.getPopup());
            } catch (Exception e) {
            }
        }
    }

    class DayBtn extends JLabel implements ActionListener {
        private static final long serialVersionUID = 1L;
        private String day;
        private Date date;

        private Color originColor;

        boolean clicked = false;

        DayBtn(String str) {
            super(str);
            this.day = str;
            Calendar cal = Calendar.getInstance();
            cal.set(getDisplayYear(), getDisplayMonth() - 1,
                    Integer.parseInt(str));
            date = cal.getTime();
            // setBorder(BorderFactory.createl));
            setVerticalAlignment(JLabel.CENTER);
            setHorizontalAlignment(JLabel.CENTER);
            originColor = this.getBackground();
            setOpaque(true);
            this.setEnabled(!picker.getDateFilter().filter(date));
            this.setBorder(BorderFactory.createEtchedBorder());

            if (this.isEnabled())
                this.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);

                        Calendar cal = Calendar.getInstance();
                        cal.setTime(picker.getDate());
                        cal.set(getDisplayYear(), getDisplayMonth() - 1,
                                Integer.parseInt(day));
                        Date calDate = cal.getTime();
                        picker.set(calDate);

                        for (DayBtn btn : dayBtns) {
                            btn.setBackground(originColor);
                            btn.clicked = false;
                        }
                        setBackground(Color.GRAY);
                        clicked = true;
                        DatePanel.this.repaint();
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        super.mouseEntered(e);
                        setBackground(Color.GRAY);
                        DatePanel.this.repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        super.mouseExited(e);
                        if (!clicked) {
                            setBackground(originColor);
                        }
                        DatePanel.this.repaint();
                    }
                });
        }

        public void actionPerformed(ActionEvent e) {
            Calendar cal = Calendar.getInstance();
            cal.set(getDisplayYear(), getDisplayMonth() - 1,
                    Integer.parseInt(day));
            Date calDate = cal.getTime();
            picker.set(calDate);
        }

        public Date getDate() {
            return date;
        }
    }
}
