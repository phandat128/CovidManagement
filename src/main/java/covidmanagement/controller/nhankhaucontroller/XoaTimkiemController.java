package covidmanagement.controller.nhankhaucontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class XoaTimkiemController {

    @FXML
    private TextField txtHoVaTen;
    @FXML
    private TextField txtMaNhanKhau;
    @FXML
    private TextField txtCMND_CDDD;
    @FXML
    private TextField txtSDT;
    @FXML
    private TextField txtMaHoKhau;
    @FXML
    private DatePicker pickerNgaySinh;

    @FXML
    private TableView<?> tableThem;
    @FXML
    private TableColumn<?, ?> columnHoVaTen;

    @FXML
    private TableColumn<?, ?> columnMaChuHo;

    @FXML
    private TableColumn<?, ?> columnTonGiao;

    @FXML
    private TableColumn<?, ?> columnNgheNghiep;

    @FXML
    private TableColumn<?, ?> collumnMaNhanKhau;

    @FXML
    private TableColumn<?, ?> columnLaChuHo;

    @FXML
    private TableColumn<?, ?> columnQuanHeVoiChuHo;

    @FXML
    private TableColumn<?, ?> columnNguyenQuan;

    @FXML
    private TableColumn<?, ?> columnGioiTinh;

    @FXML
    private TableColumn<?, ?> columnSDT;

    @FXML
    private TableColumn<?, ?> columnNgaySinh;

    @FXML
    private Button themButton;
    @FXML
    private AnchorPane themnhankhauView;

    @FXML
    private TableColumn<?, ?> columnCMND_CCCD;

    @FXML
    private TableColumn<?, ?> columnQuocTich;

    @FXML
    void dongActionevent(ActionEvent event) {

    }

    @FXML
    void xoaActionevent(ActionEvent event) {

    }

    @FXML
    void suaActionevent(ActionEvent event) {

    }

    @FXML
    void timkiemActionevent(ActionEvent event) {

    }

}