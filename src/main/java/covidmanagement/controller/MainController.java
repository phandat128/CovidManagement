package covidmanagement.controller;

import covidmanagement.Main;
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
import java.text.Normalizer;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
            root.getChildren().add(componentMain);
        }

        treeView.setRoot(root);
        treeView.setShowRoot(false);
    }

    public void selectItem(){
        TreeItem<String> item = treeView.getSelectionModel().getSelectedItem();
        String temp = Normalizer.normalize(item.getValue(), Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String fileName = pattern.matcher(temp).replaceAll("").replace(" ", "").substring(6);
        System.out.println(fileName);
        try {
            Parent componentScene = FXMLLoader.load(Main.class.getResource(fileName + "-view.fxml"));
            mainBorderPane.setCenter(componentScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveToLoginPage(ActionEvent event){
        Parent componentScene = null;
        try {
            componentScene = FXMLLoader.load(Main.class.getResource( "login-view.fxml"));
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(componentScene);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
