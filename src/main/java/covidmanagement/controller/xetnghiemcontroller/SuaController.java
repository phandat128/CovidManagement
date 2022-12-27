package covidmanagement.controller.xetnghiemcontroller;

import covidmanagement.model.XetNghiemModel;
import covidmanagement.model.XetNghiemModel.KetQuaXetNghiem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SuaController implements Initializable {

    @FXML TextField idNKField, nameField, placeField;
    @FXML DatePicker dateField;
    @FXML ChoiceBox<KetQuaXetNghiem> resultField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultField.setItems(FXCollections.observableArrayList(KetQuaXetNghiem.values()));
    }

    public void setField(int idNK, String name, LocalDate date, String place, KetQuaXetNghiem result){
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
