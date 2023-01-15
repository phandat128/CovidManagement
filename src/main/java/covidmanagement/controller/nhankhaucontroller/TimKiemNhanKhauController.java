package covidmanagement.controller.nhankhaucontroller;

import covidmanagement.Main;
import covidmanagement.model.NhanKhauModel;
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
    private TableColumn<NhanKhauModel, Integer> columnMaNhanKhau,columnMaChuHo;
    @FXML
    private TableView<NhanKhauModel> tableNhanKhau;
    @FXML
    private AnchorPane timkiemnhankhauView;

    @FXML
    private TextField txtHoVaTen, txtMaNhanKhau, txtSDT, txtCMND_CDDD, txtMaHoKhau;
    @FXML
    private DatePicker pickerNgaySinh;

    @FXML
    private Button btnTimKiem, btnXem;


    private final ObservableList<NhanKhauModel> nhanKhauList = FXCollections.observableArrayList(
            NhanKhauModel.getNhanKhauList()
    );
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

        tableNhanKhau.setItems(filteredList);

    }




    @FXML
    void xemActionevent(ActionEvent event) {
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
            stage.show();
            XemNhanKhauController controller = fxmlLoader.getController();
            controller.setField(manhankhau, hoten, gioitinh, ngaysinh, cmnd, quoctich, tongiao, sdt, nguyenquan,
                    nghenghiep, machuho, lachuho, quanhevoichuho );
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    void timkiemActionevent(ActionEvent event) {

    }


}