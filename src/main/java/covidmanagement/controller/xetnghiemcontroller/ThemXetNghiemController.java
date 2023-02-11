package covidmanagement.controller.xetnghiemcontroller;

import covidmanagement.Utility;
import covidmanagement.model.XetNghiemModel;
import covidmanagement.model.XetNghiemModel.KetQuaXetNghiem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ThemXetNghiemController implements Initializable {
    @FXML
    TextField idNKField, nameField, placeField;
    @FXML
    DatePicker dateField;
    @FXML
    ChoiceBox<KetQuaXetNghiem> resultField;
    @FXML
    Button addButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultField.setItems(FXCollections.observableArrayList(KetQuaXetNghiem.values()));
        resultField.setValue(KetQuaXetNghiem.CHƯA_XÁC_ĐỊNH);
        dateField.setValue(LocalDate.now());
        // set date format to DD/MM/yyyy
        dateField.setConverter(Utility.LOCAL_DATE_CONVERTER);
    }

    public void onAdd() {
        //xử lý ngoại lệ trường hợp các trường thông tin cần thiết bị thiếu
        if (idNKField.getText().isBlank()){
            Utility.displayWarningDialog("Trường mã nhân khẩu không được để trống");
        }
        if (nameField.getText().isBlank()){
            Utility.displayWarningDialog("Trường họ tên không được để trống");
        }
        if (placeField.getText().isBlank()){
            Utility.displayWarningDialog("Trường mã địa điểm không được để trống");
        }
        //xử lý ngoại lệ của trường mã nhân khẩu
        try {
            Integer.parseInt(idNKField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Utility.displayWarningDialog("Mã nhân khẩu chỉ được chứa chữ số");
            return;
        }
        int idNK = Integer.parseInt(idNKField.getText());
        if (idNK <= 0) {
            Utility.displayWarningDialog("Mã nhân khẩu không phải số dương");
            return;
        }
        String name = nameField.getText();
        LocalDate date = dateField.getValue();
        String place = placeField.getText();
        KetQuaXetNghiem result = resultField.getValue();

        //TODO with database
        try {
            XetNghiemModel.add(idNK, name, date, place, result);
            //TODO: thông báo thêm thành công
            Utility.displaySuccessDialog("Thêm thành công!");
        } catch (SQLException e){
            e.printStackTrace();
            //TODO: thông báo lỗi không thêm được
            Utility.displayExceptionDialog(e);
        }
        //TODO: chuyển đến trang
    }
}