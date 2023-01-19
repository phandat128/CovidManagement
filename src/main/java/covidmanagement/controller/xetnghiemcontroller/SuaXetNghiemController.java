package covidmanagement.controller.xetnghiemcontroller;

import covidmanagement.Utility;
import covidmanagement.model.XetNghiemModel;
import covidmanagement.model.XetNghiemModel.KetQuaXetNghiem;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SuaXetNghiemController implements Initializable {

    @FXML TextField idNKField, nameField, placeField;
    @FXML DatePicker dateField;
    @FXML ChoiceBox<KetQuaXetNghiem> resultField;
    @FXML Button updateButton;

    int idXN;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultField.setItems(FXCollections.observableArrayList(KetQuaXetNghiem.values()));
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
        // set date format to DD/MM/yyyy
        dateField.setConverter(new StringConverter<>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null) return "";
                return formatter.format(localDate);
            }
            @Override
            public LocalDate fromString(String s) {
                if (s.isBlank()) return null;
                return LocalDate.parse(s, formatter);
            }
        });
    }

    public void setField(int idXN, int idNK, String name, LocalDate date, String place, KetQuaXetNghiem result){
        this.idXN = idXN;
        idNKField.setText(String.valueOf(idNK));
        nameField.setText(name);
        dateField.setValue(date);
        placeField.setText(place);
        resultField.setValue(result);
    }

    public void onUpdate(ActionEvent event){
        final LocalDate date = dateField.getValue();
        final String place = placeField.getText();
        final KetQuaXetNghiem result = resultField.getValue();
        try {
            XetNghiemModel.update(idXN, date, place, result);
            //TODO: thông báo thành công
            Utility.displaySuccessDialog("Cập nhật thành công!");
        } catch (SQLException e){
            e.printStackTrace();
            //TODO: thông báo thất bại
            Utility.displayExceptionDialog(e);
        }
        //TODO: tắt cửa sổ cập nhật
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }


}