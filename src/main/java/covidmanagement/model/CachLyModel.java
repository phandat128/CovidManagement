
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

public class CachLyModel {
    private int maCl;
    private int maNK;
    private String name;
    private String cmnd;
    private LocalDate begindate;
    private LocalDate finishdate;
    private String place;
    private Button changeButton, deleteButton;


    public CachLyModel(int maCL, int maNK, String name, String cmnd, LocalDate begindate,LocalDate finishdate, String place) {
        this.maCl = maCL;
        this.maNK = maNK;
        this.name = name;
        this.cmnd = cmnd;
        this.begindate = begindate;
        this.finishdate = finishdate;
        this.place = place;
        this.changeButton = new Button("Sửa");
        this.deleteButton = new Button("Xóa");
        changeButton.setOnAction(this::handleChangeClickCl);

        deleteButton.setOnAction(this::handleDeleteClickCl);

    }
    public List<CachLyModel> getCachLyList(String cmnd,
                                           String name,
                                           LocalDate beginfrom, LocalDate beginto,
                                           LocalDate finishfrom , LocalDate finishto) throws SQLException {
        List<CachLyModel> queryList = new ArrayList<>();
        QueryDB queryDB = new QueryDB();
        //TODO here
        String sql = "";

        ResultSet rs = queryDB.executeQuery(sql);
        while (rs.next()){
            int _maCL = rs.getInt("macachly");
            int _maNK = rs.getInt("manhankhau");
            String _cmnd = rs.getString("cmnd_cccd");
            String _name = rs.getString("hoten");
            LocalDate _begindate = rs.getDate("batdau").toLocalDate();
            LocalDate _finishdate = rs.getDate("ketthuc").toLocalDate();
            String _place = rs.getString("diadiem");

            queryList.add(new CachLyModel(_maCL, _maNK, _name, _cmnd, _begindate, _finishdate, _place));
        }
        rs.close();
        queryDB.close();
        return queryList;
    }
    private void handleDeleteClickCl(ActionEvent event) {
        Utility.displayConfirmDeleteDialog("Xác nhận xóa", this.maCl, "CachLy");
    }

    private void handleChangeClickCl(ActionEvent event) {
        System.out.println(maNK);
        System.out.println(name);
        System.out.println(begindate);
        System.out.println(finishdate);
        System.out.println(place);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            MainController mainController = fxmlLoader.getController();
            mainController.moveToSuaCachLyPage(maNK, name, begindate, finishdate, place);

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

    public LocalDate getBegindate() {
        return begindate;
    }
    public LocalDate getFinishdate() {
        return finishdate;
    }

    public String getPlace() {
        return place;
    }


    public Button getChangeButton() {
        return changeButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }
}

