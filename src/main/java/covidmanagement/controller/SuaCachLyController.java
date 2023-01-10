package covidmanagement.controller;


import covidmanagement.model.CachLyModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;


import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SuaCachLyController implements Initializable {

    @FXML
    TextField MaNKCl, nameCl, placeCl;
    @FXML
    DatePicker beginDateCl, finishDateCl;
    @FXML
    Button updateButtonCl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateButtonCl.setDisable(true);
        beginDateCl.valueProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue == null) return;
            if (!currentValue.isEqual(newValue)) updateButtonCl.setDisable(false);
        });
        finishDateCl.valueProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue == null) return;
            if (!currentValue.isEqual(newValue)) updateButtonCl.setDisable(false);
        });
        placeCl.textProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue.isBlank()) return;
            if (!currentValue.equals(newValue)) {
                updateButtonCl.setDisable(false);
            }
        });
    }
    public void setFieldCl(int idNK, String name, LocalDate begindate, LocalDate finishdate, String place ){
        MaNKCl.setText(String.valueOf(idNK));
        nameCl.setText(name);
        beginDateCl.setValue(begindate);
        finishDateCl.setValue(finishdate);
        placeCl.setText(place);
    }

    public void upDateCl(){
        //TODO

    }

}
