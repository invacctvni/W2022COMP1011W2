package com.example.w2022comp1011w2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

//if want the scene loads, must implementing intializable
public class CameraChartViewController implements Initializable {

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;

//    private XYChart.Series<String,Integer> unitsSold;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        returns observable list
// drawbacks: animation has some bugs.
        barChart.getData().addAll(DBUtility.getUnitsSold());

        //create fake data to show how to get another data series.
        XYChart.Series<String,Integer> unitsSold = new XYChart.Series<>();
        unitsSold.getData().addAll(new XYChart.Data<>("Sony-Alpha 6000",150));
        unitsSold.getData().addAll(new XYChart.Data<>("fake 2",200));
        unitsSold.getData().addAll(new XYChart.Data<>("fake 3",200));
        unitsSold.getData().addAll(new XYChart.Data<>("fake 4",290));

        barChart.getData().addAll(unitsSold);
    }

}
