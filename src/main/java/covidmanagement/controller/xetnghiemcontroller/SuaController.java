package covidmanagement.controller.xetnghiemcontroller;

import covidmanagement.model.XetNghiemModel;
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

public class SuaController implements Initializable {

    @FXML TextField idNKField, nameField, placeField;
    @FXML DatePicker dateField;
    @FXML ChoiceBox<XetNghiemModel.KetQuaXetNghiem> resultField;
    @FXML Button updateButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultField.setItems(FXCollections.observableArrayList(XetNghiemModel.KetQuaXetNghiem.values()));
        updateButton.setDisable(true);
        dateField.valueProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue == null) return;
            if (!currentValue.isEqual(newValue)) updateButton.setDisable(false);
        });
        placeField.textProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue.isBlank()) return;
            if (!currentValue.equals(newValue)) {
                updateButton.setDisable(false);
            }
        });
        resultField.valueProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue == null) return;
            if (!currentValue.equals(newValue)) updateButton.setDisable(false);
        });
    }

    public void setField(int idNK, String name, LocalDate date, String place, XetNghiemModel.KetQuaXetNghiem result){
        idNKField.setText(String.valueOf(idNK));
        nameField.setText(name);
        dateField.setValue(date);
        placeField.setText(place);
        resultField.setValue(result);
    }

    public void onUpdate(){
        //TODO

    }
}