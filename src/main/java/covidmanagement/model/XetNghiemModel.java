package covidmanagement.model;

import covidmanagement.Main;
import covidmanagement.controller.MainController;
import covidmanagement.database.QueryDB;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class XetNghiemModel {
    private int maNK;
    private String name;
    private String cmnd;
    private LocalDate date;
    private String place;
    private KetQuaXetNghiem result;
    private Button changeButton, deleteButton;

    public XetNghiemModel(int maNK, String name, String cmnd, LocalDate date, String place, KetQuaXetNghiem result) {
        this.maNK = maNK;
        this.name = name;
        this.cmnd = cmnd;
        this.date = date;
        this.place = place;
        this.result = result;
        this.changeButton = new Button("Sửa");
        this.deleteButton = new Button("Xóa");
        changeButton.setOnAction(event -> {
            System.out.println(maNK);
            System.out.println(name);
            System.out.println(date);
            System.out.println(place);
            System.out.println(result);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                MainController mainController = fxmlLoader.getController();
                mainController.moveToSuaXetNghiemPage(maNK, name, date, place, result);
            } catch(IOException e){
                e.printStackTrace();
            }
        });
    }

    public List<XetNghiemModel> getData(String cmnd,
                        String name,
                        LocalDate from, LocalDate to,
                        KetQuaXetNghiem result) throws SQLException {
        List<XetNghiemModel> queryList = new ArrayList<>();
        QueryDB queryDB = new QueryDB();
        //TODO here
        String sql = "";

        ResultSet rs = queryDB.query(sql);
        while (rs.next()){
            int _maNK = rs.getInt("manhankhau");
            String _cmnd = rs.getString("cmnd_cccd");
            String _name = rs.getString("hoten");
            LocalDate _date = rs.getDate("thoigian").toLocalDate();
            String _place = rs.getString("diadiem");
            KetQuaXetNghiem _result = rs.getObject("ketqua", KetQuaXetNghiem.class);
            queryList.add(new XetNghiemModel(_maNK, _name, _cmnd, _date, _place, _result));
        }
        rs.close();
        return queryList;
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

    public enum KetQuaXetNghiem{
        POSITIVE, NEGATIVE, UNDEFINED
    }
}
