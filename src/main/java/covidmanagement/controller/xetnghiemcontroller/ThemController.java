package covidmanagement.controller.xetnghiemcontroller;

import covidmanagement.model.XetNghiemModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ThemController implements Initializable {
    @FXML
    TextField idNKField, nameField, placeField;
    @FXML
    DatePicker dateField;
    @FXML
    ChoiceBox<XetNghiemModel.KetQuaXetNghiem> resultField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultField.setItems(FXCollections.observableArrayList(XetNghiemModel.KetQuaXetNghiem.values()));


    }

    public void onAdd(){
        //TODO
    }
}
