package covidmanagement.controller;

import covidmanagement.Main;
import covidmanagement.Utility;
import covidmanagement.model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private BorderPane loginPane;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button loginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginPane.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) onLogin(new ActionEvent(loginButton, loginButton));
            if (keyEvent.getCode() == KeyCode.ESCAPE) ((Stage)loginPane.getScene().getWindow()).close();
        });
    }

    public void onClose(ActionEvent event){
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    public void onLogin(ActionEvent event){
        String _username = username.getText();
        String _password = password.getText();
        if (_username.isBlank() || _password.isBlank()) {
            Utility.displayExceptionDialog(new RuntimeException("Tên đăng nhập và mật khẩu không được để trống"));
            return;
        }
        try {
            UserModel.validate(_username, _password);
            moveToMain();
            ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        } catch (SQLException | RuntimeException e){
            Utility.displayExceptionDialog(e);
        }
    }

    public void moveToMain(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource( "main-view.fxml"));
            Parent componentScene = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Quản lý nhân khẩu và dịch bệnh Covid");
            Scene scene = new Scene(componentScene);
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
