package com.example.w2022comp1011w2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

//implemenets initializable interface call initialize method
public class CreateCameraViewController implements Initializable {
    @FXML
    private ComboBox<String> brandComboBox;

    @FXML
    private TextField modelTextField;

    @FXML
    private Label msgLabel;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField resolutionTextField;

    @FXML
    private CheckBox slrCheckBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        msgLabel.setVisible(false);
        brandComboBox.getItems().addAll(Camera.getManufacturers());
    }

    @FXML
    private void createCamera() {
        String make = brandComboBox.getSelectionModel().getSelectedItem();
        String model = modelTextField.getText();
        int res = Integer.parseInt(resolutionTextField.getText());
        boolean slr = slrCheckBox.isSelected(); //if selected return true other false
        double price = Double.parseDouble(priceTextField.getText());

        Camera newCamera = new Camera(make,model,res,slr,price);
        //visible again.
        msgLabel.setVisible(true);
        msgLabel.setText(newCamera.toString());
    }
}
