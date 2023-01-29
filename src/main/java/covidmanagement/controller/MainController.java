package covidmanagement.controller;

import covidmanagement.Main;
import covidmanagement.Utility;
import covidmanagement.model.HoKhauModel;
import covidmanagement.model.NhanKhauModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class MainController implements Initializable {

    @FXML
    private TreeView<String> treeView;

    @FXML
    private BorderPane mainBorderPane;

    @FXML private Label totalHokhau, totalNhankhau;
    @FXML private BarChart<String, Integer> ageRangeBarChart;
    @FXML private PieChart genderPieChart;

    private final String[] COMPONENTS = new String[]{"nhân khẩu", "hộ khẩu", "xét nghiệm", "khai báo", "cách ly"};
    private final List<HoKhauModel> HOKHAU_LIST = HoKhauModel.getHoKhauList();
    private final List<NhanKhauModel> NHANKHAU_LIST = NhanKhauModel.getNhanKhauList();
    private final String[] AGE_RANGES = new String[]{"0-5", "6-10", "11-14", "15-17", "18-59", "60+"};
    private final String[] GENDERS = new String[]{"Nam", "Nữ"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeItem<String> root = new TreeItem<>();
        root.getChildren().add(new TreeItem<>("Trang chủ"));
        for (String component: COMPONENTS){
            TreeItem<String> manageComponent = new TreeItem<>("Quản lý " + component);
            final ObservableList<TreeItem<String>> branch = manageComponent.getChildren();
            branch.add(new TreeItem<>("Thêm " + component));
            branch.add(new TreeItem<>("Tìm kiếm " + component));
            branch.add(new TreeItem<>("Chỉnh sửa " + component));
            root.getChildren().add(manageComponent);
        }
        treeView.setRoot(root);
        treeView.setShowRoot(false);

        totalHokhau.setText(String.valueOf(HOKHAU_LIST.size()));
        totalNhankhau.setText(String.valueOf(NHANKHAU_LIST.size()));

        Map<String, Integer> ageRangeMap = ageRangeCount();
        XYChart.Series<String, Integer> ageRangeSerie = new XYChart.Series<>();
        for (String ageRange: AGE_RANGES){
            ageRangeSerie.getData().add(new XYChart.Data<>(ageRange, ageRangeMap.get(ageRange)));
        }
        ageRangeBarChart.getData().add(ageRangeSerie);

        Map<String, Integer> genderMap = genderCount();
        genderPieChart.setStartAngle(90);
        List<PieChart.Data> genderDataList = new ArrayList<>();
        for (String gender: GENDERS){
            genderDataList.add(new PieChart.Data(gender, genderMap.get(gender)));
        }
        genderPieChart.setData(FXCollections.observableArrayList(genderDataList));
    }

    private Map<String, Integer> genderCount(){
        Map<String, Integer> map = new HashMap<>();
        for (String gender: GENDERS){
            map.put(gender, 0);
        }
        for (NhanKhauModel nhankhau: NHANKHAU_LIST){
            if (nhankhau.getGioiTinh().equalsIgnoreCase(GENDERS[0])) map.put(GENDERS[0], map.get(GENDERS[0]) + 1);
            if (nhankhau.getGioiTinh().equalsIgnoreCase(GENDERS[1])) map.put(GENDERS[1], map.get(GENDERS[1]) + 1);
        }
        return map;
    }

    private Map<String, Integer> ageRangeCount(){
        Map<String, Integer> map = new HashMap<>();
        for (String ageRange: AGE_RANGES){
            map.put(ageRange, 0);
        }
        for (NhanKhauModel nhankhau: NHANKHAU_LIST){
            int age = Period.between(nhankhau.getNgaySinh(), LocalDate.now()).getYears();
            if (age < 6) map.put(AGE_RANGES[0], map.get(AGE_RANGES[0]) + 1);
            else if (age < 11) map.put(AGE_RANGES[1], map.get(AGE_RANGES[1]) + 1);
            else if (age < 15) map.put(AGE_RANGES[2], map.get(AGE_RANGES[2]) + 1);
            else if (age < 18) map.put(AGE_RANGES[3], map.get(AGE_RANGES[3]) + 1);
            else if (age < 60) map.put(AGE_RANGES[4], map.get(AGE_RANGES[4]) + 1);
            else map.put(AGE_RANGES[5], map.get(AGE_RANGES[5]) + 1);
        }
        return map;
    }

    public void selectItem(){
        TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
        if (item == null) return;
        if (!item.isLeaf()) return;
        String fileName = Utility.removeAccent(item.getValue());
        if (fileName.equalsIgnoreCase("trangchu")){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
                BorderPane mainView = fxmlLoader.load();
                this.mainBorderPane.setCenter(mainView.getCenter());
            } catch(IOException e){
                e.printStackTrace();
            }
            return;
        }
        String fileFolder = Utility.removeAccent(item.getParent().getValue()).substring(6);
        System.out.println(fileFolder + "/" + fileName);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fileFolder + "/" + fileName + "-view.fxml"));
            Parent centerComponent = fxmlLoader.load();
            mainBorderPane.setCenter(centerComponent);
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