package edu.hitsz.dao;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyRecord implements Serializable {

    private static final long serialVersionUID = 1987342583254420574L;

    private String name;

    private int score;

    private int rank;

    private Calendar calendar;

    public int getScore() {
        return score;
    }

    public int getRank() {
        return rank;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyRecord(String name, int score, Calendar calendar) {
        this.name = name;
        this.score = score;
        this.calendar = calendar;
    }

    @Override
    public String toString() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(calendar.getTime());
        return "name: "+this.name+"\tsocre: "+this.score+"\tdate: "+time;
    }
}
