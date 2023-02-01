package covidmanagement.controller.cachlycontroller;

import covidmanagement.Utility;
import javafx.collections.FXCollections;
import covidmanagement.model.CachLyModel;
import covidmanagement.model.CachLyModel.MucDoCachLy;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.time.format.DateTimeFormatter;

public class ThemCachLyController implements Initializable {
    @FXML
    TextField MaNKCl, nameCl, placeCl;
    @FXML
    DatePicker beginDateCl, finishDateCl;
    @FXML
    ChoiceBox<CachLyModel.MucDoCachLy> mucdoCl;
    @FXML
    Button addButtonCl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mucdoCl.setItems(FXCollections.observableArrayList(CachLyModel.MucDoCachLy.values()));
        mucdoCl.setValue(CachLyModel.MucDoCachLy.UNDEFINED);

        // set date format to DD/MM/yyyy
        beginDateCl.setConverter(new StringConverter<>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null) return "";
                return formatter.format(localDate);
            }
            @Override
            public LocalDate fromString(String s) {
                if (s.isBlank()) return null;
                return LocalDate.parse(s, formatter);
            }
        });
        finishDateCl.setConverter(new StringConverter<>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null) return "";
                return formatter.format(localDate);
            }
            @Override
            public LocalDate fromString(String s) {
                if (s.isBlank()) return null;
                return LocalDate.parse(s, formatter);
            }
        });
    }

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
        if (beginDateCl.getValue() == null) {
            RuntimeException ngaySinhException = new RuntimeException("Trường Bắt Đầu không được để trống!");
            Utility.displayExceptionDialog(ngaySinhException);
            throw ngaySinhException;
        }
        if (finishDateCl.getValue() == null) {
            RuntimeException ngaySinhException = new RuntimeException("Trường Kết thúc không được để trống!");
            Utility.displayExceptionDialog(ngaySinhException);
            throw ngaySinhException;
        }
        LocalDate begin = beginDateCl.getValue();
        LocalDate finish = finishDateCl.getValue();
        if (begin != null && finish != null){
            if (begin.isAfter(finish)) {
                Utility.displayExceptionDialog(new RuntimeException("Khoảng thời gian không hợp lệ!"));
                return;
            }
        }
        //xử lý ngoại lệ trường hợp trường mã nhân khẩu
        try {
            Integer.parseInt(MaNKCl.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Utility.displayExceptionDialog(new NumberFormatException("Mã nhân khẩu chỉ được chứa chữ số!"));
            return;
        }
        int idNK = Integer.parseInt(MaNKCl.getText());
        if (idNK <= 0) {
            RuntimeException notPositiveException = new RuntimeException("Mã nhân khẩu phải là số dương");
            Utility.displayExceptionDialog(notPositiveException);
            throw notPositiveException;
        }


        String name = nameCl.getText();
        LocalDate begindate = beginDateCl.getValue();
        LocalDate finishdate = finishDateCl.getValue();
        String place = placeCl.getText();
        MucDoCachLy mucdo = mucdoCl.getValue();



        try {
            CachLyModel.addCl(idNK,name, begindate ,finishdate ,place, mucdo);

            Utility.displaySuccessDialog("Thêm thành công!");
        } catch (SQLException e){
            e.printStackTrace();

            Utility.displayExceptionDialog(e);
        }
    }

}


