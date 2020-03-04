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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    private static ListOfActivities listOfActivities = new ListOfActivities();

    private TableView<Activity> table = new TableView<Activity>();
    private ObservableList<Activity> data = FXCollections.observableArrayList(); //creates arraylist to store table data

    @Override
    public void start(Stage primaryStage) throws Exception {
        //defining the Menu size
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Kornel");
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        //Creating the labels
        Label labelWeek = new Label("Week:     ");
        Label labelDate = new Label("Date:      ");
        Label labelActivity = new Label("Activity:  ");
        Label labelPoints = new Label("Points:    ");

        //creating the text fields for the labels
        TextField textFieldWeek = new TextField();
        TextField textFieldDate = new TextField();
        TextField textFieldActivity = new TextField();
        TextField textFieldPoints = new TextField();

        //Creating a table to display data
        table.setEditable(false);

        TableColumn<Activity, Integer> weekCol = new TableColumn<>("Week");     //table column name
        weekCol.setCellValueFactory(                                                 //setting cell value
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

        table.setItems(data);                                                   //defining where the table gets its data from
        table.getColumns().addAll(weekCol, dateCol, activityCol, pointsCol);    //adding all the columns to the table

        //Creating Grid
        GridPane gridPane = new GridPane();

        //setting postions on labels and text fields in grid
        gridPane.add(labelWeek, 0, 0);
        gridPane.add(textFieldWeek, 1, 0);
        gridPane.add(labelDate, 0, 1);
        gridPane.add(textFieldDate, 1, 1);
        gridPane.add(labelActivity, 0, 2);
        gridPane.add(textFieldActivity, 1, 2);
        gridPane.add(labelPoints, 0, 3);
        gridPane.add(textFieldPoints, 1, 3);

        //Creating buttons

        //button for adding in new activities
        Button addButton = new Button("add");
        addButton.setOnAction(event -> {
            try {
                int newWeek = Integer.parseInt(textFieldWeek.getText()); //getting data out of text field and changing to int as needed
                int newDate = Integer.parseInt(textFieldDate.getText());
                String newActivity = textFieldActivity.getText();
                int newPoints = Integer.parseInt(textFieldPoints.getText());

                if ((newPoints > -11) && (newPoints < 11)) {                        //making sure the points value is within a desired value
                    data.add(new Activity(newWeek, newDate, newActivity, newPoints)); //creating new object
                    System.out.println("Activity added");
                } else {
                    System.out.println("Some value too high"); //error message
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Please Make sure that proper values are being passed in");
            }
            textFieldWeek.clear(); // clearing text fields
            textFieldDate.clear();
            textFieldActivity.clear();
            textFieldPoints.clear();
        });

        // button to remove entry from table
        Button removeButton = new Button("remove");
        removeButton.setOnAction(event -> {
            table.getItems().removeAll(
                    table.getSelectionModel().getSelectedItems());//removes selected item
        });

        //button to refresh table contents
        Button listButton = new Button("list");
        listButton.setOnAction(event -> {
            table.refresh();
        });

        //button that adds up all of the points
        Button summaryButton = new Button("summary");
        summaryButton.setOnAction(event -> {

                    int pointstotal = 0;

                    for (int i = 0; i < data.size(); i++) {
                        pointstotal = pointstotal + data.get(i).getPoints();  //getting the total points value
                    }

                    Alert alert = new Alert(Alert.AlertType.INFORMATION); //making a pop up
                    alert.setTitle("Total Of All Points");
                    alert.setHeaderText(null);
                    alert.setContentText("Your current point balance is " + pointstotal);

                    alert.showAndWait();
                }
        );

        //loads in data from a fixed position
        Button loadButton = new Button("load");
        loadButton.setOnAction(event -> {
            data.removeAll(data); //clears table
            try {
                FileInputStream fileIn = new FileInputStream("Save.ser"); //loads in file
                ObjectInputStream in = new ObjectInputStream(fileIn);

                List<Activity> list = (List<Activity>) in.readObject(); //writes file content to temp arrray

                in.close();
                fileIn.close();

                for (int i = 0; i < list.size(); i++) { //loops and adds in content from list to data
                    data.add(list.get(i));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            table.refresh();
        });

        Button saveButton = new Button("save");
        saveButton.setOnAction(event -> {
            try {
                // write object to file
                FileOutputStream fos = new FileOutputStream("Save.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(new ArrayList<Activity>(data));
                oos.close();
                System.out.println("file saved");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Exit button to end the application
        Button exitButton = new Button("exit");
        exitButton.setOnAction(e -> Platform.exit());

        //Setting up Tabs

        TabPane tabPane = new TabPane();

        Tab tab1 = new Tab("Introduction", new Label("Introduction"));
        Tab tab2 = new Tab("Activity Manager"  , new Label("Activity Manger"));
        Tab tab3 = new Tab("Results" , new Label("Results"));

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);



        //Tab2
        HBox hBox1Tab2 = new HBox();
        hBox1Tab2.getChildren().addAll(labelWeek, textFieldWeek);
        HBox hBox2Tab2 = new HBox();
        hBox2Tab2.getChildren().addAll(labelDate, textFieldDate);
        HBox hBox3Tab2 = new HBox();
        hBox3Tab2.getChildren().addAll(labelActivity, textFieldActivity);
        HBox hBox4Tab2 = new HBox();
        hBox4Tab2.getChildren().addAll(labelPoints, textFieldPoints);
        HBox hBox5Tab2 = new HBox();
        hBox5Tab2.getChildren().addAll(addButton, removeButton, listButton, summaryButton);
        HBox hBox6Tab2 = new HBox();
        hBox6Tab2.getChildren().addAll(table);
        HBox hBox7Tab2 = new HBox();
        hBox7Tab2.getChildren().addAll(loadButton, saveButton, exitButton);

        VBox vBoxTab2 = new VBox();
        vBoxTab2.getChildren().addAll(tabPane,hBox1Tab2,hBox2Tab2,hBox3Tab2,hBox4Tab2,hBox5Tab2,hBox6Tab2,hBox7Tab2);
//
//        //setting postions on buttons in grid
//        gridPane.add(addButton, 0, 4);
//        gridPane.add(removeButton, 1, 4);
//        gridPane.add(listButton, 2, 4);
//        gridPane.add(summaryButton, 3, 4);
//        gridPane.add(table, 0, 5);
//        gridPane.add(loadButton, 0, 6);
//        gridPane.add(saveButton, 1, 6);
//        gridPane.add(exitButton, 3, 6);
//
//        //Setting the padding
//        gridPane.setPadding(new Insets(10, 10, 10, 10));
//
//        //Setting the vertical and horizontal gaps between the columns
//        gridPane.setVgap(5);
//        gridPane.setHgap(5);

        //Displaying the Scene
        primaryStage.setScene(new Scene(vBoxTab2));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
