package covidmanagement.controller.nhankhaucontroller;

import covidmanagement.database.Database;
import covidmanagement.model.NhanKhauModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class ThemController implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnThem;

    @FXML
    private Button btnDong;

    @FXML
    private TableColumn<?, ?> collumnMaNhanKhau;

    @FXML
    private TableColumn<?, ?> columnCMND_CCCD;

    @FXML
    private TableColumn<?, ?> columnGioiTinh;

    @FXML
    private TableColumn<?, ?> columnHoVaTen;

    @FXML
    private TableColumn<?, ?> columnLaChuHo;

    @FXML
    private TableColumn<?, ?> columnMaChuHo;

    @FXML
    private TableColumn<?, ?> columnNgaySinh;

    @FXML
    private TableColumn<?, ?> columnNgheNghiep;

    @FXML
    private TableColumn<?, ?> columnNguyenQuan;

    @FXML
    private TableColumn<?, ?> columnQuanHeVoiChuHo;

    @FXML
    private TableColumn<?, ?> columnQuocTich;

    @FXML
    private TableColumn<?, ?> columnSDT;

    @FXML
    private TableColumn<?, ?> columnTonGiao;

    @FXML
    private TableView<?> tableThem;

    @FXML
    private TextField txtMaNhanKhau, txtHoVaTen, txtGioiTinh, txtCMND_CCCD, txtQuocTich, txtTonGiao, txtSDT, txtNguyenQuan, txtNgheNghiep, txtMaHoKhau, txtLaChuHo, txtQuanHeVoiChuHo;

    @FXML
    private DatePicker pickerNgaySinh;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    void addActionevent(ActionEvent event) {
        String maNhanKhau = txtMaNhanKhau.getText();
        String hoVaTen = txtHoVaTen.getText();
        String gioiTinh = txtGioiTinh.getText();
        String ngaySinh = pickerNgaySinh.getValue().toString();
        String cmnd_CCCD_ = txtCMND_CCCD.getText();
        String quocTich = txtQuocTich.getText();
        String tonGiao = txtTonGiao.getText();
        String sDT = txtSDT.getText();
        String nguyenQuan = txtNguyenQuan.getText();
        String ngheNghiep = txtNgheNghiep.getText();
        String maHoKhau = txtMaHoKhau.getText();
        String laChuHo = txtLaChuHo.getText();
        String quanHeVoiChuHo = txtQuanHeVoiChuHo.getText();

        try {
            Connection conn = Database.ConnectDB();
            PreparedStatement preparedStatement;
            String INSERT_QUERY = "INSERT INTO nhankhau VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = conn.prepareStatement(INSERT_QUERY);
            preparedStatement.setString(1, maNhanKhau);
            preparedStatement.setString(2, hoVaTen);
            preparedStatement.setString(3, ngaySinh);
            preparedStatement.setString(4, gioiTinh);
            preparedStatement.setString(5, cmnd_CCCD_);
            preparedStatement.setString(6, sDT);
            preparedStatement.setString(7, quocTich);
            preparedStatement.setString(8, tonGiao);
            preparedStatement.setString(9, nguyenQuan);
            preparedStatement.setString(10, maHoKhau);
            preparedStatement.setString(11, laChuHo);
            preparedStatement.setString(12, quanHeVoiChuHo);
            preparedStatement.setString(13, ngheNghiep);

            Database.closeDB(conn);




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void closeActionevent(ActionEvent event){
        Stage stage = (Stage) btnDong.getScene().getWindow();
        stage.close();
    }
//    public TextField getTxtHoVaTen() { return txtHoVaTen; }
//    public void setTxtHoVaTen(TextField txtHoVaTen) { this.txtHoVaTen = txtHoVaTen;}


}




