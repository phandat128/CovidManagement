package covidmanagement.controller.xetnghiemcontroller;

import covidmanagement.Utility;
import covidmanagement.model.XetNghiemModel.KetQuaXetNghiem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ThemController implements Initializable {
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
        resultField.setValue(KetQuaXetNghiem.UNDEFINED);
        dateField.setValue(LocalDate.now());
    }

    public void onAdd(){
        //xử lý ngoại lệ trường hợp các trường thông tin cần thiết bị thiếu
        if (idNKField.getText().isBlank()){
            RuntimeException idNKException = new RuntimeException("Trường mã nhân khẩu không được để trống!");
            Utility.displayExceptionDialog(idNKException);
            throw idNKException;
        }
        if (nameField.getText().isBlank()){
            RuntimeException nameException = new RuntimeException("Trường họ tên không được để trống!");
            Utility.displayExceptionDialog(nameException);
            throw nameException;
        }
        if (placeField.getText().isBlank()){
            RuntimeException placeException = new RuntimeException("Trường địa điểm không được để trống!");
            Utility.displayExceptionDialog(placeException);
            throw placeException;
        }
        //xử lý ngoại lệ trường hợp trường mã nhân khẩu không phải là chữ số
        int idNK = 0;
        try {
            idNK = Integer.parseInt(idNKField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Utility.displayExceptionDialog(new NumberFormatException("Mã nhân khẩu chỉ được chứa chữ số!"));
            return;
        }
        //TODO here: xử lý ngoại lệ với cơ sở dữ liệu
        String name = nameField.getText();
        LocalDate date = dateField.getValue();
        String place = placeField.getText();
        KetQuaXetNghiem result = resultField.getValue();
        System.out.println(idNK == 0 ? null : idNK);
        System.out.println(name);
        System.out.println(date);
        System.out.println(place);
        System.out.println(result);

        //TODO with database
//        String sql = "INSERT INTO XetNghiem ()"
    }
}