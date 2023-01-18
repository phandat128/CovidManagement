package covidmanagement.controller;

import covidmanagement.Utility;
import covidmanagement.model.UserModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) onLogin(new ActionEvent(loginButton, loginButton));
                if (keyEvent.getCode() == KeyCode.ESCAPE) stage.close();
            }
        });
    }

    public void onClose(ActionEvent event){
        stage.close();
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
            Utility.displaySuccessDialog("Đăng nhập thành công");
            stage.close();
        } catch (SQLException | RuntimeException e){
            Utility.displayExceptionDialog(e);
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
