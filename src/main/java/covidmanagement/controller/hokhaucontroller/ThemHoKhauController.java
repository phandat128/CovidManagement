package covidmanagement.controller.hokhaucontroller;

import covidmanagement.Utility;
import covidmanagement.model.HoKhauModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.SQLException;

public class ThemHoKhauController {
    @FXML
    TextField maHoKhau, soNha, ngach, ngo, duong, phuong, quan, thanhPho;
    @FXML
    Button them;

    public void xuLy() throws SQLException {
    //xử lý ngoại lệ trường hợp các trường thông tin cần thiết bị thiếu
        if (maHoKhau.getText().isBlank()){
            RuntimeException idNKException = new RuntimeException("Trường mã hộ khẩu không được để trống!");
            Utility.displayExceptionDialog(idNKException);
            throw idNKException;
        }
        if (soNha.getText().isBlank()){
            RuntimeException idNKException = new RuntimeException("Trường số nhà không được để trống!");
            Utility.displayExceptionDialog(idNKException);
            throw idNKException;
        }
        if (duong.getText().isBlank()){
            RuntimeException idNKException = new RuntimeException("Trường đường không được để trống!");
            Utility.displayExceptionDialog(idNKException);
            throw idNKException;
        } if (phuong.getText().isBlank()){
            RuntimeException idNKException = new RuntimeException("Trường phường không được để trống!");
            Utility.displayExceptionDialog(idNKException);
            throw idNKException;
        } if (quan.getText().isBlank()){
            RuntimeException idNKException = new RuntimeException("Trường quận không được để trống!");
            Utility.displayExceptionDialog(idNKException);
            throw idNKException;
        } if (thanhPho.getText().isBlank()){
            RuntimeException idNKException = new RuntimeException("Trường thành phố không được để trống!");
            Utility.displayExceptionDialog(idNKException);
            throw idNKException;
        }

        //xử lý ngoại lệ trường hợp trường mã nhân khẩu không phải là chữ số
        int idHK = 0;
        try {
            idHK = Integer.parseInt(maHoKhau.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Utility.displayExceptionDialog(new NumberFormatException("Mã hộ khẩu chỉ được chứa chữ số!"));
            return;
        }
        //TODO here: xử lý ngoại lệ với cơ sở dữ liệu
        String soNhaText = soNha.getText();
        String ngachText = ngach.getText();
        String ngoText = ngo.getText();
        String duongText = duong.getText();
        String phuongText = phuong.getText();
        String quanText = quan.getText();
        String thanhPhoText = thanhPho.getText();
        System.out.println(idHK);
        System.out.println(soNhaText);
        System.out.println(ngachText);
        System.out.println(ngoText);
        System.out.println(duongText);
        System.out.println(phuongText);
        System.out.println(quanText);
        System.out.println(thanhPhoText);
        //TODO with database
        try {
            HoKhauModel.add(idHK, soNhaText, ngachText, ngoText,duongText, phuongText, quanText,thanhPhoText);
            //TODO: thông báo thêm thành công
            Utility.displaySuccessDialog("Thêm thành công!");
        } catch (SQLException e){
            e.printStackTrace();
            //TODO: thông báo lỗi không thêm được
            Utility.displayExceptionDialog(e);
        }
        //TODO: chuyển đến trang

    }

    public void setMaHoKhau(int maHoKhau) {
        this.maHoKhau.setText(String.valueOf(maHoKhau));
        this.maHoKhau.setDisable(true);
    }
}

