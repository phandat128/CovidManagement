package covidmanagement.model;

import covidmanagement.Main;
import covidmanagement.Utility;
import covidmanagement.controller.cachlycontroller.SuaCachLyController;
import covidmanagement.database.QueryDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CachLyModel {
    private int maCL;
    private int maNK;
    private String name;
    private String cmnd;
    private LocalDate begindate;
    private LocalDate finishdate;
    private String place;

    private MucDoCachLy mucdo;

    private Button changeButton, deleteButton;


    public CachLyModel(int maCL, int maNK, LocalDate begindate,LocalDate finishdate, String place, MucDoCachLy mucdo) {
        this.maCL = maCL;
        this.maNK = maNK;

        this.begindate = begindate;
        this.finishdate = finishdate;
        this.place = place;
        this.mucdo = mucdo;
        this.changeButton = new Button("Sửa");
        this.deleteButton = new Button("Xóa");
        changeButton.setOnAction(this::handleChangeClickCl);
        deleteButton.setOnAction(this::handleDeleteClickCl);
        setNameAndCMND(maNK);
    }
        public static List<CachLyModel> getCachLyList(){
            List<CachLyModel> queryList = new ArrayList<>();
            try {
                QueryDB queryDB = new QueryDB();
                String sql = "select * from cachly;";

                ResultSet rs = queryDB.executeQuery(sql);
                while (rs.next()) {
                    int _maCL = rs.getInt("macachly");
                    int _maNK = rs.getInt("manhankhau");
                    LocalDate _beginDate = rs.getDate("batdau").toLocalDate();
                    LocalDate _finishDate = rs.getDate("ketthuc").toLocalDate();
                    String _place = rs.getString("diadiem");
                    MucDoCachLy _mucdo = MucDoCachLy.valueOf(rs.getString("mucdo"));

                    queryList.add(new CachLyModel(_maCL, _maNK, _beginDate, _finishDate, _place, _mucdo));
                }
                rs.close();
                queryDB.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            return queryList;
        }

        public static void addCl(int maNK,String name, LocalDate beginDate, LocalDate finishDate, String place, MucDoCachLy mucdo) throws SQLException{
            NhanKhauModel nhanKhau = NhanKhauModel.getInstanceById(maNK);
            if (!nhanKhau.getHoTen().equalsIgnoreCase(name)) throw new SQLException("Tên và mã nhân khẩu không trùng khớp");
            QueryDB queryDB = new QueryDB();
            PreparedStatement statement = queryDB.getConnection().prepareStatement(
                    "INSERT INTO CachLy(manhankhau, batdau, ketthuc, diadiem, mucdo) VALUES (?, ?, ?, ?, ?);"
            );
            statement.setInt(1, maNK);
            statement.setDate(2, Date.valueOf(beginDate));
            statement.setDate(3, Date.valueOf(finishDate));
            statement.setString(4, place);
            statement.setString(5, mucdo.toString());
            statement.executeUpdate();
            statement.close();
            queryDB.close();

            LichSuModel.add("Thêm thông tin ca cách ly " + name);
        }

        public static void updateCl(int maCachLy, LocalDate begindate, LocalDate finishdate, String place, MucDoCachLy mucdo) throws SQLException{
            QueryDB queryDB = new QueryDB();
            PreparedStatement statement = queryDB.getConnection().prepareStatement(
                    "UPDATE CachLy SET (batdau ,ketthuc, diadiem, mucdo) = (?, ?, ?, ?) WHERE macachly = ?;"
            );
            statement.setDate(1, Date.valueOf(begindate));
            statement.setDate(2, Date.valueOf(finishdate));
            statement.setString(3, place);
            statement.setString(4, mucdo.toString());
            statement.setInt(5, maCachLy);
            statement.executeUpdate();
            statement.close();
            queryDB.close();

            LichSuModel.add("Sửa đổi thông tin ca cách ly số " + maCachLy);
        }

    public static void deleteCachLy(int maNhanKhau) throws SQLException {
        PreparedStatement preparedStatement = null;
        QueryDB queryDB = null;
        try {
            queryDB = new QueryDB();
            preparedStatement = queryDB.getConnection().prepareStatement(
                    "DELETE FROM cachly WHERE MaNhankhau = ?;");
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

    private void handleDeleteClickCl(ActionEvent event) {
            Utility.displayConfirmDeleteDialog("Xác nhận xóa", this.maCL, "CachLy");
            LichSuModel.add("Xóa ca cách ly " + this.maCL);
    }

    private void handleChangeClickCl(ActionEvent event) {
        System.out.println(maNK);
        System.out.println(name);
        System.out.println(begindate);
        System.out.println(finishdate);
        System.out.println(place);
        System.out.println(mucdo);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("cachly/suacachly-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Chỉnh sửa thông tin ca cách ly");
            stage.show();
            SuaCachLyController Controller = fxmlLoader.getController();
            Controller.setFieldCl(maCL, maNK, name, begindate, finishdate, place, mucdo);

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

    public LocalDate getBeginDate() {
        return begindate;
    }
    public LocalDate getFinishDate() {
        return finishdate;
    }

    public String getPlace() {
        return place;
    }

    public MucDoCachLy getMucdo() {
        return mucdo;
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
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public enum MucDoCachLy{
        UNDEFINED, F0, F1, F2, F3
    }
}




