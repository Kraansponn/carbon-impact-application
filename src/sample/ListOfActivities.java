package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfActivities implements Serializable {

    private ArrayList<Activity> activities = new ArrayList<>();

    public ListOfActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    public ListOfActivities() {
    }

    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }

}