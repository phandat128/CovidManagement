package covidmanagement.controller.nhankhaucontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class ChinhSuaController {

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
    private TableView<?> tableNhanKhau;
    @FXML
    private TableColumn<?, ?> columnHoVaTen;
    @FXML
    private TableColumn<?, ?> columnMaChuHo;
    @FXML
    private TableColumn<?, ?> columnNgheNghiep;
    @FXML
    private TableColumn<?, ?> collumnMaNhanKhau;
    @FXML
    private TableColumn<?, ?> columnGioiTinh;
    @FXML
    private TableColumn<?, ?> columnSDT;
    @FXML
    private TableColumn<?, ?> columnNgaySinh;
    @FXML
    private TableColumn<?, ?> columnCMND_CCCD;
    @FXML
    private AnchorPane chinhsuanhankhauView;
    @FXML
    private Button btnTimKiem, btnSua, btnXem, btnXoa;
    @FXML
    void xemActionevent(ActionEvent event) {

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