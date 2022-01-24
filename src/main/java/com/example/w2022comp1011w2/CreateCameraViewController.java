package com.example.w2022comp1011w2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Formatter;
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
    private Spinner<Integer> resolutionSpinner;

    @FXML
    private CheckBox slrCheckBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        msgLabel.setVisible(false);
        msgLabel.setText("");
        brandComboBox.getItems().addAll(Camera.getManufacturers());

        //configure the spinner to only accept realistic camera resolutions.
        //Will use a spinner value factory.
        //this constructor takes minimum value.
        //the constructor is taking the minimum, maximum, default and step/increment
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5,100,20,5);
        //5,100,20,5 v
        resolutionSpinner.setValueFactory(spinnerValueFactory);
        TextField spinnerTextField = resolutionSpinner.getEditor();
        resolutionSpinner.setEditable(true);
//      Create a customer changeListener class wasn't efficient - extra files and didn't allow to access
//        JS objects private in the controller.
//        spinnerTextField.textProperty().addListener(new SpinnerChangeListener());
        //we can create an annoymous inner class.


//        spinnerTextField.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
//
//                try {
//                    Integer.parseInt(newValue);
//                    msgLabel.setText("");
//                }catch(Exception e)
//                {
//                    msgLabel.setText("Only whole numbers allowed for resolution");
//                    spinnerTextField.setText(oldValue);
//                }
////                msgLabel.setText(String.format("Old value: %s new value: %s",oldValue, newValue));
//            }
//        });

        //Let's use a lambda expression. ex1
        spinnerTextField.textProperty().addListener( (obs, oldValue, newValue) -> {
            try{
                Integer.parseInt(newValue);
            }
            catch(Exception e) {
                spinnerTextField.setText(oldValue);
            }
        });

        //update the price text field s.t. it will accept a double.
        priceTextField.textProperty().addListener((obs,oldValue,newValue)->{
            try{
                Double.parseDouble(newValue);
            }catch (Exception e) {
                priceTextField.setText(oldValue);
            }
        });
    }

    @FXML
    private void createCamera() {


            String make = brandComboBox.getSelectionModel().getSelectedItem();
            String model = modelTextField.getText();
            boolean slr = slrCheckBox.isSelected(); //if selected return true other false
            int res=-1;
            double price = -1;
            try {
            res = resolutionSpinner.getValue();
            price=Double.parseDouble(priceTextField.getText());
        }catch(Exception e) {
            msgLabel.setText("Resolution must be a whole number");
        }
            if (res != -1 && price != -1) {
                msgLabel.setText("Resolution numbers must numbers only");
            }
            try {
                Camera newCamera = new Camera(make, model, res, slr, price);
                Formatter formatter = new Formatter(new File("camera.txt"));
                formatter.format("new camera: %s\n",newCamera);
                formatter.close();
                //visible again.
//            msgLabel.setVisible(true);
            }catch(IllegalArgumentException e)
            {
                msgLabel.setText(e.getMessage());
            }catch (FileNotFoundException e)
            {
                msgLabel.setText("error writting to file "+e.getMessage());
            }
//                msgLabel.setText(newCamera.toString());

    }
}
