package covidmanagement.controller.nhankhaucontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SuaController implements Initializable  {
        @FXML
        private Button btnSua;
        @FXML
        private Button btnDong;

        @FXML
        private TextField txtHoVaTen;

        @FXML
        private TextField txtQuocTich;

        @FXML
        private TextField txtGioiTinh;

        @FXML
        private TextField txtSDT;

        @FXML
        private TextField txtQuanHeVoiChuHo;

        @FXML
        private TextField txtLaChuHo;

        @FXML
        private TextField txtCMND_CCCD;

        @FXML
        private TextField txtMaHoKhau;

        @FXML
        private DatePicker pickerNgaySinh;

        @FXML
        private TextField txtMaNhanKhau;

        @FXML
        private TextField txtTonGiao;

        @FXML
        private TextField txtNguyenQuan;

        @FXML
        private TextField txtNgheNghiep;

        @Override
        public void initialize(URL location, ResourceBundle resources) {

        }

        @FXML
        void suaActionevent(ActionEvent event) {

        }

        @FXML
        void dongActionevent(ActionEvent event) {
            Stage stage = (Stage) btnDong.getScene().getWindow();
            stage.close();
        }


}

