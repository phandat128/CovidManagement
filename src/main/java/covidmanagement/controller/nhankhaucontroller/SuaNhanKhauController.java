package covidmanagement.controller.nhankhaucontroller;

import covidmanagement.Utility;
import covidmanagement.database.QueryDB;
import covidmanagement.model.NhanKhauModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
        private TextField txtHoVaTen, txtMaNhanKhau, txtTonGiao, txtNguyenQuan, txtNgheNghiep, txtQuocTich, txtGioiTinh,
                txtSDT, txtQuanHeVoiChuHo, txtLaChuHo, txtCMND_CCCD, txtMaHoKhau;
        @FXML
        private DatePicker pickerNgaySinh;

        int MaNhanKhau;
        String HoTen;

        @Override
        public void initialize(URL location, ResourceBundle resources) {

        }

        public void setField(int maNhanKhau, String hoVaTen, String gioiTinh, LocalDate ngaySinh, String cmnd_CCCD_,
                             String quocTich, String tonGiao, String sDT, String nguyenQuan, String ngheNghiep,
                             int maHoKhau, Boolean laChuHo, String quanHeVoiChuHo){
                String chuho = "không";
                if(laChuHo == true) chuho = "có";
                this.MaNhanKhau = maNhanKhau;
                txtHoVaTen.setText(hoVaTen);
                txtGioiTinh.setText(gioiTinh);
                pickerNgaySinh.setValue(ngaySinh);
                txtCMND_CCCD.setText(cmnd_CCCD_);
                txtQuocTich.setText(quocTich);
                txtTonGiao.setText(tonGiao);
                txtSDT.setText(sDT);
                txtNguyenQuan.setText(nguyenQuan);
                txtNgheNghiep.setText(ngheNghiep);
                txtMaHoKhau.setText(String.valueOf(maHoKhau));
                txtLaChuHo.setText(chuho);
                txtQuanHeVoiChuHo.setText(quanHeVoiChuHo);
        }

        @FXML
        void suaActionevent(ActionEvent event) throws SQLException {
                if (txtMaNhanKhau.getText().isBlank()) {
                        RuntimeException maNhanKhauException = new RuntimeException("Trường Mã Nhân Khẩu không được để trống!");
                        Utility.displayExceptionDialog(maNhanKhauException);
                        throw maNhanKhauException;
                }

                if (txtHoVaTen.getText().isBlank()) {
                        RuntimeException hoVaTenException = new RuntimeException("Trường Họ Và Tên không được để trống!");
                        Utility.displayExceptionDialog(hoVaTenException);
                        throw hoVaTenException;
                }

                if (pickerNgaySinh.getValue() == null) {
                        RuntimeException ngaySinhException = new RuntimeException("Trường Ngày Sinh không được để trống!");
                        Utility.displayExceptionDialog(ngaySinhException);
                        throw ngaySinhException;
                }

                if (txtCMND_CCCD.getText().isBlank()) {
                        RuntimeException cmnd_CCCDException = new RuntimeException("Trường CMND_CCCD không được để trống!");
                        Utility.displayExceptionDialog(cmnd_CCCDException);
                        throw cmnd_CCCDException;
                }

                if (txtMaHoKhau.getText().isBlank()) {
                        RuntimeException maHoKhauException = new RuntimeException("Trường Mã Hộ Khẩu không được để trống!");
                        Utility.displayExceptionDialog(maHoKhauException);
                        throw maHoKhauException;
                }


                int maNhanKhau = Integer.parseInt(txtMaNhanKhau.getText());
                String hoVaTen = txtHoVaTen.getText();
                String gioiTinh = txtGioiTinh.getText();
                LocalDate ngaySinh = pickerNgaySinh.getValue();
                String cmnd_CCCD_ = txtCMND_CCCD.getText();
                String quocTich = txtQuocTich.getText();
                String tonGiao = txtTonGiao.getText();
                String sDT = txtSDT.getText();
                String nguyenQuan = txtNguyenQuan.getText();
                String ngheNghiep = txtNgheNghiep.getText();
                int maHoKhau = Integer.parseInt(txtMaHoKhau.getText());
                String quanHeVoiChuHo = txtQuanHeVoiChuHo.getText();
                // TODO
                Boolean laChuHo;
                if(txtLaChuHo.getText() == "có") laChuHo = true;
                else laChuHo = false;

                NhanKhauModel.updateNhanKhau(maNhanKhau, hoVaTen, gioiTinh, ngaySinh, cmnd_CCCD_, quocTich, tonGiao,
                        sDT, nguyenQuan, ngheNghiep, maHoKhau, laChuHo, quanHeVoiChuHo);





        }

        @FXML
        void dongActionevent(ActionEvent event) {
            Stage stage = (Stage) btnDong.getScene().getWindow();
            stage.close();
        }


}

