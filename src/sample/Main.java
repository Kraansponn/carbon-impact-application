package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.NumberBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
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
import java.util.*;

public class Main extends Application {
    private TableView<Activity> table = new TableView<Activity>();
    //    private TableView<Activity> table2 = new TableView<Activity>();
    private ObservableList<Activity> data = FXCollections.observableArrayList(); //creates arraylist to store table data

    @Override
    public void start(Stage primaryStage) throws Exception {
        //defining the Menu size
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Kornel");
        primaryStage.setWidth(700);
        primaryStage.setHeight(700);

        //Creating the labels
        Label labelWeek = new Label("Week:     ");
        Label labelDate = new Label("Date:      ");
        Label labelActivity = new Label("Activity:  ");
        Label labelPoints = new Label("Points:    ");

        //creating the text fields for the labels
        TextField textFieldWeek = new TextField();
        TextField textFieldDate = new TextField();
//        TextField textFieldActivity = new TextField();
        TextField textFieldPoints = new TextField();

        // ComboBox and loading in activites
        Scanner s = new Scanner(new File("Activities.txt"));    //file with activites to load in
        ArrayList<String> comboBoxItems = new ArrayList<String>();    //Creating arraylist to store Activities
        while (s.hasNext()) {
            comboBoxItems.add(s.next());
        }
        s.close();

        //making combobox for activities
        ComboBox comboBoxActivity = new ComboBox();
        for (int i = 0; i < comboBoxItems.size(); i++) {
            comboBoxActivity.getItems().add(comboBoxItems.get(i));
        }

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

//        table2.setItems(data);
//        table2.getColumns().addAll(weekCol, dateCol, activityCol, pointsCol);

        //Creating buttons

        //button for adding in new activities

        Button addButton = new Button("add");
        addButton.setOnAction(event -> {
            try {
                int newWeek = Integer.parseInt(textFieldWeek.getText()); //getting data out of text field and changing to int as needed
                int newDate = Integer.parseInt(textFieldDate.getText());
                String newActivity = comboBoxActivity.getValue().toString();
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
//            textFieldActivity.clear();
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

        Tab tab1 = new Tab("Introduction");
        Tab tab2 = new Tab("Activity Manager");
        Tab tab3 = new Tab("Results");
        Tab tab4 = new Tab("Add New Activities");

        //Tab1


//  Put tabs into their own methods




        TabPane tabPane1 = new TabPane();

        tabPane1.getTabs().add(tab1);
        tabPane1.getTabs().add(tab2);
        tabPane1.getTabs().add(tab3);
        tabPane1.getTabs().add(tab4);

        VBox vBoxTab1 = new VBox();
        Label labelTab1 = new Label("This is an introduction screen");
        Label labelTab2 = new Label("This app is designed to enable you to track your carbon emissions");

        vBoxTab1.getChildren().addAll(tabPane1, labelTab1, labelTab2);

        Scene tab1Scene = new Scene(vBoxTab1, 700, 700);

        //Tab2

        TabPane tabPane2 = new TabPane();

        tabPane2.getTabs().add(tab1);
        tabPane2.getTabs().add(tab2);
        tabPane2.getTabs().add(tab3);
        tabPane2.getTabs().add(tab4);

        HBox hBox1Tab2 = new HBox();
        hBox1Tab2.getChildren().addAll(labelWeek, textFieldWeek);
        HBox hBox2Tab2 = new HBox();
        hBox2Tab2.getChildren().addAll(labelDate, textFieldDate);
        HBox hBox3Tab2 = new HBox();
        hBox3Tab2.getChildren().addAll(labelActivity, comboBoxActivity);
        HBox hBox4Tab2 = new HBox();
        hBox4Tab2.getChildren().addAll(labelPoints, textFieldPoints);
        HBox hBox5Tab2 = new HBox();
        hBox5Tab2.getChildren().addAll(addButton, removeButton, listButton, summaryButton);
        HBox hBox6Tab2 = new HBox();
        hBox6Tab2.getChildren().addAll(table);
        HBox hBox7Tab2 = new HBox();
        hBox7Tab2.getChildren().addAll(loadButton, saveButton, exitButton);

        VBox vBoxTab2 = new VBox();
        vBoxTab2.getChildren().addAll(tabPane2, hBox1Tab2, hBox2Tab2, hBox3Tab2, hBox4Tab2, hBox5Tab2, hBox6Tab2, hBox7Tab2);
        Scene tab2Scene = new Scene(vBoxTab2, 700, 700);

        //tab3
        final ListView listView = new ListView();
        listView.setPrefSize(200, 250);
        listView.setEditable(true);
        SortedList<String> sortedList = new SortedList(data);
        Collections.sort(sortedList);
        listView.setItems(sortedList);

        TabPane tabPane3 = new TabPane();

        tabPane3.getTabs().add(tab1);
        tabPane3.getTabs().add(tab2);
        tabPane3.getTabs().add(tab3);
        tabPane3.getTabs().add(tab4);

        Label tab3test = new Label("List of Activities");

        VBox vBoxTab3 = new VBox();

        vBoxTab3.getChildren().addAll(tabPane3, tab3test, listView);

        Scene tab3Scene = new Scene(vBoxTab3, 700, 700);

//tab4
        TabPane tabPane4 = new TabPane();

        tabPane4.getTabs().add(tab1);
        tabPane4.getTabs().add(tab2);
        tabPane4.getTabs().add(tab3);
        tabPane4.getTabs().add(tab4);

        Label labelEnterNewActivity = new Label("Enter New Activity");
        TextField textFieldNewActivity = new TextField();

        Button buttonAddActivity = new Button("add");
        buttonAddActivity.setOnAction(event -> {

            comboBoxItems.add(textFieldNewActivity.getText().replace(" ", "_"));


            FileWriter writer = null;
            try {
                writer = new FileWriter("Activities.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (String str : comboBoxItems) {
                try {
                    writer.write(str + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            comboBoxActivity.getItems().add(textFieldNewActivity.getText().replace(" ", "_"));
            textFieldNewActivity.clear();
        });


        HBox hBox1Tab4 = new HBox();
        HBox hBox2Tab4 = new HBox();

        hBox1Tab4.getChildren().addAll(labelEnterNewActivity, textFieldNewActivity);
        hBox2Tab4.getChildren().addAll(buttonAddActivity);

        VBox vBoxTab4 = new VBox();

        vBoxTab4.getChildren().addAll(tabPane4, hBox1Tab4, hBox2Tab4);

        Scene tab4Scene = new Scene(vBoxTab4, 700, 700);

        //adding ability to change scenes to tabs
        tab1.setOnSelectionChanged(event -> {
            primaryStage.setScene(tab1Scene);
        });
        tab2.setOnSelectionChanged(event -> {
            primaryStage.setScene(tab2Scene);
        });
        tab3.setOnSelectionChanged(event -> {
            primaryStage.setScene(tab3Scene);
        });
        tab4.setOnSelectionChanged(event -> {
            primaryStage.setScene(tab4Scene);
        });

        //Displaying the Scene
        primaryStage.setScene(tab1Scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
