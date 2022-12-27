package covidmanagement.controller;

import covidmanagement.Main;
import covidmanagement.Utility;
import covidmanagement.controller.xetnghiemcontroller.SuaController;
import covidmanagement.model.XetNghiemModel.KetQuaXetNghiem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TreeView<String> treeView;

    @FXML
    private BorderPane mainBorderPane;

    private final String[] components = new String[]{"nhân khẩu", "hộ khẩu", "xét nghiệm", "khai báo", "cách ly"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeItem<String> root = new TreeItem<>();

        for (String component: components){
            TreeItem<String> componentMain = new TreeItem<>("Quản lý " + component);

            componentMain.getChildren().add(new TreeItem<>("Thêm " + component));
            componentMain.getChildren().add(new TreeItem<>("Sửa " + component));
            if (!component.equals("xét nghiệm")) componentMain.getChildren().add(new TreeItem<>("Xóa " + component));
            componentMain.getChildren().add(new TreeItem<>("Tìm kiếm " + component));

            root.getChildren().add(componentMain);
        }

        treeView.setRoot(root);
        treeView.setShowRoot(false);
    }

    public void selectItem(){
        TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
        if (item == null) return;
        if (!item.isLeaf()) return;
        String fileName = Utility.removeAccent(item.getValue());
        String fileFolder = Utility.removeAccent(item.getParent().getValue()).substring(6);
        System.out.println(fileFolder + "/" + fileName);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fileFolder + "/" + fileName + "-view.fxml"));
            Parent componentScene = fxmlLoader.load();
            mainBorderPane.setCenter(componentScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveToLoginPage(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource( "login-view.fxml"));
            Parent componentScene = fxmlLoader.load();
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(componentScene);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveToSuaXetNghiemPage(int idNK, String name, LocalDate date, String place, KetQuaXetNghiem result){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("xetnghiem/suaxetnghiem-view.fxml"));
            Parent componentScene = fxmlLoader.load();
            SuaController suaController = fxmlLoader.getController();
            suaController.setField(idNK, name, date, place, result);
            mainBorderPane.setCenter(componentScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
