package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setWidth(500);
        primaryStage.setHeight(300);

        Label labelWeek = new Label("Week: ");
        Label labelDate = new Label("Date: ");
        Label labelActivity = new Label("Activity: ");
        Label labelPoints = new Label("Points: ");

        TextField textFieldWeek = new TextField();
        TextField textFieldDate = new TextField();
        TextField textFielActivity = new TextField();
        TextField textFieldPoints = new TextField();

        GridPane gridPane = new GridPane();

        gridPane.addRow(0, labelWeek,textFieldWeek);
        gridPane.addRow(1, labelDate,textFieldDate);
        gridPane.addRow(2, labelActivity,textFielActivity);
        gridPane.addRow(3, labelPoints,textFieldPoints);

        Button add = new Button("add");
        Button remove = new Button("remove");
        Button list = new Button("list");
        Button summary = new Button("summary");
        Button load = new Button("load");
        Button save = new Button("save");
        Button exit = new Button("exit");

        gridPane.addRow(4, add,remove,list,summary);
        gridPane.addRow(5, load,save,exit);

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
