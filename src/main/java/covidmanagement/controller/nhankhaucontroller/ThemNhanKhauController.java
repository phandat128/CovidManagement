package covidmanagement.controller.nhankhaucontroller;

import covidmanagement.Utility;
import covidmanagement.database.QueryDB;
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
    private TextField txtMaNhanKhau, txtHoVaTen, txtGioiTinh, txtCMND_CCCD, txtQuocTich, txtTonGiao, txtSDT, txtNguyenQuan, txtNgheNghiep, txtMaHoKhau, txtLaChuHo, txtQuanHeVoiChuHo;

    @FXML
    private DatePicker pickerNgaySinh;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    void addActionevent(ActionEvent event) {

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


        try {

            QueryDB queryDB = new QueryDB();
            PreparedStatement preparedStatement = queryDB.getConnection().prepareStatement(
                    "INSERT INTO nhankhau(manhankhau, hoten, ngaysinh, gioitinh, cmnd_cccd, sdt, quoctich, tongiao, nguyenquan, mahokhau, lachuho, quanhevoichuho, nghenghiep) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)"
            );
            preparedStatement.setInt(1, maNhanKhau);
            preparedStatement.setString(2, hoVaTen);
            preparedStatement.setDate(3, Date.valueOf(ngaySinh));
            preparedStatement.setString(4, gioiTinh);
            preparedStatement.setString(5, cmnd_CCCD_);
            preparedStatement.setString(6, sDT);
            preparedStatement.setString(7, quocTich);
            preparedStatement.setString(8, tonGiao);
            preparedStatement.setString(9, nguyenQuan);
            preparedStatement.setInt(10, maHoKhau);
            preparedStatement.setBoolean(11, laChuHo);
            preparedStatement.setString(12, quanHeVoiChuHo);
            preparedStatement.setString(13, ngheNghiep);

            int result = preparedStatement.executeUpdate();
            preparedStatement.close();
            queryDB.close();
            if(result == 1){
                RuntimeException thanhCongException = new RuntimeException("Thêm nhân khẩu thành công");
                Utility.displayExceptionDialog(thanhCongException);
                throw thanhCongException;
            } else{
//                RuntimeException thatBaiException = new RuntimeException("Thêm nhân khẩu thất bại");
//                Utility.displayExceptionDialog(thatBaiException);
//                throw thatBaiException;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}




