package sample;

import java.io.Serializable;

public class Activity implements Serializable, Comparable {
    private int week;
    private int date;
    private String activity;
    private int points;

    public Activity(int week, int date, String activity, int points) {
        this.week = week;
        this.date = date;
        this.activity = activity;
        this.points = points;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public int compareTo(Activity comparestu) {
        int compareage = ((Activity) comparestu).getPoints();
        return this.points - compareage;
    }

    @Override
    public String toString() {
        return "[ date=" + date + ", week=" + week + ", activity=" + activity + ", points=" + points + "]";
    }
}
