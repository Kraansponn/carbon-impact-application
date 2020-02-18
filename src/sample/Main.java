package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setWidth(500);
        primaryStage.setHeight(300);

        VBox dataEntry = new VBox(20);
        Button add = new Button("add");
        Button remove = new Button("remove");
        Button list = new Button("list");
        Button summary = new Button("summary");
        Button load = new Button("load");
        Button save = new Button("save");
        Button exit = new Button("exit");
        dataEntry.getChildren().addAll(add,remove,list,summary,load,save,exit);
        primaryStage.setScene(new Scene(dataEntry));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
