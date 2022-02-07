package com.example.w2022comp1011w2;
//main method
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/*Customized version of that class*/
public class Main extends Application {
    @Override
//    how we launch the application
    public void start(Stage stage) throws IOException {
//        change fxml loader object
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("create-camera-view.fxml"));
//        scene is what we put on stage
        Scene scene = new Scene(fxmlLoader.load());
//        stage is more like a window
        stage.setTitle("Create Camera");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        run application
        launch();

    }
}

