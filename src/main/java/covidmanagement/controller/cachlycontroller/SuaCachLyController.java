package covidmanagement.controller.cachlycontroller;


import covidmanagement.Utility;
import covidmanagement.model.CachLyModel;
import covidmanagement.model.CachLyModel.MucDoCachLy;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

public class SuaCachLyController implements Initializable {

    @FXML
    TextField MaNKCl, nameCl, placeCl;
    @FXML
    DatePicker beginDateCl, finishDateCl;
    @FXML
    ChoiceBox<CachLyModel.MucDoCachLy> mucdoCl;
    @FXML
    Button updateButtonCl;
    int idCL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mucdoCl.setItems(FXCollections.observableArrayList(CachLyModel.MucDoCachLy.values()));
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
        mucdoCl.valueProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue == null) return;
            if (!currentValue.equals(newValue)) updateButtonCl.setDisable(false);
        });
        // set date format to DD/MM/yyyy
        beginDateCl.setConverter(new StringConverter<>() {
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
        finishDateCl.setConverter(new StringConverter<>() {
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

    public void setFieldCl(int idCL, int idNK, String name, LocalDate begindate, LocalDate finishdate, String place, MucDoCachLy mucdo ){
        this.idCL = idCL;
        MaNKCl.setText(String.valueOf(idNK));
        nameCl.setText(name);
        beginDateCl.setValue(begindate);
        finishDateCl.setValue(finishdate);
        placeCl.setText(place);
        mucdoCl.setValue(mucdo);
    }

    public void upDateCl(ActionEvent event){
        //TODO
        final LocalDate begindate = beginDateCl.getValue();
        final LocalDate finishdate = finishDateCl.getValue();
        if (begindate != null && finishdate != null){
            if (begindate.isAfter(finishdate)) {
                Utility.displayExceptionDialog(new RuntimeException("Khoảng thời gian không hợp lệ!"));
                return;
            }
        }
        final String place = placeCl.getText();
        final CachLyModel.MucDoCachLy mucdo = mucdoCl.getValue();

        try {
            CachLyModel.updateCl(idCL, begindate, finishdate, place, mucdo);
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
