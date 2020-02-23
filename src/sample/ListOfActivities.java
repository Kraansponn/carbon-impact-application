package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class ListOfActivities implements Serializable {

    private ArrayList ListOfActivities = new ArrayList<>();

    public ListOfActivities(ArrayList listOfActivities) {
        ListOfActivities = listOfActivities;
    }

    public ArrayList getListOfActivities() {
        return ListOfActivities;
    }

    public void setListOfActivities(ArrayList listOfActivities) {
        ListOfActivities = listOfActivities;
    }

    public boolean addActivity(Activity activity) {
        return ListOfActivities.add(activity);
    }

    public boolean removeActivity(Activity activity) {
        return ListOfActivities.remove(activity);
    }

    public static void createNewActivity(String week, String date, String activity, String point) {
        
            int weekAsInt = Integer.parseInt(week);
            int dateAsInt = Integer.parseInt(date);
            int pointAsInt = Integer.parseInt(point);
            if ((pointAsInt > -11) && (pointAsInt < 11)) {
                new Activity(weekAsInt, dateAsInt, activity, pointAsInt);
                System.out.println("Activity added");
            } else {
                System.out.println("Failed to add activity");
            }

    }
}