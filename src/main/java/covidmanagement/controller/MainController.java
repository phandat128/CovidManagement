package covidmanagement.controller;

import covidmanagement.Main;
import covidmanagement.Utility;
import covidmanagement.controller.khaibaocontroller.SuaKhaiBaoController;
import covidmanagement.controller.khaibaocontroller.XemKhaiBaoController;
import covidmanagement.controller.xetnghiemcontroller.SuaController;
import covidmanagement.model.KhaiBaoModel;
import covidmanagement.model.XetNghiemModel.KetQuaXetNghiem;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

            final ObservableList<TreeItem<String>> branch = componentMain.getChildren();
            branch.add(new TreeItem<>("Thêm " + component));
            branch.add(new TreeItem<>("Tìm kiếm " + component));
            branch.add(new TreeItem<>("Chỉnh sửa " + component));

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
            Stage stage = new Stage();
            stage.setTitle("Đăng nhập");
            LoginController loginController = fxmlLoader.getController();
            loginController.setStage(stage);
            Scene scene = new Scene(componentScene);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public void moveToSuaKhaiBaoPage(String diemkhaibao, int maNhanKhau, LocalDate ngayKhaiBao, String lichTrinh,
//                                     boolean bhyt, boolean trieuchung, boolean tiepXucNguoiBenh, boolean tiepXucNguoiTuVungDich,
//                                     boolean tiepXucNguoiCoBieuHien, String benhNen){
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("khaibao/suakhaibao-view.fxml"));
//            Parent componentScene = fxmlLoader.load();
//            SuaKhaiBaoController suaController = fxmlLoader.getController();
//            suaController.setField(diemkhaibao, ngayKhaiBao, maNhanKhau, lichTrinh,
//                                bhyt, trieuchung, tiepXucNguoiBenh, tiepXucNguoiTuVungDich,
//                                tiepXucNguoiCoBieuHien, benhNen);
//            mainBorderPane.setCenter(componentScene);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void moveToXemKhaiBaoPage(String diemkhaibao, LocalDate ngayKhaiBao, int maNhanKhau, String lichTrinh,
//                                     boolean bhyt, boolean trieuchung, boolean tiepXucNguoiBenh, boolean tiepXucNguoiTuVungDich,
//                                     boolean tiepXucNguoiCoBieuHien, String benhNen){
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("khaibao/xemkhaibao-view.fxml"));
//            Parent componentScene = fxmlLoader.load();
//            XemKhaiBaoController xemController = fxmlLoader.getController();
//            xemController.setField(diemkhaibao, ngayKhaiBao, maNhanKhau, lichTrinh,
//                    bhyt, trieuchung, tiepXucNguoiBenh, tiepXucNguoiTuVungDich,
//                    tiepXucNguoiCoBieuHien, benhNen);
//            mainBorderPane.setCenter(componentScene);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
