package covidmanagement.controller.hokhaucontroller;

import covidmanagement.Utility;
import covidmanagement.model.HoKhauModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
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
        maHKField.setDisable(true);
        soNhaField.setText(soNha);
        ngachField.setText(ngach);
        ngoField.setText(ngo);
        duongField.setText(duong);
        phuongField.setText(phuong);
        quanField.setText(quan);
        thanhPhoField.setText(thanhPho);
        thanhPhoField.setDisable(true);
    }
    public void capnhat (ActionEvent event) throws SQLException {
        final String soNha = soNhaField.getText();
        final String ngach = ngachField.getText();
        final String ngo = ngoField.getText();
        final String duong = duongField.getText();
        final String phuong = phuongField.getText();
        final String quan = quanField.getText();
        final String ma= maHKField.getText();
        maHK = Integer.parseInt(ma);
        try {
            HoKhauModel.update(soNha, ngach, ngo, duong, phuong, quan,maHK);
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
//        HoKhauModel.update(soNha, ngach, ngo, duong, phuong, quan,maHK);
//        //TODO: đóng cửa sổ, thông báo thành công/thất bại, chuyển đến trang tìm kiếm
    }
}
