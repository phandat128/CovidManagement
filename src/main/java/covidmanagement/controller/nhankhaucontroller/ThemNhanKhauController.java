package covidmanagement.controller.nhankhaucontroller;

import covidmanagement.Utility;
import covidmanagement.database.QueryDB;
import covidmanagement.model.NhanKhauModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class ThemNhanKhauController implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnThem;

    @FXML
    private TextField txtMaNhanKhau, txtHoVaTen, txtCMND_CCCD, txtQuocTich, txtTonGiao, txtSDT, txtNguyenQuan, txtNgheNghiep, txtMaHoKhau, txtQuanHeVoiChuHo;

    @FXML
    private DatePicker pickerNgaySinh;

    @FXML
    private RadioButton lachuhoco, lachuhokhong, gioitinhnam, gioitinhnu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtMaNhanKhau.setDisable(true);

    }
    @FXML
    void addActionevent(ActionEvent event) throws SQLException {
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
        try{
            if(key) {
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
                String quanHeVoiChuHo;
                // TODO
                Boolean laChuHo;
                if (lachuhoco.isSelected()) {
                    laChuHo = true;
                    quanHeVoiChuHo = "Chủ hộ";

                } else {
                    laChuHo = false;
                    quanHeVoiChuHo = txtQuanHeVoiChuHo.getText();
                }

                NhanKhauModel.addNhanKhau(hoVaTen, gioiTinh, ngaySinh, cmnd_CCCD_, quocTich, tonGiao,
                        sDT, nguyenQuan, ngheNghiep, maHoKhau, laChuHo, quanHeVoiChuHo);
            }
        }
        catch (NumberFormatException e) {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setHeaderText("Lỗi khi thêm dữ liệu: " + e.getMessage() + ".\n" + "Mã nhân khẩu chỉ chứa chữ số. Vui lòng nhập lại!!");
                alert2.show();
            }



    }
    @FXML
    void resetActionevent(ActionEvent event) {

        txtMaNhanKhau.setText("");
        txtHoVaTen.setText("");
        pickerNgaySinh.setValue(null);

        gioitinhnam.setSelected(false);
        gioitinhnu.setSelected(false);

        txtCMND_CCCD.setText("");
        txtQuocTich.setText("");
        txtTonGiao.setText("");
        txtSDT.setText("");
        txtNguyenQuan.setText("");
        txtNgheNghiep.setText("");
        txtMaHoKhau.setText("");
        lachuhoco.setSelected(false);
        lachuhokhong.setSelected(false);
        txtQuanHeVoiChuHo.setText("");


    }
}




