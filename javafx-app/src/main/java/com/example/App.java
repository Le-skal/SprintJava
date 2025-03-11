package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        VBox root = FXMLLoader.load(getClass().getResource("/layouts/App.fxml"));

        // Retrieve the label and button from the FXML-loaded scene graph
        Label messageLabel = (Label) root.lookup("#messageLabel");
        Button clickButton = (Button) root.lookup("#clickButton");

        // Set up the event handler for the button
        clickButton.setOnAction(event -> {
            messageLabel.setText("🐼-WE LOVE PANDAS!!!!!-🐼");
        });

        // Create a scene with the root node
        Scene scene = new Scene(root, 300, 200);
        scene.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());

        // Configure the main window
        primaryStage.setTitle("Ma Première Application JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}