package covidmanagement.controller.hokhaucontroller;

import covidmanagement.model.HoKhauModel;
import covidmanagement.model.XetNghiemModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SuaHoKhaucontroller implements Initializable {
    @FXML
    TextField maHKField;
    @FXML
    TextField soNhaField;
    @FXML
    TextField ngachField;
    @FXML
    TextField ngoField;
    @FXML
    TextField duongField;
    @FXML
    TextField phuongField;
    @FXML
    TextField quanField;
    @FXML
    TextField thanhPhoField;
    @FXML
    Button capNhat;
    int maHK;
    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        capNhat.setDisable(true);
        soNhaField.textProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue.isBlank()) return;
            if (!currentValue.equals(newValue)) {
                capNhat.setDisable(false);
            }
        });
        ngachField.textProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue.isBlank()) return;
            if (!currentValue.equals(newValue)) {
                capNhat.setDisable(false);
            }
        });
        ngoField.textProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue.isBlank()) return;
            if (!currentValue.equals(newValue)) {
                capNhat.setDisable(false);
            }
        });
        duongField.textProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue.isBlank()) return;
            if (!currentValue.equals(newValue)) {
                capNhat.setDisable(false);
            }
        });
        phuongField.textProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue.isBlank()) return;
            if (!currentValue.equals(newValue)) {
                capNhat.setDisable(false);
            }
        });
        quanField.textProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue.isBlank()) return;
            if (!currentValue.equals(newValue)) {
                capNhat.setDisable(false);
            }
        });
        thanhPhoField.textProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue.isBlank()) return;
            if (!currentValue.equals(newValue)) {
                capNhat.setDisable(false);
            }
        });
    }

    public  void setField( int maHK, String soNha, String ngach, String ngo, String duong, String phuong, String quan, String thanhPho) {
        maHKField.setText(String.valueOf(maHK));
        soNhaField.setText(soNha);
        ngachField.setText(ngach);
        ngoField.setText(ngo);
        duongField.setText(duong);
        phuongField.setText(phuong);
        quanField.setText(quan);
        thanhPhoField.setText(thanhPho);
    }
    public void capnhat () throws SQLException {
        final String soNha = soNhaField.getText();
        final String ngach = ngachField.getText();
        final String ngo = ngoField.getText();
        final String duong = duongField.getText();
        final String phuong = phuongField.getText();
        final String quan = quanField.getText();
        final String thanhPho = thanhPhoField.getText();
        HoKhauModel.update(soNha, ngach, ngo, duong, phuong, quan, thanhPho);
        //TODO: đóng cửa sổ, thông báo thành công/thất bại, chuyển đến trang tìm kiếm
    }
}
