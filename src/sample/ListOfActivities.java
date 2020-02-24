package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfActivities implements Serializable {

    private ArrayList<Activity> activities = new ArrayList<>();

    public ListOfActivities() {
    }

    public ListOfActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }


    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }


    public static void createNewActivity(String week, String date, String activity, String point) {
        try {
            int weekAsInt = Integer.parseInt(week);
            int dateAsInt = Integer.parseInt(date);
            int pointAsInt = Integer.parseInt(point);
            if ((pointAsInt > -11) && (pointAsInt < 11)) {
                new Activity(weekAsInt, dateAsInt, activity, pointAsInt);
                System.out.println("Activity added");
            } else {
                System.out.println("Some value too high");
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Please Make sure that proper values are being passed in");
        }
    }
}