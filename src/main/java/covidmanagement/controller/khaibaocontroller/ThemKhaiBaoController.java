package covidmanagement.controller.khaibaocontroller;

import covidmanagement.Main;
import covidmanagement.Utility;
import covidmanagement.model.KhaiBaoModel;
import covidmanagement.model.XetNghiemModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ThemKhaiBaoController implements Initializable {
    @FXML
    TextField declareSpotField, nameField, traceField, benhNenField;
    @FXML
    RadioButton BHYTYes, BHYTNo, symptomYes, symptomNo, covidContactYes, covidContactNo, countryContactYes, countryContactNo, symptomContactYes, symptomContactNo;
    @FXML
    DatePicker declareDate;
    @FXML
    Button saveDeclare;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        declareDate.setValue(LocalDate.now());
    }

    public void onAdd() throws SQLException {
        //xử lý ngoại lệ trường hợp các trường thông tin cần thiết bị thiếu
        if (nameField.getText().isBlank()){
            RuntimeException idNKException = new RuntimeException("Trường mã nhân khẩu không được để trống!");
            Utility.displayExceptionDialog(idNKException);
            throw idNKException;
        }
        if (declareSpotField.getText().isBlank()){
            RuntimeException placeException = new RuntimeException("Trường địa điểm không được để trống!");
            Utility.displayExceptionDialog(placeException);
            throw placeException;
        }
        //xử lý ngoại lệ của trường mã nhân khẩu
        try {
            Integer.parseInt(nameField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Utility.displayExceptionDialog(new NumberFormatException("Mã nhân khẩu chỉ được chứa chữ số!"));
            return;
        }
        int idNK = Integer.parseInt(nameField.getText());
        if (idNK <= 0) {
            RuntimeException notPositiveException = new RuntimeException("Mã nhân khẩu phải là số dương");
            Utility.displayExceptionDialog(notPositiveException);
            throw notPositiveException;
        }
        LocalDate date = declareDate.getValue();
        String place = declareSpotField.getText();
        String lichtrinh = traceField.getText();
        String benhNen = benhNenField.getText();

        //TODO here: xử lý ngoại lệ với cơ sở dữ liệu: xử lý trường hợp tên nhập vào không tương ứng với mã nhân khẩu

        //TODO with database
        KhaiBaoModel.add(idNK,  place, date, BHYTYes.isSelected(), lichtrinh, symptomYes.isSelected(),
                covidContactYes.isSelected(), countryContactYes.isSelected(), symptomContactYes.isSelected(), benhNen);
    }
}
