package com.example.w2022comp1011w2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
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

    @FXML
    private Label highestRevenueLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cameraIDColumn.setCellValueFactory(new PropertyValueFactory<>("cameraID"));
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        resolutionColumn.setCellValueFactory(new PropertyValueFactory<>("resolution"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        slrColumn.setCellValueFactory(new PropertyValueFactory<>("slr"));
        unitsSoldColumn.setCellValueFactory(new PropertyValueFactory<>("unitsSold"));
//        ArrayList<Camera> cameras = DBUtility.getCamerasFromDB();
//        System.out.println("");
        tableView.getItems().addAll(DBUtility.getCamerasFromDB());
        highestRevenueLabel.setText("Highest Revenue = " + getHighestRevenue());
    }

    private String getHighestRevenue()
    {
        if (tableView.getItems().size() == 0)
            return "No cameras in the table";
        else
        {
            Camera highRev = tableView.getItems().get(0);
            System.out.println("get 0 is " + tableView.getItems().get(0));
//            System.out.println(tableView.getItems());
//            troubelshooting is important
            for (Camera camera : tableView.getItems())
            {
                double highestRevenue = highRev.getPrice() * highRev.getUnitsSold();
                double cameraRevenue = camera.getPrice() * camera.getUnitsSold();
                if (cameraRevenue > highestRevenue)
                    highRev = camera;
            }
                double highRevenue = highRev.getPrice() * highRev.getUnitsSold();
                System.out.println(tableView.getItems());
                return (String.format("$%.2f, %s", highRevenue, highRev));
            }
    }

    /***
     *  Change scenes to the chart view
     *
     */
    @FXML
    private void loadChartData(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event,"camera-chart-view.fxml");
    }

}
