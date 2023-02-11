package covidmanagement.controller.nhankhaucontroller;

import covidmanagement.Main;
import covidmanagement.Utility;
import covidmanagement.controller.hokhaucontroller.ThemHoKhauController;
import covidmanagement.model.HoKhauModel;
import covidmanagement.model.NhanKhauModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
    private TextField txtMaNhanKhau, txtHoVaTen, txtCMND_CCCD, txtQuocTich, txtTonGiao, txtSDT, txtNguyenQuan, txtNgheNghiep, txtMaHoKhau, txtQuanHeVoiChuHo, txtGhiChu;

    @FXML
    private DatePicker pickerNgaySinh;

    @FXML
    private RadioButton lachuho, khonglachuho, gioitinhnam, gioitinhnu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtMaNhanKhau.setDisable(true);
        pickerNgaySinh.setConverter(Utility.LOCAL_DATE_CONVERTER);
    }
    @FXML
    void addActionevent(ActionEvent event) throws SQLException {
        if (txtHoVaTen.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Trường Họ Và Tên không được để trống!");
            alert.show();
            return;
        }else if (pickerNgaySinh.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Trường Ngày Sinh không được để trống!");
            alert.show();
            return;
        }else if (pickerNgaySinh.getValue().isAfter(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Vui lòng nhập lại ngày sinh!");
            alert.show();
            return;
        }else if (!gioitinhnam.isSelected() && !gioitinhnu.isSelected()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Trường Giới Tính không được để trống!");
            alert.show();
            return;
        }else if (!txtCMND_CCCD.getText().isBlank() && !txtCMND_CCCD.getText().matches("[0-9]+")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Trường CMND_CCCD chỉ được chứa chữ số!");
            alert.show();
            return;
        }else if (txtQuocTich.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Trường Quốc Tịch không được để trống!");
            alert.show();
            return;
        }else if (!txtSDT.getText().isBlank()) {
            if (!txtSDT.getText().matches("[0-9]+")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Trường Số Điện Thoại chỉ được chứa chữ số!");
                alert.show();
                return;
            }
        }else if (txtMaHoKhau.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Trường Mã Hộ Khẩu không được để trống!");
            alert.show();
            return;
        }else if (!txtMaHoKhau.getText().matches("[0-9]+")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Trường Mã Hộ Khẩu chỉ được chứa chữ số!");
            alert.show();
            return;
        }
        if (khonglachuho.isSelected() && txtQuanHeVoiChuHo.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Vui lòng nhập trường Quan Hệ Với Chủ Hộ!");
            alert.show();
            return;
        }
        if (khonglachuho.isSelected() && txtQuanHeVoiChuHo.getText().equalsIgnoreCase("Chủ hộ")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Vui lòng chọn lại trường quan hệ với chủ hộ!!");
            alert.show();
            return;
        }
        if (lachuho.isSelected() && !txtQuanHeVoiChuHo.getText().isBlank()){
            if (!txtQuanHeVoiChuHo.getText().equalsIgnoreCase("Chủ hộ")){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Vui lòng điền lại trường quan hệ với chủ hộ!!");
                alert.show();
                return;
            }
        }

                String hoVaTen = txtHoVaTen.getText();

                String gioiTinh = null;
                if (gioitinhnam.isSelected()) gioiTinh = "Nam";
                if (gioitinhnu.isSelected()) gioiTinh = "Nữ";

                LocalDate ngaySinh = pickerNgaySinh.getValue();
                String cmnd_CCCD_ = txtCMND_CCCD.getText();
                String quocTich = txtQuocTich.getText();
                String tonGiao = txtTonGiao.getText().isBlank() ? "Không" : txtTonGiao.getText();
                String sDT = txtSDT.getText();
                String nguyenQuan = txtNguyenQuan.getText();
                String ngheNghiep = txtNgheNghiep.getText();
                String ghiChu = txtGhiChu.getText();
                try{
                    Integer.parseInt(txtMaHoKhau.getText());
                }catch(NumberFormatException e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Lỗi: " + e.getMessage() + ".\n" + "Mã nhân khẩu chỉ chứa chữ số. Vui lòng nhập lại!!");
                    alert.show();
                }
                int maHoKhau = Integer.parseInt(txtMaHoKhau.getText());
                String quanHeVoiChuHo;

                // TODO
                boolean laChuHo;
                if (lachuho.isSelected()) {
                    laChuHo = true;
                    quanHeVoiChuHo = "Chủ hộ";

                } else {
                    laChuHo = false;
                    quanHeVoiChuHo = txtQuanHeVoiChuHo.getText();
                }

                if (!HoKhauModel.isExistedHoKhau(maHoKhau)){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText("Mã hộ khẩu chưa tồn tại trong dữ liệu. Bạn có muốn tạo mới?");
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.OK){
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hokhau/themhokhau-view.fxml"));
                            Scene scene = new Scene(fxmlLoader.load());
                            Stage stage = new Stage();
                            ThemHoKhauController controller = fxmlLoader.getController();
                            controller.setMaHoKhau(maHoKhau);
                            stage.setTitle("Thêm hộ khẩu mới");
                            stage.setScene(scene);
                            stage.showAndWait();
                        } catch (IOException e){
                            e.printStackTrace();
                            return;
                        }
                    } else {
                        return;
                    }
                }
                NhanKhauModel.addNhanKhau(hoVaTen, gioiTinh, ngaySinh, cmnd_CCCD_, quocTich, tonGiao,
                        sDT, nguyenQuan, ngheNghiep, maHoKhau, laChuHo, quanHeVoiChuHo, ghiChu);
            }

    @FXML
    void resetActionevent(ActionEvent event) {

        txtMaNhanKhau.setText("");
        txtHoVaTen.setText("");
        pickerNgaySinh.setValue(null);

        gioitinhnam.setSelected(true);
        gioitinhnu.setSelected(false);

        txtCMND_CCCD.setText("");
        txtQuocTich.setText("");
        txtTonGiao.setText("");
        txtSDT.setText("");
        txtNguyenQuan.setText("");
        txtNgheNghiep.setText("");
        txtMaHoKhau.setText("");
        lachuho.setSelected(true);
        khonglachuho.setSelected(false);
        txtQuanHeVoiChuHo.setText("");
        txtGhiChu.setText("");

    }
}




