package covidmanagement.controller.hokhaucontroller;

import covidmanagement.Utility;
import covidmanagement.model.HoKhauModel;
import covidmanagement.model.XetNghiemModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TimKiemHoKhauController implements Initializable {
    @FXML
    TextField thanhPho, quan, phuong, duong;
    @FXML
    TableView<HoKhauModel> table;
    @FXML
    TableColumn<HoKhauModel, Integer> sttColumn;

    @FXML
    TableColumn<HoKhauModel, String> maHoKhauColumn;
    @FXML
    TableColumn<HoKhauModel, String> maChuHoColumn;
    @FXML
    TableColumn<HoKhauModel, String> soNhaColumn;
    @FXML
    TableColumn<HoKhauModel, String> ngachColumn;
    @FXML
    TableColumn<HoKhauModel, String> ngoColumn;
    @FXML
    TableColumn<HoKhauModel, String> duongColumn;
    @FXML
    TableColumn<HoKhauModel, String> phuongColumn;
    @FXML
    TableColumn<HoKhauModel, String> quanColumn;
    @FXML
    TableColumn<HoKhauModel, String> thanhPhoColumn;
    @FXML
    TableColumn<HoKhauModel, String> suaColumn;
    @FXML
    TableColumn<HoKhauModel, String> xoaColumn;
    private final ObservableList<HoKhauModel> HoKhauList = FXCollections.observableArrayList(
            new HoKhauModel(1, 423, 193754683, "35a", "32", "214", "láng", "Láng Thượng", "Đống Đa", "Hà Nội")
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        maHoKhauColumn.setCellValueFactory(new PropertyValueFactory<>("maHK"));
        maChuHoColumn.setCellValueFactory(new PropertyValueFactory<>("maCH"));
        soNhaColumn.setCellValueFactory(new PropertyValueFactory<>("soNha"));
        ngachColumn.setCellValueFactory(new PropertyValueFactory<>("ngach"));
        ngoColumn.setCellValueFactory(new PropertyValueFactory<>("ngo"));
        duongColumn.setCellValueFactory(new PropertyValueFactory<>("duong"));
        phuongColumn.setCellValueFactory(new PropertyValueFactory<>("phuong"));
        quanColumn.setCellValueFactory(new PropertyValueFactory<>("quan"));
        thanhPhoColumn.setCellValueFactory(new PropertyValueFactory<>("thanhpho"));
        suaColumn.setCellValueFactory(new PropertyValueFactory<>("suaButton"));
        xoaColumn.setCellValueFactory(new PropertyValueFactory<>("xoaButton"));

        table.setItems(HoKhauList);

        //set serial number column
        sttColumn.setCellValueFactory(
                cellDataFeatures -> new ReadOnlyObjectWrapper<>(
                        table.getItems().indexOf(cellDataFeatures.getValue()) + 1
                ));
        sttColumn.setSortable(false);
    }
    public void timKiem(ActionEvent event) throws RuntimeException{


        System.out.println(thanhPho);
        System.out.println(quan);
        System.out.println(phuong);
        System.out.println(duong);
    }
}