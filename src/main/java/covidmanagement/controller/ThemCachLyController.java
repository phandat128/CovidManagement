package covidmanagement.controller;

import covidmanagement.Utility;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ThemCachLyController  {
    @FXML
    TextField MaNKCl, nameCl, placeCl;
    @FXML
    DatePicker begindateCl, finishdateCl;
    @FXML
    Button addButtonCl;




    public void them(){
        //xử lý ngoại lệ trường hợp các trường thông tin cần thiết bị thiếu
        if (MaNKCl.getText().isBlank()){
            RuntimeException MaNKClException = new RuntimeException("Trường mã nhân khẩu không được để trống!");
            Utility.displayExceptionDialog(MaNKClException);
            throw MaNKClException;
        }
        if (nameCl.getText().isBlank()){
            RuntimeException nameClException = new RuntimeException("Trường họ tên không được để trống!");
            Utility.displayExceptionDialog(nameClException);
            throw nameClException;
        }
        if (placeCl.getText().isBlank()){
            RuntimeException placeClException = new RuntimeException("Trường địa điểm không được để trống!");
            Utility.displayExceptionDialog(placeClException);
            throw placeClException;
        }
        //xử lý ngoại lệ trường hợp trường mã nhân khẩu không phải là chữ số
        int idNK = 0;
        try {
            idNK = Integer.parseInt(MaNKCl.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Utility.displayExceptionDialog(new NumberFormatException("Mã nhân khẩu chỉ được chứa chữ số!"));
            return;
        }
        //TODO here: xử lý ngoại lệ với cơ sở dữ liệu
        String name = nameCl.getText();
        LocalDate begindate = begindateCl.getValue();
        LocalDate finishdate = finishdateCl.getValue();
        String place = placeCl.getText();
        System.out.println(idNK == 0 ? null : idNK);
        System.out.println(name);
        System.out.println(begindate);
        System.out.println(finishdate);
        System.out.println(place);

        //TODO with database
//        String sql = "INSERT INTO XetNghiem ()"
    }

}


