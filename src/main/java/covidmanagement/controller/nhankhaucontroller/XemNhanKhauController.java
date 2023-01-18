package covidmanagement.controller.nhankhaucontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class XemNhanKhauController implements Initializable {

    @FXML
    private Button btnDong;

    @FXML
    private TextField txtHoVaTen, txtQuocTich, txtGioiTinh, txtSDT, txtQuanHeVoiChuHo, txtCMND_CCCD, txtMaHoKhau,
            txtMaNhanKhau, txtNguyenQuan, txtNgheNghiep, txtTonGiao;

    @FXML
    private DatePicker pickerNgaySinh;

    @FXML
    private RadioButton lachuhoco, lachuhokhong;

    @FXML
    private AnchorPane xemnhankhauView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setField(int maNhanKhau, String hoVaTen, String gioiTinh, LocalDate ngaySinh, String cmnd_CCCD_,
                         String quocTich, String tonGiao, String sDT, String nguyenQuan, String ngheNghiep,
                         int maHoKhau, Boolean laChuHo, String quanHeVoiChuHo){

        txtMaNhanKhau.setText(String.valueOf(maNhanKhau));
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

        if (laChuHo) lachuhoco.setSelected(true);
        else lachuhokhong.setSelected(true);

        txtQuanHeVoiChuHo.setText(quanHeVoiChuHo);
    }

    @FXML
    void dongActionevent(ActionEvent event) {
        Stage stage = (Stage) btnDong.getScene().getWindow();
        stage.close();
    }
}