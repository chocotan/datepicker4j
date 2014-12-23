package io.loli.datepicker;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TimePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private NumPanel hourPanel = new NumPanel(24, null);

    private NumPanel minPanel = new NumPanel(60, hourPanel);

    private NumPanel secondPanel = new NumPanel(60, minPanel);

    private JPanel controlPanel = new JPanel();

    private Picker picker;

    public TimePanel(final Picker picker) {
        this.setLayout(new GridLayout(1, 0));
        this.setVisible(true);
        this.setSize(100, 200);
        this.picker = picker;
        add(hourPanel);
        int width = 60;

        if (picker.getFormat().contains("m")) {
            add(minPanel);
            width += 60;
        }
        if (picker.getFormat().contains("s")) {
            add(secondPanel);
            width += 60;
            this.setPreferredSize(new Dimension(width, 80));
        }
        this.setPreferredSize(new Dimension(width, 80));
        JButton nowBtn = new JButton("Now");
        JButton okBtn = new JButton("Ok");
        controlPanel.setLayout(new GridLayout(2, 1));
        controlPanel.add(nowBtn);
        controlPanel.add(okBtn);

        set(picker.getDate());

        nowBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setNow();
            }
        });

        okBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                picker.set(getDate());
                picker.close();
            }
        });

        add(controlPanel);

    }

    public void setNow() {
        set(new Date());
    }

    public void set(int h, int m, int s) {
        hourPanel.set(h);
        minPanel.set(m);
        secondPanel.set(s);
    }

    public void set(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        set(hour, min, second);
    }

    public void set(int h, int m) {
        hourPanel.set(h);
        minPanel.set(m);
    }

    class NumPanel extends JPanel {
        private static final long serialVersionUID = 1L;

        private int num;
        private JTextField numField = new JTextField();

        private JButton upBtn = new JButton("+");
        private JButton downBtn = new JButton("-");

        private Timer timer;

        private NumPanel parent;

        public void add() {
            int result = get() + 1;
            if (result > num - 1) {
                result = 0;
                if (parent != null)
                    parent.add();
            }
            set(result);
        }

        public void minus() {
            int result = get() - 1;
            if (result < 0) {
                result = num - 1;
                if (parent != null)
                    parent.minus();
            }
            set(result);
        }

        public NumPanel(final int num, NumPanel parent) {
            this.num = num;
            this.parent = parent;
            this.setLayout(new GridLayout(3, 1));
            add(upBtn);
            add(numField);
            add(downBtn);

            upBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (timer == null) {
                        timer = new Timer();
                    }
                    add();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            add();
                        }
                    }, 300, 50);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                }
            });
            downBtn.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {

                    if (timer == null) {
                        timer = new Timer();
                    }
                    minus();

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            minus();
                        }
                    }, 300, 50);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                }
            });

        }

        public void set(int i) {
            numField.setText(String.valueOf(i));
            picker.set(getDate());
        }

        public int get() {
            int nowNum = 0;
            try {
                nowNum = Integer.parseInt(numField.getText());
            } catch (Exception e) {
            }
            return nowNum;
        }
    }

    public Date getDate() {
        int hour = hourPanel.get();
        int min = minPanel.get();
        int second = secondPanel.get();
        Calendar cal = Calendar.getInstance();
        cal.setTime(picker.getDate());
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.SECOND, second);
        return cal.getTime();
    }

}
