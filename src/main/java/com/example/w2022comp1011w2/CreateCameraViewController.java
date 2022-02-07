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
        //add all manufacturers
        brandComboBox.getItems().addAll(Camera.getManufacturers());

        //configure the spinner to only accept realistic camera resolutions.
        //Will use a spinner value factory.
        //this constructor takes minimum value.
        //the constructor is taking the minimum, maximum, default and step/increment
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(5,100,20,5);
        //5,100,20,5 v
        resolutionSpinner.setValueFactory(spinnerValueFactory);

        resolutionSpinner.setEditable(true);

//        grab the text field out of the spinner for us.
        TextField spinnerTextField = resolutionSpinner.getEditor();
        //?

//      Create a customer changeListener class wasn't efficient - extra files and didn't allow accessing JS objects that are private in the controller.
//        spinnerTextField.textProperty().addListener(new SpinnerChangeListener());
//        we can create an anonymous inner class.

//        spinnerTextField.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
//                msgLabel.setText("");
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
        spinnerTextField.textProperty().addListener((obs, oldValue, newValue) -> {
            try{
                //if integer it is fine.
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
            price =Double.parseDouble(priceTextField.getText());
        }catch(Exception e) {
            msgLabel.setText("Resolution must be a whole number");
        }
            if (res != -1 && price != -1) {

            try {
                Camera newCamera = new Camera(make, model, res, slr, price);
                //once get valid camera, works a lot like printing to a file. Writting to the files system
                Formatter formatter = new Formatter(new File("camera.txt"));
                formatter.format("new camera: %s\n",newCamera);
                formatter.close();
                //insert into db.
                DBUtility.insertCameraIntoDB(newCamera);
                msgLabel.setText(newCamera.toString());
                //visible again.
//            msgLabel.setVisible(true);
            }catch(IllegalArgumentException e)
            {
                msgLabel.setText(e.getMessage());
            }catch (Exception e)
            {
                msgLabel.setText("error writting to file "+e.getMessage());
            }
//                msgLabel.setText(newCamera.toString());
            }
    }
}
