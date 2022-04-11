package edu.hitsz.dao;

import java.util.Calendar;

public class Record {
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

    public Record(String name, int score, int rank, Calendar calendar) {
        this.name = name;
        this.score = score;
        this.rank = rank;
        this.calendar = calendar;
    }
}
