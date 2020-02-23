package sample;

import java.io.Serializable;

public class Activity implements Serializable {
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

    @Override
    public String toString() {
        return "Activity{" +
                "week=" + week +
                ", date=" + date +
                ", activity='" + activity + '\'' +
                ", points=" + points +
                '}';
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
}
