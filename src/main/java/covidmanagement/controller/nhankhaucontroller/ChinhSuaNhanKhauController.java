package covidmanagement.controller.nhankhaucontroller;

import covidmanagement.Main;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ChinhSuaNhanKhauController implements Initializable {

    @FXML
    private AnchorPane chinhsuanhankhauView;
    @FXML
    private TableView<NhanKhauModel> tableNhanKhau;
    @FXML
    private TableColumn<NhanKhauModel, String> columnHoVaTen, columnNgheNghiep, columnCMND_CCCD, columnGioiTinh, columnNgaySinh, columnSDT;
    @FXML
    private TableColumn<NhanKhauModel, Integer> columnMaChuHo,columnMaNhanKhau, columnid;
    @FXML
    private TextField txtHoVaTen, txtSDT, txtCMND_CDDD, txtMaHoKhau, txtMaNhanKhau;
    @FXML
    private DatePicker pickerNgaySinh;
    @FXML
    private Button btnSua, btnTimKiem, btnXoa, btnReset;

    private final ObservableList<NhanKhauModel> nhanKhauList = FXCollections.observableArrayList(NhanKhauModel.getNhanKhauList());
    private final FilteredList<NhanKhauModel> filteredList = new FilteredList<>(nhanKhauList);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
    public void xoaActionevent(ActionEvent event) {

        int selectedIndex = tableNhanKhau.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Are you sure?");
            alert.setHeaderText("Xác nhận xóa?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("Are you sure?");
                alert1.setHeaderText("Xóa nhân khẩu sẽ xóa tất cả mọi dữ liệu bao gồm cả hộ khẩu, khai báo, cách ly, xét nghiệm của nhân khẩu."
                        +" Bạn vẫn chắc chắn muốn xóa?");
                alert1.showAndWait();

                if (alert1.getResult() == ButtonType.OK)
                //delete in database
                {
                    NhanKhauModel nhankhau = tableNhanKhau.getSelectionModel().getSelectedItem();
                    int manhankhau = nhankhau.getMaNhanKhau();
                try {
                    NhanKhauModel.deleteCahLy(manhankhau);
                    NhanKhauModel.deleteKhaiBao(manhankhau);
                    NhanKhauModel.deleteXetNghiem(manhankhau);
                    NhanKhauModel.deleteNhanKhau(manhankhau);
                } catch (SQLException e){
                    e.printStackTrace();
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setHeaderText("Xóa nhân khẩu thất bại!!");
                    alert2.show();
                }

                    // delete in table
                    FilteredList<NhanKhauModel> filteredData = (FilteredList<NhanKhauModel>) tableNhanKhau.getItems();
                    NhanKhauModel selectedItem = filteredData.get(selectedIndex);
                    ObservableList<NhanKhauModel> sourceData = (ObservableList<NhanKhauModel>) filteredData.getSource();
                    boolean removed = sourceData.remove(selectedItem);
                    if (removed) {
                        tableNhanKhau.getSelectionModel().clearSelection();
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setHeaderText("Xóa nhân khẩu thành công!!");
                        alert2.show();
                    }
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Chưa chọn nhân khẩu");
            alert.show();

        }

    }

    @FXML
    public void suaActionevent(ActionEvent event) {

        int selectedIndex = this.tableNhanKhau.getSelectionModel().getSelectedIndex();
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
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("nhankhau/suanhankhau-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                SuaNhanKhauController controller = fxmlLoader.getController();
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
    void resetActionevent(ActionEvent event) {
        txtMaNhanKhau.setText("");
        txtHoVaTen.setText("");
        pickerNgaySinh.setValue(null);
        txtCMND_CDDD.setText("");
        txtSDT.setText("");
        txtMaHoKhau.setText("");

        final ObservableList<NhanKhauModel> nhanKhauList = FXCollections.observableArrayList(NhanKhauModel.getNhanKhauList());
        final FilteredList<NhanKhauModel> List1 = new FilteredList<>(nhanKhauList);

        tableNhanKhau.setItems(List1);

    }

    public void setTableNhanKhau(TableView<NhanKhauModel> tableNhanKhau) {
        this.tableNhanKhau = tableNhanKhau;
    }
}
