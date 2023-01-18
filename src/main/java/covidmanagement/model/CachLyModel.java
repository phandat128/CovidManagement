package covidmanagement.model;

import covidmanagement.Main;
import covidmanagement.Utility;
import covidmanagement.controller.SuaCachLyController;
import covidmanagement.database.QueryDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
    private Button changeButton, deleteButton;


    public CachLyModel(int maCL, int maNK, LocalDate begindate,LocalDate finishdate, String place) {
        this.maCL = maCL;
        this.maNK = maNK;
        setNameAndCMNDCl(maNK);
        this.begindate = begindate;
        this.finishdate = finishdate;
        this.place = place;
        this.changeButton = new Button("Sửa");
        this.deleteButton = new Button("Xóa");
        changeButton.setOnAction(this::handleChangeClickCl);

        deleteButton.setOnAction(this::handleDeleteClickCl);
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
                    queryList.add(new CachLyModel(_maCL, _maNK, _beginDate, _finishDate, _place));
                }
                rs.close();
                queryDB.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            return queryList;
        }

        public static void addCl(int MaNK, LocalDate beginDate, LocalDate finishDate, String place) throws SQLException{
            QueryDB queryDB = new QueryDB();
            PreparedStatement statement = queryDB.getConnection().prepareStatement(
                    "INSERT INTO CachLy(manhankhau, batdau, ketthuc, diadiem) VALUES (?, ?, ?, ?);"
            );
            statement.setInt(1, MaNK);
            statement.setDate(2, Date.valueOf(beginDate));
            statement.setDate(3, Date.valueOf(finishDate));
            statement.setString(4, place);
            statement.executeUpdate();
            statement.close();
            queryDB.close();
        }

        public static void updateCl(int maCachLy, LocalDate begindate, LocalDate finishdate, String place) throws SQLException{
            QueryDB queryDB = new QueryDB();
            PreparedStatement statement = queryDB.getConnection().prepareStatement(
                    "UPDATE CachLy SET (batdau ,ketthuc, diadiem) = (?, ?, ?) WHERE macachly = ?;"
            );
            statement.setDate(1, Date.valueOf(begindate));
            statement.setDate(1, Date.valueOf(finishdate));
            statement.setString(2, place);
            statement.setInt(4, maCachLy);
            statement.executeUpdate();
            statement.close();
            queryDB.close();
        }

        private void handleDeleteClickCl(ActionEvent event) {
            Utility.displayConfirmDeleteDialog("Xác nhận xóa", this.maCL, "CachLy");
        }
        private void handleChangeClickCl(ActionEvent event) {
            System.out.println(maNK);
            System.out.println(name);
            System.out.println(begindate);
            System.out.println(finishdate);
            System.out.println(place);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("cachly/suacachly-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                SuaCachLyController Controller = fxmlLoader.getController();
                Controller.setFieldCl ( maCL, maNK, name, begindate, finishdate, place);

            } catch(IOException e){
                e.printStackTrace();
            }
        }
    public int getMaNKCl() {
        return maNK;
    }

    public String getNameCl() {
        return name;
    }

    public LocalDate getBegindate() {
        return begindate;
    }
    public LocalDate getFinishdate() {
        return finishdate;
    }

    public String getPlaceCl() {
        return place;
    }


    public Button getChangeButtonCl() {
        return changeButton;
    }

    public Button getDeleteButtonCl() {
        return deleteButton;
    }
    private void setNameAndCMNDCl(int maNK){
        //TODO
    }
}




