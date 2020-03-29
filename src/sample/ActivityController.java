package sample;

public class ActivityController {
    private Activity model;
    private ActivityView view;

    public ActivityController(Activity model, ActivityView view) {
        this.model = model;
        this.view = view;
    }

    public int getWeek() {
        return model.getWeek();
    }

    public void setWeek(int week) {
        model.setWeek(week);
    }

    public int getDate() {
        return model.getDate();
    }

    public void setDate(int date) {
        model.setDate(date);
    }

    public String getActivity() {
        return model.getActivity();
    }

    public void setActivity(String activity) {
        model.setActivity(activity);
    }

    public int getPoints() {
        return model.getPoints();
    }

    public void setPoints(int points) {
        model.setPoints(points);

    }

    public void updateView() {
        view.printActivityDetails(model.getWeek(), model.getDate(), model.getActivity(), model.getPoints());
    }
}
