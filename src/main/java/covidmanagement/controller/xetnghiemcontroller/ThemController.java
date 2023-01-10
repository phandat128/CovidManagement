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
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        int idNK;
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