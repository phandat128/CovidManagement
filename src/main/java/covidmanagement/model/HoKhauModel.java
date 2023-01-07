package covidmanagement.model;

import covidmanagement.Main;
import covidmanagement.Utility;
import covidmanagement.controller.MainController;
import covidmanagement.database.QueryDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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

    public HoKhauModel(int stt, int maHK, int maCH, String soNha, String ngach, String ngo, String duong, String phuong, String quan, String thanhPho) {
        this.stt = stt;
        this.maHK = maHK;
        this.maCH = maCH;
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

    public List<HoKhauModel> getHoKhauList(String thanhPho,
                                           String phuong,
                                           String quan,
                                           String duong) throws SQLException {
        List<HoKhauModel> queryList = new ArrayList<>();
        QueryDB queryDB = new QueryDB();
        //TODO here
        String sql = "";

        ResultSet rs = queryDB.query(sql);
        while (rs.next()){
            int _stt = rs.getInt("stt");
            int _maHK = rs.getInt("mahokhaukhau");
            int _maCH = rs.getInt("machuho");
            String _soNha = rs.getString("sonha");
            String _ngach = rs.getString("ngach");
            String _ngo = rs.getString("ngo");
            String _duong = rs.getString("duong");
            String _phuong = rs.getString("phuong");
            String _quan = rs.getString("quan");
            String _thanhpho = rs.getString("thanhpho");
            queryList.add(new HoKhauModel(_stt, _maHK, _maCH, _soNha, _ngach, _ngo, _duong,_phuong,_quan,_thanhpho));
        }
        rs.close();
        queryDB.close();
        return queryList;
    }
    private void xoaClick (ActionEvent event) {
        Utility.displayConfirmDialog("Xác nhận xóa?", this.stt, "HoKhau");
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
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            MainController mainController = fxmlLoader.getController();
            mainController.moveToSuaHoKhauPage(maHK, soNha, ngach, ngo, duong, phuong, thanhPho);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public int getStt() {
        return stt;
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


