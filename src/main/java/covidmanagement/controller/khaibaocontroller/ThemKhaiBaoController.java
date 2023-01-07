package covidmanagement.controller.khaibaocontroller;

import covidmanagement.Main;
import covidmanagement.Utility;
import covidmanagement.model.KhaiBaoModel;
import covidmanagement.model.XetNghiemModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ThemKhaiBaoController implements Initializable {
    @FXML
    TextField declareSpotField, nameField, IDField, traceField, benhNenField;
    @FXML
    ChoiceBox<KhaiBaoModel.gioiTinh> gender;
    @FXML
    RadioButton BHYTYes, BHYTNo, symptomYes, symptomNo, covidContactYes, covidContactNo, countryContactYes, countryContactNo, symptomContactYes, symptomContactNo;
    @FXML
    DatePicker declareDate;
    @FXML
    Button saveDeclare;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        gender.setItems(FXCollections.observableArrayList(KhaiBaoModel.gioiTinh.values()));
        gender.setValue(KhaiBaoModel.gioiTinh.UNDEFINED);
        declareDate.setValue(LocalDate.now());
    }

    public void onAdd(){
        //xử lý ngoại lệ trường hợp các trường thông tin cần thiết bị thiếu
        if (IDField.getText().isBlank()){
            RuntimeException IDException = new RuntimeException("Trường CMND/CCCD không được để trống!");
            Utility.displayExceptionDialog(IDException);
            throw IDException;
        }
        if (nameField.getText().isBlank()){
            RuntimeException nameException = new RuntimeException("Trường họ tên không được để trống!");
            Utility.displayExceptionDialog(nameException);
            throw nameException;
        }
        if (declareSpotField.getText().isBlank()){
            RuntimeException placeException = new RuntimeException("Trường địa điểm khai báo không được để trống!");
            Utility.displayExceptionDialog(placeException);
            throw placeException;
        }
        if (gender.getValue() == KhaiBaoModel.gioiTinh.UNDEFINED){
            RuntimeException genderException = new RuntimeException("Trường giới tính không được để trống!");
            Utility.displayExceptionDialog(genderException);
            throw genderException;
        }

        //xử lý ngoại lệ trường hợp trường mã nhân khẩu không phải là chữ số
        int ID = 0;
        try {
            ID = Integer.parseInt(IDField.getText());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Utility.displayExceptionDialog(new NumberFormatException("CCCD/CMND chỉ được chứa chữ số!"));
            return;
        }
        //TODO here: xử lý ngoại lệ với cơ sở dữ liệu
        String name = nameField.getText();
        LocalDate date = declareDate.getValue();
        String place = declareSpotField.getText();
        KhaiBaoModel.gioiTinh result = gender.getValue();
        System.out.println(ID == 0 ? null : ID);
        System.out.println(name);
        System.out.println(date);
        System.out.println(place);
        System.out.println(result);

        //TODO with database
    }
    public void switchToMainView(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Parent componentScene = fxmlLoader.load();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(componentScene);
        stage.setScene(scene);
        stage.show();
    }
}
