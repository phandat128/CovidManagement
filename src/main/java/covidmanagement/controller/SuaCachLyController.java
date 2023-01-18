package covidmanagement.controller;


import covidmanagement.Utility;
import covidmanagement.model.CachLyModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
    Button updateButtonCl;
    int idCl;

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

    public void setFieldCl(int idCl, int idNK, String name, LocalDate begindate, LocalDate finishdate, String place ){
        this.idCl = idCl;
        MaNKCl.setText(String.valueOf(idNK));
        nameCl.setText(name);
        beginDateCl.setValue(begindate);
        finishDateCl.setValue(finishdate);
        placeCl.setText(place);
    }

    public void upDateCl(ActionEvent event){
        //TODO
        final LocalDate begindate = beginDateCl.getValue();
        final LocalDate finishdate = finishDateCl.getValue();
        final String place = placeCl.getText();
        try {
            CachLyModel.updateCl(idCl, begindate, finishdate, place);
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
