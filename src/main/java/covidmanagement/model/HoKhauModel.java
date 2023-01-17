package covidmanagement.model;

import covidmanagement.Main;
import covidmanagement.Utility;
import covidmanagement.controller.hokhaucontroller.SuaHoKhaucontroller;
import covidmanagement.database.QueryDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoKhauModel {
    private int stt;
    private int maHK;
    private int maCH;
    private String soNha;
    private String ngach;
    private String ngo;
    private String duong;
    private String phuong;
    private String quan;
    private String thanhPho;
    private Button suaButton, xoaButton;

    public HoKhauModel(  int maHK, String soNha, String ngach, String ngo, String duong, String phuong, String quan, String thanhPho) {
        this.maHK = maHK;
        this.soNha = soNha;
        this.ngach = ngach;
        this.ngo = ngo;
        this.duong = duong;
        this.phuong = phuong;
        this.quan = quan;
        this.thanhPho = thanhPho;
        this.suaButton = new Button("Sửa");
        this.xoaButton = new Button("Xóa");
        suaButton.setOnAction(this::suaClick);
        xoaButton.setOnAction(this::xoaClick);
    }

    public static List<HoKhauModel> getHoKhauList(){
        List<HoKhauModel> queryList = new ArrayList<>();
        try {
            QueryDB queryDB = new QueryDB();
            String sql = "select * from hokhau;";

            ResultSet rs = queryDB.executeQuery(sql);
            while (rs.next()) {
                int _maHK = rs.getInt("mahokhau");
                String _soNha = rs.getString("sonha");
                String _ngach = rs.getString("ngach");
                String _ngo = rs.getString("ngo");
                String _duong = rs.getString("duong");
                String _phuong = rs.getString("phuong");
                String _quan = rs.getString("quan");
                String _thanhPho = rs.getString("thanhpho");
                queryList.add(new HoKhauModel( _maHK, _soNha, _ngach,_ngo,_duong,_phuong,_quan,_thanhPho));
            }
            rs.close();
            queryDB.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return queryList;
    }

    public static void add(int maHK, String soNha, String ngach, String ngo, String duong, String phuong, String quan, String thanhPho) throws SQLException{
        QueryDB queryDB = new QueryDB();
        PreparedStatement statement = queryDB.getConnection().prepareStatement(
                "INSERT INTO HoKhau(mahokhau, sonha, ngach, ngo, duong, phuong, quan, thanhpho) VALUES (?, ?, ?, ?, ?, ?, ?, ?);"
        );
        statement.setInt(1, maHK);
        statement.setString(2, soNha);
        statement.setString(3, ngach);
        statement.setString(4, ngo);
        statement.setString(5, duong);
        statement.setString(6, phuong);
        statement.setString(7, quan);
        statement.setString(8, thanhPho);
        statement.executeUpdate();
        statement.close();
        queryDB.close();
    }

    public static void update(String soNha, String ngach, String ngo, String duong, String phuong, String quan, String thanhPho) throws SQLException{
        QueryDB queryDB = new QueryDB();
        PreparedStatement statement = queryDB.getConnection().prepareStatement(
                "UPDATE HoKhau SET (sonha, ngach, ngo, duong, phuong, quan, thanhpho) = (?, ?, ?, ?, ?, ?, ?) ;"
        );
        statement.setString(1, soNha);
        statement.setString(2, ngach);
        statement.setString(3, ngo);
        statement.setString(4, duong);
        statement.setString(5, phuong);
        statement.setString(6, quan);
        statement.setString(7, thanhPho);

        statement.executeUpdate();
        statement.close();
        queryDB.close();
    }
    private void xoaClick (ActionEvent event) {
        Utility.displayConfirmDeleteDialog("Xác nhận xóa?", this.maHK, "HoKhau");
    }
    private void suaClick(ActionEvent event){
        System.out.println(maHK);
        System.out.println(soNha);
        System.out.println(ngach);
        System.out.println(ngo);
        System.out.println(duong);
        System.out.println(phuong);
        System.out.println(quan);
        System.out.println(thanhPho);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hokhau/suahokhau-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            SuaHoKhaucontroller  controller = fxmlLoader.getController();
            controller.setField(maHK, soNha,ngach,ngo,duong,phuong,quan,thanhPho);
        } catch(IOException e){
            e.printStackTrace();
        }
    }



    public int getMaHK() {
        return maHK;
    }

    public int getMaCH() {
        return maCH;
    }

    public String getSoNha() {
        return soNha;
    }

    public String getNgach() {
        return ngach;
    }

    public String getNgo() {
        return ngo;
    }

    public String getDuong() {
        return duong;
    }

    public String getPhuong() {
        return phuong;
    }

    public String getQuan() {
        return quan;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public Button getSuaButton() {
        return suaButton;
    }

    public Button getXoaButton() {
        return xoaButton;
    }
}


