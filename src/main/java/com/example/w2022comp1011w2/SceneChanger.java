package com.example.w2022comp1011w2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class SceneChanger {
    /**
     * This method will change to the new scene pased into the method as an argument
     */
    public static void changeScenes(ActionEvent event, String fxmlFileName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFileName));
//        scene is what we put on stage
        Scene scene = new Scene(fxmlLoader.load());
//        stage is more like a window
//        derive the stage object from the action event.
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
