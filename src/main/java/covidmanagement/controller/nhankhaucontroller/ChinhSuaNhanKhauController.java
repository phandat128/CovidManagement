package covidmanagement.controller.nhankhaucontroller;

import covidmanagement.Main;
import covidmanagement.controller.xetnghiemcontroller.SuaController;
import covidmanagement.model.NhanKhauModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
    private TableColumn<NhanKhauModel, Integer> columnMaChuHo,columnMaNhanKhau;
    @FXML
    private TextField txtHoVaTen, txtSDT, txtCMND_CDDD, txtMaHoKhau, txtMaNhanKhau;
    @FXML
    private DatePicker pickerNgaySinh;
    @FXML
    private Button btnSua, btnTimKiem, btnXoa;

    private final ObservableList<NhanKhauModel> nhanKhauList = FXCollections.observableArrayList(NhanKhauModel.getNhanKhauList());
    private final FilteredList<NhanKhauModel> List = new FilteredList<>(nhanKhauList);

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

        tableNhanKhau.setItems(List);

    }


    @FXML
    void timkiemActionevent(ActionEvent event) {

    }

    @FXML
    void xoaActionevent(ActionEvent event) {

    }

    @FXML
    void suaActionevent(ActionEvent event) throws IOException {
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
                    nghenghiep, machuho, lachuho, quanhevoichuho );
        } catch(IOException e){
            e.printStackTrace();
        }

    }

}
