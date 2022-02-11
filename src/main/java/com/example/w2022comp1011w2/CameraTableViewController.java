package com.example.w2022comp1011w2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CameraTableViewController implements Initializable {

    @FXML
    private TableColumn<Camera, Integer> cameraIDColumn;

    @FXML
    private TableColumn<Camera, String> makeColumn;

    @FXML
    private TableColumn<Camera, String> modelColumn;

    @FXML
    private TableColumn<Camera, Double> priceColumn;

    @FXML
    private TableColumn<Camera, Integer> resolutionColumn;

    @FXML
    private TableColumn<Camera, Boolean> slrColumn;

    @FXML
    private TableView<Camera> tableView;

    @FXML
    private TableColumn<Camera, Integer> unitsSoldColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Camera> cameras = DBUtility.getCamerasFromDB();
        System.out.println("");
    }
}
