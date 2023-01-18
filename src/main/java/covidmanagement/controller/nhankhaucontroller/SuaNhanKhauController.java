package covidmanagement.controller.nhankhaucontroller;

import covidmanagement.Utility;
import covidmanagement.database.QueryDB;
import covidmanagement.model.NhanKhauModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.zone.ZoneRulesProvider;
import java.util.ResourceBundle;

public class SuaNhanKhauController implements Initializable  {
        @FXML
        private Button btnCapNhat;
        @FXML
        private Button btnDong;

        @FXML
        private TextField txtHoVaTen, txtMaNhanKhau, txtTonGiao, txtNguyenQuan, txtNgheNghiep, txtQuocTich,
                txtSDT, txtQuanHeVoiChuHo, txtCMND_CCCD, txtMaHoKhau;
        @FXML
        private DatePicker pickerNgaySinh;

        @FXML
        private RadioButton lachuhoco, lachuhokhong, gioitinhnam, gioitinhnu;

        @Override
        public void initialize(URL location, ResourceBundle resources) {

        }

        public void setField(int maNhanKhau, String hoVaTen, String gioiTinh, LocalDate ngaySinh, String cmnd_CCCD_,
                             String quocTich, String tonGiao, String sDT, String nguyenQuan, String ngheNghiep,
                             int maHoKhau, Boolean laChuHo, String quanHeVoiChuHo){

                txtMaNhanKhau.setText(String.valueOf(maNhanKhau));
                txtMaNhanKhau.setDisable(true);
                txtHoVaTen.setText(hoVaTen);

                if (gioiTinh.equalsIgnoreCase("Nam")) gioitinhnam.setSelected(true);
                if (gioiTinh.equalsIgnoreCase("Nữ"))gioitinhnu.setSelected(true);

                pickerNgaySinh.setValue(ngaySinh);
                txtCMND_CCCD.setText(cmnd_CCCD_);
                txtQuocTich.setText(quocTich);
                txtTonGiao.setText(tonGiao);
                txtSDT.setText(sDT);
                txtNguyenQuan.setText(nguyenQuan);
                txtNgheNghiep.setText(ngheNghiep);
                txtMaHoKhau.setText(String.valueOf(maHoKhau));

                if (laChuHo) lachuhoco.setSelected(true);
                else lachuhokhong.setSelected(true);

                txtQuanHeVoiChuHo.setText(quanHeVoiChuHo);
        }

        @FXML
        void suaActionevent(ActionEvent event) throws SQLException {
                Boolean key = true;
                if (txtHoVaTen.getText().isBlank()) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Trường Họ Và Tên không được để trống!");
                        alert.show();
                        key = false;
                }

                if (pickerNgaySinh.getValue() == null) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Trường Ngày Sinh không được để trống!");
                        alert.show();
                        key = false;
                }

                if (!pickerNgaySinh.getValue().isBefore(LocalDate.now())) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Đồng chí từ tương lai tới à!!");
                        alert.show();
                        key = false;
                }

                if (!gioitinhnam.isSelected() && !gioitinhnu.isSelected()){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Trường Giới Tính không được để trống!");
                        alert.show();
                        key = false;
                }

                if (txtCMND_CCCD.getText().isBlank()) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Trường CMND_CCCD không được để trống!");
                        alert.show();
                        key = false;
                }

                if (!txtCMND_CCCD.getText().matches("[0-9]+")){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Trường CMND_CCCD chỉ được chứa chữ số!");
                        alert.show();
                        key = false;
                }

                if (txtMaHoKhau.getText().isBlank()) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Trường Mã Hộ Khẩu không được để trống!");
                        alert.show();
                        key = false;
                }
                if (lachuhokhong.isSelected() && txtQuanHeVoiChuHo.getText().equalsIgnoreCase("Chủ hộ")) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Vui lòng chọn lại trường quan hệ với chủ hộ!!");
                        alert.show();
                        key = false;
                }
                try {
                        if (key) {
                                int maNhanKhau = Integer.parseInt(txtMaNhanKhau.getText());
                                String hoVaTen = txtHoVaTen.getText();

                                String gioiTinh = null;
                                if (gioitinhnam.isSelected()) gioiTinh = "Nam";
                                if (gioitinhnu.isSelected()) gioiTinh = "Nữ";

                                LocalDate ngaySinh = pickerNgaySinh.getValue();
                                String cmnd_CCCD_ = txtCMND_CCCD.getText();
                                String quocTich = txtQuocTich.getText();
                                String tonGiao = txtTonGiao.getText();
                                String sDT = txtSDT.getText();
                                String nguyenQuan = txtNguyenQuan.getText();
                                String ngheNghiep = txtNgheNghiep.getText();
                                int maHoKhau = Integer.parseInt(txtMaHoKhau.getText());
                                // TODO
                                String quanHeVoiChuHo;
                                Boolean laChuHo;
                                if (lachuhoco.isSelected()) {
                                        laChuHo = true;
                                        quanHeVoiChuHo = "Chủ hộ";

                                } else {
                                        laChuHo = false;
                                        quanHeVoiChuHo = txtQuanHeVoiChuHo.getText();
                                }

                                NhanKhauModel.updateNhanKhau(maNhanKhau, hoVaTen, gioiTinh, ngaySinh, cmnd_CCCD_, quocTich, tonGiao,
                                        sDT, nguyenQuan, ngheNghiep, maHoKhau, laChuHo, quanHeVoiChuHo);

                        }
                }catch (NumberFormatException e) {
                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
                        alert2.setHeaderText("Lỗi khi thêm dữ liệu: " + e.getMessage() + ".\n" + "Mã nhân khẩu chỉ chứa chữ số. Vui lòng nhập lại!!");
                        alert2.show();
                }

        }

        @FXML
        void dongActionevent(ActionEvent event) {
            Stage stage = (Stage) btnDong.getScene().getWindow();
            stage.close();
        }


}

