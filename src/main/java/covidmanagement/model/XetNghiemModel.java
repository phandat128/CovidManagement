package covidmanagement.model;

import covidmanagement.Main;
import covidmanagement.Utility;
import covidmanagement.controller.xetnghiemcontroller.SuaXetNghiemController;
import covidmanagement.database.QueryDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class XetNghiemModel {
    private int maXN;
    private int maNK;
    private String name;
    private String cmnd;
    private LocalDate date;
    private String place;
    private KetQuaXetNghiem result;
    private Button changeButton, deleteButton;

    public XetNghiemModel(int maXN, int maNK, LocalDate date, String place, KetQuaXetNghiem result) {
        this.maXN = maXN;
        this.maNK = maNK;
        this.date = date;
        this.place = place;
        this.result = result;
        this.changeButton = new Button("Sửa");
        this.deleteButton = new Button("Xóa");
        changeButton.setOnAction(this::handleChangeClick);
        deleteButton.setOnAction(this::handleDeleteClick);
        setNameAndCMND(maNK);
    }


    public static List<XetNghiemModel> getXetNghiemList(){
        List<XetNghiemModel> queryList = new ArrayList<>();
        try {
            QueryDB queryDB = new QueryDB();
            String sql = "select * from xetnghiem;";

            ResultSet rs = queryDB.executeQuery(sql);
            while (rs.next()) {
                int _maXN = rs.getInt("maxetnghiem");
                int _maNK = rs.getInt("manhankhau");
                LocalDate _date = rs.getDate("thoigian").toLocalDate();
                String _place = rs.getString("diadiem");
                KetQuaXetNghiem _result = KetQuaXetNghiem.valueOf(rs.getString("ketqua"));
                queryList.add(new XetNghiemModel(_maXN, _maNK, _date, _place, _result));
            }
            rs.close();
            queryDB.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return queryList;
    }

    public static void add(int maNK, String name, LocalDate date, String place, KetQuaXetNghiem result) throws SQLException{
        NhanKhauModel nhanKhau = NhanKhauModel.getInstanceById(maNK);
        if (!nhanKhau.getHoTen().equalsIgnoreCase(name)) throw new SQLException("Tên và mã nhân khẩu không trùng khớp");
        QueryDB queryDB = new QueryDB();
        PreparedStatement statement = queryDB.getConnection().prepareStatement(
                "INSERT INTO XetNghiem(manhankhau, thoigian, diadiem, ketqua) VALUES (?, ?, ?, ?);"
        );
        statement.setInt(1, maNK);
        statement.setDate(2, Date.valueOf(date));
        statement.setString(3, place);
        statement.setString(4, result.toString());
        statement.executeUpdate();
        statement.close();
        queryDB.close();

        LichSuModel.add("Thêm xét nghiệm của " + name);
    }

    public static void update(int maXetNghiem, LocalDate date, String place, KetQuaXetNghiem result) throws SQLException{
        QueryDB queryDB = new QueryDB();
        PreparedStatement statement = queryDB.getConnection().prepareStatement(
                "UPDATE XetNghiem SET (thoigian, diadiem, ketqua) = (?, ?, ?) WHERE maxetnghiem = ?;"
        );
        statement.setDate(1, Date.valueOf(date));
        statement.setString(2, place);
        statement.setString(3, result.toString());
        statement.setInt(4, maXetNghiem);
        statement.executeUpdate();
        statement.close();
        queryDB.close();

        LichSuModel.add("Sửa đổi thông tin xét nghiệm số " + maXetNghiem);
    }

    public static void deleteXetNghiem(int maNhanKhau) throws SQLException {
        PreparedStatement preparedStatement = null;
        QueryDB queryDB = null;
        try {
            queryDB = new QueryDB();
            preparedStatement = queryDB.getConnection().prepareStatement(
                    "DELETE FROM xetnghiem WHERE MaNhankhau = ?;");
            preparedStatement.setInt(1, maNhanKhau);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            queryDB.close();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Lỗi khi Xóa dữ liệu: " + e.getMessage() + "." + "Vui lòng thực hiện lại!!");
            alert.show();
        }
    }

    private void handleDeleteClick(ActionEvent event) {
        Utility.displayConfirmDeleteDialog("Xác nhận xóa?", this.maXN, "XetNghiem");
        LichSuModel.add("Xóa xét nghiệm số " + this.maXN);
    }

    private void handleChangeClick(ActionEvent event){
        System.out.println(maNK);
        System.out.println(name);
        System.out.println(date);
        System.out.println(place);
        System.out.println(result);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("xetnghiem/suaxetnghiem-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Chỉnh sửa thông tin xét nghiệm");
            stage.show();
            SuaXetNghiemController controller = fxmlLoader.getController();
            controller.setField(maXN, maNK, name, date, place, result);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public int getMaNK() {
        return maNK;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public KetQuaXetNghiem getResult() {
        return result;
    }

    public Button getChangeButton() {
        return changeButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    private void setNameAndCMND(int maNK){
        //TODO
        try {
            NhanKhauModel nhanKhau = NhanKhauModel.getInstanceById(maNK);
            this.name = nhanKhau.getHoTen();
            this.cmnd = nhanKhau.getCMNDCCCD();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public enum KetQuaXetNghiem{
        DƯƠNG_TÍNH, ÂM_TÍNH, CHƯA_XÁC_ĐỊNH
    }
}