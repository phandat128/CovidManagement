package covidmanagement.controller.nhankhaucontroller;

import covidmanagement.Main;
import covidmanagement.Utility;
import covidmanagement.model.NhanKhauModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TimKiemNhanKhauController implements Initializable {

    @FXML
    private TableColumn<NhanKhauModel, String> columnHoVaTen, columnGioiTinh, columnNgheNghiep, columnCMND_CCCD, columnSDT, columnNgaySinh;

    @FXML
    private TableColumn<NhanKhauModel, Integer> columnMaNhanKhau, columnMaChuHo, columnid;
    @FXML
    private TableView<NhanKhauModel> tableNhanKhau;
    @FXML
    private AnchorPane timkiemnhankhauView;

    @FXML
    private TextField txtHoVaTen, txtMaNhanKhau, txtSDT, txtCMND_CDDD, txtMaHoKhau;
    @FXML
    private DatePicker pickerNgaySinh;

    @FXML
    private Button btnTimKiem, btnXem, btnReset;


    private final ObservableList<NhanKhauModel> nhanKhauList = FXCollections.observableArrayList(
            NhanKhauModel.getNhanKhauList());
    private final FilteredList<NhanKhauModel> filteredList = new FilteredList<>(nhanKhauList);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pickerNgaySinh.setConverter(Utility.LOCAL_DATE_CONVERTER);

        columnMaNhanKhau.setCellValueFactory(new PropertyValueFactory<>("MaNhanKhau"));
        columnHoVaTen.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
        columnNgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
        columnGioiTinh.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
        columnSDT.setCellValueFactory(new PropertyValueFactory<>("SDT"));
        columnCMND_CCCD.setCellValueFactory(new PropertyValueFactory<>("CMNDCCCD"));
        columnNgheNghiep.setCellValueFactory(new PropertyValueFactory<>("NgheNghiep"));
        columnMaChuHo.setCellValueFactory(new PropertyValueFactory<>("MaHoKhau"));

        columnid.setCellValueFactory(
                cellDataFeatures -> new ReadOnlyObjectWrapper<>(
                        tableNhanKhau.getItems().indexOf(cellDataFeatures.getValue()) + 1
                ));
        columnid.setSortable(false);

        tableNhanKhau.setItems(filteredList);

    }


    @FXML
    public void xemActionevent(ActionEvent event) {

        int selectedIndex = tableNhanKhau.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            NhanKhauModel nhankhau = tableNhanKhau.getSelectionModel().getSelectedItem();
            int manhankhau = nhankhau.getMaNhanKhau();
            String hoten = nhankhau.getHoTen();
            String gioitinh = nhankhau.getGioiTinh();
            LocalDate ngaysinh = nhankhau.getNgaySinh();
            String cmnd = nhankhau.getCMNDCCCD();
            String quoctich = nhankhau.getQuocTich();
            String tongiao = nhankhau.getTonGiao();
            String sdt = nhankhau.getSDT();
            String nguyenquan = nhankhau.getNguyenQuan();
            String nghenghiep = nhankhau.getNgheNghiep();
            int machuho = nhankhau.getMaHoKhau();
            Boolean lachuho = nhankhau.getLaChuHo();
            String quanhevoichuho = nhankhau.getQuanHeVoiChuHo();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("nhankhau/xemnhankhau-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Chi tiết thông tin nhân khẩu");
                stage.show();
                XemNhanKhauController controller = fxmlLoader.getController();
                controller.setField(manhankhau, hoten, gioitinh, ngaysinh, cmnd, quoctich, tongiao, sdt, nguyenquan,
                        nghenghiep, machuho, lachuho, quanhevoichuho);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Chưa chọn nhân khẩu");
            alert.show();
        }

    }

    @FXML
    void timkiemActionevent(ActionEvent event) {
        int maNhanKhau = 0;
        String maNhanKhauValue = txtMaNhanKhau.getText();
        if (!maNhanKhauValue.isBlank()) {
            maNhanKhau = Integer.parseInt(maNhanKhauValue);
        }
        String finalHoVaTen = txtHoVaTen.getText();
        LocalDate finalNgaySinh = pickerNgaySinh.getValue();
        String finalCmnd_CCCD_ = txtCMND_CDDD.getText();
        String finalSDT = txtSDT.getText();

        int maHoKhau = 0;
        String maHoKhauValue = txtMaHoKhau.getText();
        if (!maHoKhauValue.isBlank()) {
            maHoKhau = Integer.parseInt(maHoKhauValue);
        }

        int finalMaNhanKhau = maNhanKhau;
        int finalMaHoKhau = maHoKhau;

//        ObservableList<NhanKhauModel> data = tableNhanKhau.getItems();
        ObservableList<NhanKhauModel> data = FXCollections.observableArrayList(NhanKhauModel.getNhanKhauList());
        FilteredList<NhanKhauModel> filteredData = new FilteredList<>(data, p -> true);

        filteredData.setPredicate(nhanKhauRow -> {
            if (finalMaNhanKhau == 0 && finalHoVaTen.isBlank() && finalNgaySinh == null && finalCmnd_CCCD_.isBlank() && finalSDT.isBlank() && finalMaHoKhau == 0) {
                return true;
            }
            if (finalMaNhanKhau != 0 && nhanKhauRow.getMaNhanKhau() != finalMaNhanKhau) return false;
            if (!finalHoVaTen.isBlank() && !nhanKhauRow.getHoTen().contains(finalHoVaTen)) return false;
            if (finalNgaySinh != null && !nhanKhauRow.getNgaySinh().equals(finalNgaySinh)) return false;
            if (!finalCmnd_CCCD_.isBlank() && !nhanKhauRow.getCMNDCCCD().contains(finalCmnd_CCCD_)) return false;
            if (!finalSDT.isBlank() && !nhanKhauRow.getSDT().contains(finalSDT)) return false;
            if (finalMaHoKhau != 0 && nhanKhauRow.getMaHoKhau() != finalMaHoKhau) return false;
            return true;
        });
        tableNhanKhau.setItems(filteredData);
    }
    @FXML
    void resetActionevent(ActionEvent event) {
        txtMaNhanKhau.setText("");
        txtHoVaTen.setText("");
        pickerNgaySinh.setValue(null);
        txtCMND_CDDD.setText("");
        txtSDT.setText("");
        txtMaHoKhau.setText("");
        tableNhanKhau.setItems(filteredList);

    }

    public void setTableNhanKhau(TableView<NhanKhauModel> tableNhanKhau) {
        this.tableNhanKhau = tableNhanKhau;
    }
}