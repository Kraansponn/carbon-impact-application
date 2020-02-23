package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    //
    private TableView<Activity> table = new TableView<Activity>();
    //
    private final ObservableList<Activity> data =
            FXCollections.observableArrayList(
                    new Activity(1, 22, "Fishing",7),
                    new Activity(2, 26, "running",2),
                    new Activity(33, 16, "Driving to work",-8),
                    new Activity(7, 21, "Eating out of season fruit",-5),
                    new Activity(14, 31, "swimming in river",4)
            );

    @Override
    public void start(Stage primaryStage) throws Exception {
        //defining the Menu size
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Kornel");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        //Creating the labels
        Label labelWeek = new Label("Week: ");
        Label labelDate = new Label("Date: ");
        Label labelActivity = new Label("Activity: ");
        Label labelPoints = new Label("Points: ");

        //creating the text fields for the labels
        TextField textFieldWeek = new TextField();
        TextField textFieldDate = new TextField();
        TextField textFieldActivity = new TextField();
        TextField textFieldPoints = new TextField();

        //Creating a table to display data
//        final Label label = new Label("Activities Log");
        table.setEditable(false);
        //table Column names
        TableColumn<Activity, Integer> weekCol = new TableColumn<>("Week");
        weekCol.setCellValueFactory(
                new PropertyValueFactory<Activity, Integer>("week"));

        TableColumn<Activity, Integer> dateCol = new TableColumn<>("date");
        dateCol.setCellValueFactory(
                new PropertyValueFactory<Activity, Integer>("date"));

        TableColumn<Activity, String> activityCol = new TableColumn<>("Activity");
        activityCol.setCellValueFactory(
                new PropertyValueFactory<Activity, String>("activity"));

        TableColumn<Activity, Integer> pointsCol = new TableColumn<Activity, Integer>("Points");
        pointsCol.setCellValueFactory(
                new PropertyValueFactory<Activity, Integer>("points"));

        table.setItems(data);
        table.getColumns().addAll(weekCol, dateCol, activityCol, pointsCol);

        //Creating Grid
        GridPane gridPane = new GridPane();

        //setting postions on labels and text fields in grid
        gridPane.add( labelWeek, 0,0);
        gridPane.add( textFieldWeek, 1,0);
        gridPane.add( labelDate, 0,1);
        gridPane.add( textFieldDate, 1,1);
        gridPane.add( labelActivity, 0,2);
        gridPane.add( textFieldActivity, 1,2);
        gridPane.add(labelPoints, 0, 3);
        gridPane.add(textFieldPoints, 1, 3);

        //Creating all the buttons
        Button addButton = new Button("add");
        addButton.setOnAction(event -> {
            String newWeek = textFieldWeek.getText();
            String newDate = textFieldDate.getText();
            String newActivity = textFieldActivity.getText();
            String newPoints = textFieldPoints.getText();
            ListOfActivities.createNewActivity(newWeek,newDate,newActivity,newPoints);
            textFieldWeek.clear();
            textFieldDate.clear();
            textFieldActivity.clear();
            textFieldPoints.clear();
            System.out.println();
        });



        Button removeButton = new Button("remove");
        Button listButton = new Button("list");
        Button summaryButton = new Button("summary");
        Button loadButton = new Button("load");
        Button saveButton = new Button("save");
        Button exitButton = new Button("exit");
        exitButton.setOnAction(e -> Platform.exit());;

        //setting postions on buttons in grid
        gridPane.add(addButton, 0, 4);
        gridPane.add(removeButton, 1, 4);
        gridPane.add(listButton, 2, 4);
        gridPane.add(summaryButton, 3, 4);
        gridPane.add(table, 0, 5);
        gridPane.add(loadButton, 0, 6);
        gridPane.add(saveButton, 1, 6);
        gridPane.add(exitButton,3, 6);

        //Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        //Displaying the Scene
        primaryStage.setScene(new Scene(gridPane));
        primaryStage.show();

//        TabPane tabPane = new TabPane();
//
//        Tab tab1 = new Tab("Planes", new Label("Show all planes available"));
//        Tab tab2 = new Tab("Cars"  , new Label("Show all cars available"));
//        Tab tab3 = new Tab("Boats" , new Label("Show all boats available"));
//
//        tabPane.getTabs().add(tab1);
//        tabPane.getTabs().add(tab2);
//        tabPane.getTabs().add(tab3);
//
//        VBox vBox = new VBox(tabPane);
//        Scene scene = new Scene(vBox);
//
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("JavaFX App");
//
//        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
