package covidmanagement.controller.hokhaucontroller;
import covidmanagement.controller.nhankhaucontroller.ChinhSuaNhanKhauController;
import covidmanagement.controller.nhankhaucontroller.TimKiemNhanKhauController;
import covidmanagement.model.NhanKhauModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class XemHoKhauController implements Initializable {

    @FXML
    TableColumn<NhanKhauModel, String> columnHoVaTen, columnGioiTinh, columnNgheNghiep, columnCMND_CCCD, columnSDT, columnNgaySinh, columnQuanHe;
    @FXML
    TableColumn<NhanKhauModel, Integer> columnMaNhanKhau, columnid;
    @FXML
    TableView<NhanKhauModel> tableNhanKhau;
    @FXML
    Label maHoKhauLabel, tenChuHoLabel;
    @FXML
    Button btnXem, btnSua, btnXoa;

    private int maHoKhau;

    private ObservableList<NhanKhauModel> nhanKhauTrongHoList;

    private final TimKiemNhanKhauController timKiemNhanKhauController = new TimKiemNhanKhauController();
    private final ChinhSuaNhanKhauController chinhSuaNhanKhauController = new ChinhSuaNhanKhauController();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timKiemNhanKhauController.setTableNhanKhau(this.tableNhanKhau);
        chinhSuaNhanKhauController.setTableNhanKhau(this.tableNhanKhau);
        try {
            nhanKhauTrongHoList = FXCollections.observableArrayList(NhanKhauModel.getNhanKhauListByMaHK(maHoKhau));
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        columnMaNhanKhau.setCellValueFactory(new PropertyValueFactory<>("MaNhanKhau"));
        columnHoVaTen.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
        columnNgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
        columnGioiTinh.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
        columnSDT.setCellValueFactory(new PropertyValueFactory<>("SDT"));
        columnCMND_CCCD.setCellValueFactory(new PropertyValueFactory<>("CMNDCCCD"));
        columnNgheNghiep.setCellValueFactory(new PropertyValueFactory<>("NgheNghiep"));
        columnQuanHe.setCellValueFactory(new PropertyValueFactory<>("QuanHeVoiChuHo"));

        columnid.setCellValueFactory(
                cellDataFeatures -> new ReadOnlyObjectWrapper<>(
                        tableNhanKhau.getItems().indexOf(cellDataFeatures.getValue()) + 1
                ));
        columnid.setSortable(false);
        tableNhanKhau.setItems(nhanKhauTrongHoList);

        maHoKhauLabel.setText(String.valueOf(maHoKhau));
        for (NhanKhauModel nhankhau: nhanKhauTrongHoList){
            if (nhankhau.getLaChuHo()){
                tenChuHoLabel.setText(nhankhau.getHoTen());
                break;
            }
        }

        btnXem.setOnAction(timKiemNhanKhauController::xemActionevent);
        btnSua.setOnAction(chinhSuaNhanKhauController::suaActionevent);
        btnXoa.setOnAction(chinhSuaNhanKhauController::xoaActionevent);
    }

    public void setMaHoKhau(int maHoKhau) {
        this.maHoKhau = maHoKhau;
    }
}
