package covidmanagement.model;

import covidmanagement.Main;
import covidmanagement.Utility;
import covidmanagement.controller.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;

public class KhaiBaoModel {
    private int maKhaiBao;
    private String hoTen;
    private static KhaiBaoModel.gioiTinh gioiTinh;
    private String diemKhaiBao;
    private LocalDate ngayKhaiBao;
    private String cmnd;
    private boolean BHYT;
    private String lichTrinh;
    private boolean trieuChung;
    private boolean tiepXucNguoiBenh;
    public boolean tiepXucNguoiTuVungDich;
    public boolean tiepXucNguoiCoBieuHien;
    public String benhNen;
    public Button changeButton;
    public Button deleteButton;
    public Button viewButton;

    public int getMaKhaiBao() {
        return maKhaiBao;
    }

    public String getHoTen() {
        return hoTen;
    }

    public static KhaiBaoModel.gioiTinh getGioiTinh() {
        return gioiTinh;
    }

    public String getDiemKhaiBao() {
        return diemKhaiBao;
    }

    public LocalDate getNgayKhaiBao() {
        return ngayKhaiBao;
    }

    public String getCmnd() {
        return cmnd;
    }

    public boolean isBHYT() {
        return BHYT;
    }

    public String getLichTrinh() {
        return lichTrinh;
    }

    public boolean isTrieuChung() {
        return trieuChung;
    }

    public boolean isTiepXucNguoiBenh() {
        return tiepXucNguoiBenh;
    }

    public boolean isTiepXucNguoiTuVungDich() {
        return tiepXucNguoiTuVungDich;
    }

    public boolean isTiepXucNguoiCoBieuHien() {
        return tiepXucNguoiCoBieuHien;
    }

    public String getBenhNen() {
        return benhNen;
    }

    public Button getChangeButton() {
        return changeButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }
    public Button getViewButton() {
        return viewButton;
    }

    public KhaiBaoModel(int maKhaiBao, String hoTen, String diemkhaibao, LocalDate ngayKhaiBao, String cmnd, KhaiBaoModel.gioiTinh gioiTinh,
                        boolean bhyt, String lichTrinh, boolean trieuchung, boolean tiepXucNguoiBenh,
                        boolean tiepXucNguoiTuVungDich, boolean tiepXucNguoiCoBieuHien, String benhNen) {
        this.maKhaiBao = maKhaiBao;
        this.hoTen = hoTen;
        this.diemKhaiBao = diemkhaibao;
        this.ngayKhaiBao = ngayKhaiBao;
        KhaiBaoModel.gioiTinh = gioiTinh;
        this.cmnd = cmnd;
        BHYT = bhyt;
        this.lichTrinh = lichTrinh;
        this.trieuChung = trieuchung;
        this.tiepXucNguoiBenh = tiepXucNguoiBenh;
        this.tiepXucNguoiTuVungDich = tiepXucNguoiTuVungDich;
        this.tiepXucNguoiCoBieuHien = tiepXucNguoiCoBieuHien;
        this.benhNen = benhNen;
        this.changeButton = new Button("Sửa");
        this.deleteButton = new Button("Xóa");
        this.viewButton = new Button("Xem");
        changeButton.setOnAction(this::handleChangeClick);
        deleteButton.setOnAction(this::handleDeleteClick);
        viewButton.setOnAction(this::handleViewClick);
    }

    private void handleDeleteClick(ActionEvent actionEvent) {
        Utility.displayConfirmDialog("Bạn muốn xóa khai báo này không?", maKhaiBao, "Khaibao");
    }

    private void handleChangeClick(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            MainController mainController = fxmlLoader.getController();
            mainController.moveToSuaKhaiBaoPage(diemKhaiBao, hoTen, cmnd, lichTrinh, gioiTinh,
                    BHYT, trieuChung, tiepXucNguoiBenh, tiepXucNguoiTuVungDich,
                    tiepXucNguoiCoBieuHien, benhNen, ngayKhaiBao);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    private void handleViewClick(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            MainController mainController = fxmlLoader.getController();
            mainController.moveToXemKhaiBaoPage(diemKhaiBao, hoTen, cmnd, lichTrinh, gioiTinh,
                    BHYT, trieuChung, tiepXucNguoiBenh, tiepXucNguoiTuVungDich,
                    tiepXucNguoiCoBieuHien, benhNen, ngayKhaiBao);
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public enum gioiTinh {
        NAM, NỮ, UNDEFINED;
    }
}
